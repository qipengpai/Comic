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
public class Product extends BaseEntity implements Serializable {

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = ran.generateString(32);
	private String productId = "";
	private String name = "";
	private String introduc = ""; // 短介绍
	private String introduction = ""; // 介绍图
	private String photo = ""; // 中图
	private double oldPrice = 0.0; // 原价
	private double price = 0.0; // 價格
	private int state = 0; // 狀態
	private int saleState = 0; // 打折状态狀態
	private int currency = 0; // 奖励 
	private double sale = 0.0; // 打折比例
	private int sort = 0; // 排序
	private int hot = 0; // 热度
	private String implDate = ""; // 操作時間
	private String type = ""; // 操作時間
	
	
	
	public double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getSaleState() {
		return saleState;
	}

	public void setSaleState(int saleState) {
		this.saleState = saleState;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduc() {
		return introduc;
	}

	public void setIntroduc(String introduc) {
		this.introduc = introduc;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public String getImplDate() {
		return implDate;
	}

	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}