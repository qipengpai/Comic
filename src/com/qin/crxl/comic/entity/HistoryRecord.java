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
public class HistoryRecord extends BaseEntity implements Serializable{

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String userId = "";	     //用户id
	private String cartoonId = "";	 //漫画id
	private String cartoonSetId="";  //每话id
	private String implDate="";      //操作日期
	private int watchState=0;     //观看状态
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
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public int getWatchState() {
		return watchState;
	}
	public void setWatchState(int watchState) {
		this.watchState = watchState;
	}
	
	

}