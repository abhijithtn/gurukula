package com.asr2.gurukula.student.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.asr2.gurukula.commons.dao.GenericDAO;
import com.asr2.gurukula.commons.dao.Request;
import com.asr2.gurukula.commons.dao.Result;
import com.asr2.gurukula.student.model.Student;
import com.asr2.gurukula.student.model.Student_;

/**
 * Created by abhijith.nagarajan on 8/12/15.
 */
public class StudentDAO extends GenericDAO<Student> {

	public Result<Student> search(Request<Student> request) {

		Result<Student> result = new Result<>();
		CriteriaBuilder criteriaBuilder = super.getEm().getCriteriaBuilder();
		CriteriaQuery<Student> criteria = criteriaBuilder.createQuery(Student.class);
		Root root = criteria.from(Student.class);
		criteria.select(root);
		request.getSearchObj().addWhereClause(root, criteriaBuilder, criteria);
		criteria.orderBy(criteriaBuilder.asc(root.get(Student_.name)));
		result.setIsTxSuccessful(true);
		result.setResult(super.getEm().createQuery(criteria).getResultList());
		return result;
	}


}
