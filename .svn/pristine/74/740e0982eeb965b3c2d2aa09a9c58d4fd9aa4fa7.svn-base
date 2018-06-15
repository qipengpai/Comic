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
public class Barrage extends BaseEntity implements Serializable{

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String userId="";	      //用户id
	private String cartoonId = ""; //每话id
	private String cartoonSetId = ""; //每话id
	private String cartoonPhotoId="";//图片Id
	private String contentInfo="";    //内容
	private String contentDate="";    //弹幕时间
	private int state=0;               //排序
	private String implDate = "";     //操作时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	public String getCartoonPhotoId() {
		return cartoonPhotoId;
	}
	public void setCartoonPhotoId(String cartoonPhotoId) {
		this.cartoonPhotoId = cartoonPhotoId;
	}
	public String getContentInfo() {
		return contentInfo;
	}
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	public String getContentDate() {
		return contentDate;
	}
	public void setContentDate(String contentDate) {
		this.contentDate = contentDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	
}
