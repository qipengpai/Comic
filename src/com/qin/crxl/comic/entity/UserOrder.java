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
public class UserOrder extends BaseEntity implements Serializable {
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = ran.generateString(32);
	private String orderUserId = "";
	private String orderUserName = "";
	private String orderNum = "";
	private int orderMoney = 0;
	private String orderDescription = "";
	private String orderRemarks = "";
	private int orderCurrency = 0;
	private String orderIntegral = "";
	private int orderState = 0;
	private String orderDate = "";
	private String orderImplDate = "";
	private String mchCreateIp;//用户Ip
	
	
	
	public String getMchCreateIp() {
		return mchCreateIp;
	}

	public void setMchCreateIp(String mchCreateIp) {
		this.mchCreateIp = mchCreateIp;
	}

	public String getOrderUserName() {
		return orderUserName;
	}

	public void setOrderUserName(String orderUserName) {
		this.orderUserName = orderUserName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(int orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getOrderRemarks() {
		return orderRemarks;
	}

	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}

	public int getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(int orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getOrderIntegral() {
		return orderIntegral;
	}

	public void setOrderIntegral(String orderIntegral) {
		this.orderIntegral = orderIntegral;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderImplDate() {
		return orderImplDate;
	}

	public void setOrderImplDate(String orderImplDate) {
		this.orderImplDate = orderImplDate;
	}

}
