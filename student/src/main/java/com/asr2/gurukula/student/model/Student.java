package com.asr2.gurukula.student.model;

import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.asr2.gurukula.branch.model.Branch;
import com.asr2.gurukula.commons.dao.Searchable;
import com.asr2.gurukula.commons.dao.SysEntity;
import com.google.common.collect.Lists;

/**
 * Created by abhijith.nagarajan on 8/9/15.
 */
@Entity
@Table(name = "TB_STUDENT")
public class Student extends SysEntity {

	@NotNull
	@Searchable
	@Id
	private String usn;

	@NotNull
	@Searchable
	private String name;

	@NotNull
	private String fatherName;

	@NotNull
	private String motherName;

	@NotNull
	private String category;

	@NotNull
	private String gender;

	@ManyToOne(optional = false)
	@JoinColumn(name = "STUDENT_BRANCH", referencedColumnName = "code")
	@Searchable
	private Branch branch;

	private Date joinedOn;

	@NotNull
	@Searchable
	private int currentSemester;

	public Student() {
	}

	public String getUsn() {
		return usn;
	}

	public void setUsn(String usn) {
		this.usn = usn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Date getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}

	public int getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		return !(usn != null ? !usn.equals(student.usn) : student.usn != null);

	}

	@Override
	public int hashCode() {
		int result = usn.hashCode();
		result = 31 * result + (usn != null ? usn.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return Json.createObjectBuilder()
				.add("usn", getUsn())
				.add("name", getName())
				.add("branch", getBranch().getName())
				.add("semester", getCurrentSemester())
				.build().toString();
	}

	@Override
	public String getPK() {
		return getUsn();
	}

	@Override
	public Class getKlass() {
		return Student.class;
	}

	public void addWhereClause(Root root, CriteriaBuilder builder, CriteriaQuery<Student> query) {
		if(StringUtils.isNotEmpty(this.name))
			query.where(builder.like(root.get(Student_.fatherName), this.fatherName));

		if(StringUtils.isNotEmpty(this.name))
			query.where(builder.like(root.get(Student_.motherName), this.motherName));


	}
}
