package com.asr2.gurukula.branch.dao;

import com.asr2.gurukula.branch.model.Branch;
import com.asr2.gurukula.branch.model.Branch_;
import com.asr2.gurukula.commons.api.Message;
import com.asr2.gurukula.commons.dao.GenericDAO;
import com.asr2.gurukula.commons.dao.Result;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Optional;

/**
 * Created by abhijith.nagarajan on 7/17/15.
 */
public class BranchDAO extends GenericDAO<Branch> implements Serializable {


    public Branch get(String code) {
        return super.getEm().find(Branch.class, code);
    }

    public Result<Branch> getAll() {
        Result<Branch> result = new Result<Branch>();
        CriteriaBuilder criteriaBuilder = super.getEm().getCriteriaBuilder();
        CriteriaQuery<Branch> criteria = criteriaBuilder.createQuery(Branch.class);
        Root root = criteria.from(Branch.class);
        criteria.select(root);
        criteria.orderBy(criteriaBuilder.asc(root.get(Branch_.name)), criteriaBuilder.asc(root.get(Branch_.code)));
        result.setIsTxSuccessful(true);
        result.setResult(super.getEm().createQuery(criteria).getResultList());
        return result;
    }
}
