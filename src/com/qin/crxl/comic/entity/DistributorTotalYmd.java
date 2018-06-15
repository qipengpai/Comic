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
public class DistributorTotalYmd extends BaseEntity implements Serializable {
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String distridutionId = "";//分销商Id
	private String implDate = "";//统计日期
	private int totalType =0;//状态  1 本月 2 总计
	private double recharge = 0.0;//充值金额
	private double ordinaryRecharge = 0.0;//咔咔豆充值金额
	private int ordinaryRechargeNum = 0;//咔咔豆充值笔数
	private double vipRecharge = 0.0;//vip充值金额
	private int vipRechargeNum = 0;//vip充值笔数
	private int rechargePersonNum = 0;//充值人数
	private double perCapitaRecharge = 0.0;//人均充值
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
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public int getTotalType() {
		return totalType;
	}
	public void setTotalType(int totalType) {
		this.totalType = totalType;
	}
	public double getRecharge() {
		return recharge;
	}
	public void setRecharge(double recharge) {
		this.recharge = recharge;
	}
	public double getOrdinaryRecharge() {
		return ordinaryRecharge;
	}
	public void setOrdinaryRecharge(double ordinaryRecharge) {
		this.ordinaryRecharge = ordinaryRecharge;
	}
	public int getOrdinaryRechargeNum() {
		return ordinaryRechargeNum;
	}
	public void setOrdinaryRechargeNum(int ordinaryRechargeNum) {
		this.ordinaryRechargeNum = ordinaryRechargeNum;
	}
	public double getVipRecharge() {
		return vipRecharge;
	}
	public void setVipRecharge(double vipRecharge) {
		this.vipRecharge = vipRecharge;
	}
	public int getVipRechargeNum() {
		return vipRechargeNum;
	}
	public void setVipRechargeNum(int vipRechargeNum) {
		this.vipRechargeNum = vipRechargeNum;
	}
	public int getRechargePersonNum() {
		return rechargePersonNum;
	}
	public void setRechargePersonNum(int rechargePersonNum) {
		this.rechargePersonNum = rechargePersonNum;
	}
	public double getPerCapitaRecharge() {
		return perCapitaRecharge;
	}
	public void setPerCapitaRecharge(double perCapitaRecharge) {
		this.perCapitaRecharge = perCapitaRecharge;
	}
	
	
}
