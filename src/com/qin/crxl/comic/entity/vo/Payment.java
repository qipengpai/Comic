package com.qin.crxl.comic.entity.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qin.crxl.comic.base.BaseEntity;



public class Payment  {
	
	private String appId;
	private String deviceType;
	private String funcode;
	private String mhtOrderNo;
	private String mhtOrderName;
	private String version;
	private String mhtCurrencyType;
	private String mhtOrderAmt;
	private String mhtOrderDetail;
	private String mhtOrderType;
	private String mhtOrderStartTime;
	private String notifyUrl;
	private String frontNotifyUrl;
	private String mhtCharset;
	private String outputType;
	private String payChannelType;
	private String mhtSignType;
	private String mhtSignature;
	
	public String getMhtSignature() {
		return mhtSignature;
	}
	public void setMhtSignature(String mhtSignature) {
		this.mhtSignature = mhtSignature;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getFuncode() {
		return funcode;
	}
	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}
	public String getMhtOrderNo() {
		return mhtOrderNo;
	}
	public void setMhtOrderNo(String mhtOrderNo) {
		this.mhtOrderNo = mhtOrderNo;
	}
	public String getMhtOrderName() {
		return mhtOrderName;
	}
	public void setMhtOrderName(String mhtOrderName) {
		this.mhtOrderName = mhtOrderName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMhtCurrencyType() {
		return mhtCurrencyType;
	}
	public void setMhtCurrencyType(String mhtCurrencyType) {
		this.mhtCurrencyType = mhtCurrencyType;
	}
	public String getMhtOrderAmt() {
		return mhtOrderAmt;
	}
	public void setMhtOrderAmt(String mhtOrderAmt) {
		this.mhtOrderAmt = mhtOrderAmt;
	}
	public String getMhtOrderDetail() {
		return mhtOrderDetail;
	}
	public void setMhtOrderDetail(String mhtOrderDetail) {
		this.mhtOrderDetail = mhtOrderDetail;
	}
	public String getMhtOrderType() {
		return mhtOrderType;
	}
	public void setMhtOrderType(String mhtOrderType) {
		this.mhtOrderType = mhtOrderType;
	}
	public String getMhtOrderStartTime() {
		return mhtOrderStartTime;
	}
	public void setMhtOrderStartTime(String mhtOrderStartTime) {
		this.mhtOrderStartTime = mhtOrderStartTime;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getFrontNotifyUrl() {
		return frontNotifyUrl;
	}
	public void setFrontNotifyUrl(String frontNotifyUrl) {
		this.frontNotifyUrl = frontNotifyUrl;
	}
	public String getMhtCharset() {
		return mhtCharset;
	}
	public void setMhtCharset(String mhtCharset) {
		this.mhtCharset = mhtCharset;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	public String getPayChannelType() {
		return payChannelType;
	}
	public void setPayChannelType(String payChannelType) {
		this.payChannelType = payChannelType;
	}
	public String getMhtSignType() {
		return mhtSignType;
	}
	public void setMhtSignType(String mhtSignType) {
		this.mhtSignType = mhtSignType;
	}
	
	
	
}
