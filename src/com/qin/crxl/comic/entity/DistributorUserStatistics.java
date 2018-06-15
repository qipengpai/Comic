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
public class DistributorUserStatistics extends BaseEntity implements Serializable{

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String distridutionId = ""; //分销商id
	private String statisticsDate = ""; //统计日期
	private int newUser = 0; //分销商id
	private int payUser = 0; //付费用户
	private int manUser = 0; //男性
	private int womanUser = 0; //女性
	private int noUser = 0; //未知性别用户
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
	public String getStatisticsDate() {
		return statisticsDate;
	}
	public void setStatisticsDate(String statisticsDate) {
		this.statisticsDate = statisticsDate;
	}
	public int getNewUser() {
		return newUser;
	}
	public void setNewUser(int newUser) {
		this.newUser = newUser;
	}
	public int getPayUser() {
		return payUser;
	}
	public void setPayUser(int payUser) {
		this.payUser = payUser;
	}
	public int getManUser() {
		return manUser;
	}
	public void setManUser(int manUser) {
		this.manUser = manUser;
	}
	public int getWomanUser() {
		return womanUser;
	}
	public void setWomanUser(int womanUser) {
		this.womanUser = womanUser;
	}
	public int getNoUser() {
		return noUser;
	}
	public void setNoUser(int noUser) {
		this.noUser = noUser;
	}
	
	
	
}
