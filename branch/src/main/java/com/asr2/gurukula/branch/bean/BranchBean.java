package com.asr2.gurukula.branch.bean;

import com.asr2.gurukula.branch.dao.BranchDAO;
import com.asr2.gurukula.branch.model.Branch;
import com.asr2.gurukula.commons.api.Operation;
import com.asr2.gurukula.commons.dao.Result;
import com.asr2.gurukula.commons.web.FacesUtils;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by abhijith.nagarajan on 7/17/15.
 */
@Named
@SessionScoped
public class BranchBean implements Serializable {

    @Inject
    private BranchDAO branchDAO;

    private Result<Branch> result;

    private Operation operation;

    private boolean isRefresh;

    @Inject
    private Branch fBranch;

    public BranchBean() {
    }

    public Branch getBranch() {
        return fBranch;
    }

    public void setBranch(Branch branch) {
        fBranch = branch;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public Collection<Branch> getBranches() {
        if (null == result || isRefresh) {
            search();
            isRefresh = false;
        }

        return result.getResult();
    }

    public void createActionListener (ActionEvent event) {
        this.operation = Operation.CREATE;
        this.fBranch = new Branch();
    }

    @Transactional
    public String search() {
        result = branchDAO.getAll();
        return null;
    }

    @Transactional
    public String create() {
        Result<Branch> result = branchDAO.save(fBranch);

        FacesUtils.addFacesMessage(result.getMessages());

        if(result.isTxSuccessful()) {
            search();
            return "search";
        } else
            return null;
    }

    @Transactional
    public String update() {
        Result<Branch> result = branchDAO.update(fBranch);

        FacesUtils.addFacesMessage(result.getMessages());

        if(result.isTxSuccessful()) {
            search();
            return "search";
        } else
            return null;
    }

    @Transactional
    public String delete(String branchCode) {
        Result<Branch> result = branchDAO.delete(Branch.class, branchCode);
        FacesUtils.addFacesMessage(result.getMessages());
        return "search";
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
