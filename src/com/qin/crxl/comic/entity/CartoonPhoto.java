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
public class CartoonPhoto extends BaseEntity implements Serializable{
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String cartoonId = "";	 //漫画id
	private String cartoonSetId="";  //每话id
	private String photoUrl = "";	 //图片路径
	private int sort = 0;	 	     //序号
	private String implDate="";      //操作日期
	private String photoHeight="";  //图片高度
	private String photoWidth="";       //图片宽度
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getCartoonSetId() {
		return cartoonSetId;
	}
	public void setCartoonSetId(String cartoonSetId) {
		this.cartoonSetId = cartoonSetId;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public String getPhotoHeight() {
		return photoHeight;
	}
	public void setPhotoHeight(String photoHeight) {
		this.photoHeight = photoHeight;
	}
	public String getPhotoWidth() {
		return photoWidth;
	}
	public void setPhotoWidth(String photoWidth) {
		this.photoWidth = photoWidth;
	}
	
	
	
	
}
