package com.asr2.gurukula.branch.model;

import com.asr2.gurukula.commons.dao.SysEntity;
import org.apache.commons.lang3.StringUtils;

import javax.json.Json;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Locale;

/**
 * Created by abhijith.nagarajan on 7/17/15.
 */
@Entity
@Table(name = "TB_BRANCH")
public class Branch extends SysEntity {

    @Column
    @NotNull
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[A-Za-z \\,]+$", message = "Name can have only alphabets and spaces")
    private String name;

    @Id
    @Size(min = 2, max = 10)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Code can have only alphabets")
    private String code;

    public Branch() {
    }

    public Branch(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.capitalize(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = StringUtils.upperCase(code, Locale.ENGLISH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        return code.equals(branch.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return Json.createObjectBuilder().add("name", getName()).add("code", getCode()).build().toString();
    }

    @Override
    public String getPK() {
        return this.code;
    }

    @Override
    public Class getKlass() {
        return this.getClass();
    }
}
