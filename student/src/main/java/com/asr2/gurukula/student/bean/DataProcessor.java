package com.asr2.gurukula.student.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.asr2.gurukula.branch.dao.BranchDAO;
import com.asr2.gurukula.branch.model.Branch;
import com.asr2.gurukula.student.dao.StudentDAO;
import com.asr2.gurukula.student.model.Student;
import com.google.common.collect.Lists;

/**
 * Created by abhijith.nagarajan on 11/2/15.
 */
class DataProcessor implements Serializable {

	public DataProcessor() {
	}

	@Inject
	private BranchDAO fBranchDAO;

	@Inject
	private StudentDAO fStudentDAO;

	public void process(InputStream is, int headerRowNum) {
		try {

			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);

			if(sheet.getLastRowNum() <= headerRowNum)
				return;

			Branch branch = getBranch(sheet.getRow(headerRowNum - 2));

			final List<Student> studentList = Lists.newLinkedList();

			Iterator<Row> rowIterator = sheet.rowIterator();
			rowIterator.forEachRemaining((r) -> {
				if(r.getRowNum() < headerRowNum) {
					// do nothing
				} else {
					Student student = new Student();
					int i = 1;
					student.setUsn(r.getCell(i++, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					student.setName(r.getCell(i++, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					student.setFatherName(r.getCell(i++, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					student.setMotherName(r.getCell(i++, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					student.setGender(r.getCell(i++, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					student.setCategory(r.getCell(i++, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
					student.setBranch(branch);
					studentList.add(student);
				}
			});


			fStudentDAO.saveAll(studentList);

		} catch (InvalidFormatException|IOException e) {
			e.printStackTrace();
		}
	}

	private Branch getBranch (Row row) {
		String branchCode = row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
		branchCode = StringUtils.trimToEmpty(StringUtils.substringAfter(branchCode, ":"));
		Branch branch = fBranchDAO.get(branchCode);

		if(branch == null && StringUtils.isNotEmpty(branchCode)) {
			branch = new Branch(StringUtils.rightPad(branchCode, 5, "A"), branchCode.toUpperCase());
			fBranchDAO.save(branch);
		}

		return branch;
	}
}
