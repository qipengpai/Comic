


package com.qin.crxl.comic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qin.crxl.comic.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table
public class PhoneVerification extends BaseEntity implements Serializable{
	@SuppressWarnings("static-access")
    @Id
    @Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id=ran.generateString(32);
	private String phone="";
	private String code="";
	private Date impl=new Date();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getImpl() {
		return impl;
	}
	public void setImpl(Date impl) {
		this.impl = impl;
	}
	
}
