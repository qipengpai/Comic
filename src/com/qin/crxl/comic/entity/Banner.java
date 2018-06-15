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
public class Banner extends BaseEntity implements Serializable{
	//-3225793557570219168L
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String cartoonId="";
	private String comicUrl = "";
	private String httpImg="";
	private int  state=0;
	private int  click = 0;
	private String title = "";
	private String implDate = "";
	private int iosState = 0;           //ios漫画上架状态状态
	private int anroidState = 0;           //android漫画上架状态状态
	
	
	public int getIosState() {
		return iosState;
	}
	public void setIosState(int iosState) {
		this.iosState = iosState;
	}
	public int getAnroidState() {
		return anroidState;
	}
	public void setAnroidState(int anroidState) {
		this.anroidState = anroidState;
	}
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
	public String getComicUrl() {
		return comicUrl;
	}
	public void setComicUrl(String comicUrl) {
		this.comicUrl = comicUrl;
	}
	public String getHttpImg() {
		return httpImg;
	}
	public void setHttpImg(String httpImg) {
		this.httpImg = httpImg;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	
	

}
