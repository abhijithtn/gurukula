package com.asr2.gurukula.student.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.asr2.gurukula.branch.model.Branch;
import com.asr2.gurukula.commons.api.Message;
import com.asr2.gurukula.commons.api.Operation;
import com.asr2.gurukula.commons.dao.Request;
import com.asr2.gurukula.commons.dao.Result;
import com.asr2.gurukula.commons.web.FacesUtils;
import com.asr2.gurukula.commons.web.FileUpload;
import com.asr2.gurukula.student.dao.StudentDAO;
import com.asr2.gurukula.student.model.Student;

/**
 * Created by abhijith.nagarajan on 8/12/15.
 */
@Named
@ViewScoped
public class StudentBean implements Serializable{

	private Student fStudent;

	private int page = 0;

	@Inject
	private FileUpload fUpload;

	@Inject
	private DataProcessor fProcessor;

	@Inject
	private StudentDAO fStudentDAO;

	@Resource
	private ManagedExecutorService executorService;

	private Result<Student> result;

	private Operation operation;
	
	private boolean isRefresh;

	public StudentBean() {
		fStudent = new Student();
	}

	public Collection<Student> getStudents() {
		if (null == result || isRefresh) {
			search();
			isRefresh = false;
		}

		return result.getResult();
	}

	public void createActionListener (ActionEvent event) {
		this.operation = Operation.CREATE;
		this.fStudent = new Student();
	}


	@Transactional
	public String search() {
		result = fStudentDAO.search(new Request<>(0,10, fStudent));
		return null;
	}

	@Transactional
	public String create() {
		Result<Student> result = fStudentDAO.save(fStudent);

		FacesUtils.addFacesMessage(result.getMessages());

		if(result.isTxSuccessful()) {
			search();
			return "search";
		} else
			return null;
	}

	@Transactional
	public String update() {
		Result<Student> result = fStudentDAO.update(fStudent);

		FacesUtils.addFacesMessage(result.getMessages());

		if(result.isTxSuccessful()) {
			search();
			return "search";
		} else
			return null;
	}

	@Transactional
	public String delete(String studentId) {
		Result<Student> result = fStudentDAO.delete(Student.class, studentId);
		FacesUtils.addFacesMessage(result.getMessages());
		return "search";
	}

	public void handleFileUpload(FileUploadEvent event) {
		this.getUpload().setUploadFile(event.getFile());
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@Transactional
	public String processFile() throws Exception{
		String fileName = "Test";
		if(this.getUpload().getUploadFile() != null && this.getUpload().getUploadFile().getFileName() != null) {
			fileName = this.getUpload().getUploadFile().getFileName();
		}

		fProcessor.process(this.getUpload().getUploadFile().getInputstream(), this.getUpload().getHeaderRowNum());

		FacesUtils.addFacesMessage(new Message(FacesMessage.SEVERITY_INFO, "File is uploaded and will be processed " +
				"shortly"));
		return "search";
	}










	public FileUpload getUpload() {
		return fUpload;
	}

	public void setUpload(FileUpload upload) {
		fUpload = upload;
	}

	public Student getStudent() {
		return fStudent;
	}

	public void setStudent(Student student) {
		fStudent = student;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Result<Student> getResult() {
		return result;
	}

	public void setResult(Result<Student> result) {
		this.result = result;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public boolean isRefresh() {
		return isRefresh;
	}

	public void setIsRefresh(boolean isRefresh) {
		this.isRefresh = isRefresh;
	}



}
