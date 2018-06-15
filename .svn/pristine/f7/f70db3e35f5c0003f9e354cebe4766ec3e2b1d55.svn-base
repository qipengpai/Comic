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
public class DistributorCartoonModel extends BaseEntity implements Serializable {

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String cartoonId = "";//操作时间
	private String implDate = "";//操作时间
	private String modelUrl = "";//模型路径
	private int modelType =0;//模型类型 1  正文魔板 2 原文引导末班
	private int state = 0; //状态
	private int sort = 0; //排序
	
	
	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public String getModelUrl() {
		return modelUrl;
	}
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	public int getModelType() {
		return modelType;
	}
	public void setModelType(int modelType) {
		this.modelType = modelType;
	}
}
