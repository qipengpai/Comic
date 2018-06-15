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
public class CartoonSet extends BaseEntity implements Serializable{
	
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String showPhoto = "";	 //展示图
	private String titile="";        //标题
	private String updateTitile="";  //标题
	private String details="";       //介绍
	private String updateDate = "";	 //更新日期
	private String implDate = "";	 //操作时间
	private String cartoonId="";	 //漫画id
	private String barrageCount="";	 //弹幕次数
	private int commentCount =0;     //评论次数
	private int playCount =0;        //播放次数
	private int buyCount =0;        //购买次数
	private int okCount =0;      //点赞数
	private int sort=0;	 			 //序列
	private int vip=0;	 			 //是否vip
	private int price=0;	         //价格
	private int moneyState=0;	     //付费状态
	private int watchState=0;	     //观看状态
	
	
	
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public String getUpdateTitile() {
		return updateTitile;
	}
	public void setUpdateTitile(String updateTitile) {
		this.updateTitile = updateTitile;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	
	public int getOkCount() {
		return okCount;
	}
	public void setOkCount(int okCount) {
		this.okCount = okCount;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getBarrageCount() {
		return barrageCount;
	}
	public void setBarrageCount(String barrageCount) {
		this.barrageCount = barrageCount;
	}

	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShowPhoto() {
		return showPhoto;
	}
	public void setShowPhoto(String showPhoto) {
		this.showPhoto = showPhoto;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getWatchState() {
		return watchState;
	}
	public void setWatchState(int watchState) {
		this.watchState = watchState;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getMoneyState() {
		return moneyState;
	}
	public void setMoneyState(int moneyState) {
		this.moneyState = moneyState;
	}
	
	
}
