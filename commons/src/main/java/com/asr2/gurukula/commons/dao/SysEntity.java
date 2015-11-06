package com.asr2.gurukula.commons.dao;

import com.asr2.gurukula.commons.api.DateUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by abhijith.nagarajan on 7/17/15.
 */
@MappedSuperclass
public abstract class SysEntity implements Serializable {

    @NotNull
    @Column(name="created_on", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @NotNull
    @Column(name="updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @PrePersist
    private void onInsert() {
        this.createdOn = DateUtils.getCurrentDate();
        this.updatedOn = this.createdOn;
    }

    @PreUpdate
    private void onUpdate() {

        this.createdOn = DateUtils.getCurrentDate();
        this.updatedOn = this.createdOn;
    }

    public abstract String getPK();

    public abstract Class getKlass();

}
