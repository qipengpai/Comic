package com.qin.crxl.comic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qin.crxl.comic.base.BaseEntity;
@SuppressWarnings("serial")
@Entity
@Table
public class DistributorWithdrawals extends BaseEntity implements Serializable {

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String distridutionId = ""; //分销商id
	private double todayRecharge= 0.0;//订单金额
	private int orderSum = 0; //订单数量
	private double withdrawalsMoney= 0.0;//提现金额
	private double proportion =0.0;//  分成比例
	private int withdrawalsState = 0; //提现状态  1已到账 0 待打款
	private String impldate = ""; //操作时间
	private String withdrawalsDate="";//提现时间
	private String remarks = ""; //备注
	
	
	public double getTodayRecharge() {
		return todayRecharge;
	}
	public void setTodayRecharge(double todayRecharge) {
		this.todayRecharge = todayRecharge;
	}
	public int getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(int orderSum) {
		this.orderSum = orderSum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDistridutionId() {
		return distridutionId;
	}
	public void setDistridutionId(String distridutionId) {
		this.distridutionId = distridutionId;
	}
	
	public double getProportion() {
		return proportion;
	}
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}
	public double getWithdrawalsMoney() {
		return withdrawalsMoney;
	}
	public void setWithdrawalsMoney(double withdrawalsMoney) {
		this.withdrawalsMoney = withdrawalsMoney;
	}
	public int getWithdrawalsState() {
		return withdrawalsState;
	}
	public void setWithdrawalsState(int withdrawalsState) {
		this.withdrawalsState = withdrawalsState;
	}
	public String getImpldate() {
		return impldate;
	}
	public void setImpldate(String impldate) {
		this.impldate = impldate;
	}
	public String getWithdrawalsDate() {
		return withdrawalsDate;
	}
	public void setWithdrawalsDate(String withdrawalsDate) {
		this.withdrawalsDate = withdrawalsDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
