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
public class Distributor extends BaseEntity implements Serializable {
	
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String userName = "";//账号
	private String qd = "";//渠道
	private String userPwd = "";//密码
	private String nickName = "";//用户名
	private String implDate = "";//注册日期
	private String lastLoginDate = "";//最后登录日期
	private String headImg = "";//头像
	private String email = "";// email
	private String userType = "";// 账号类型 
	private String phone = "";// 手机号
	private String payType = "";//用户支付类型
	private String AccountNum = "";// 付款账号
	private String AccountName = "";// 收款人姓名
	private int state = 0;// 使用状态
	private int withdrawalsType =0; //提现类型 1日结 2 周结 3 月结 
	private double balance =0.0;//  余额
	private double proportion =0.0;//  分成比例
	private double allRecharge=0.0;//总充值
	private double overRecharge=0.0;//已结算充值
	
	
	
	public String getQd() {
		return qd;
	}
	public void setQd(String qd) {
		this.qd = qd;
	}
	public double getOverRecharge() {
		return overRecharge;
	}
	public void setOverRecharge(double overRecharge) {
		this.overRecharge = overRecharge;
	}
	public double getAllRecharge() {
		return allRecharge;
	}
	public void setAllRecharge(double allRecharge) {
		this.allRecharge = allRecharge;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getWithdrawalsType() {
		return withdrawalsType;
	}
	public void setWithdrawalsType(int withdrawalsType) {
		this.withdrawalsType = withdrawalsType;
	}
	public double getProportion() {
		return proportion;
	}
	public void setProportion(double proportion) {
		this.proportion = proportion;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getAccountNum() {
		return AccountNum;
	}
	public void setAccountNum(String accountNum) {
		AccountNum = accountNum;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	
	
	
	
	
	
	

}
