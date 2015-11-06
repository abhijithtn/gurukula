package com.asr2.gurukula.commons.web;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.UploadedFile;

import com.asr2.gurukula.commons.api.Message;

/**
 * Created by abhijith.nagarajan on 10/13/15.
 */
@ManagedBean
public class FileUpload implements Serializable {

	private UploadedFile uploadFile;

	private int headerRowNum = 5;

	public int getHeaderRowNum() {
		return headerRowNum;
	}

	public void setHeaderRowNum(int headerRowNum) {
		this.headerRowNum = headerRowNum;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
