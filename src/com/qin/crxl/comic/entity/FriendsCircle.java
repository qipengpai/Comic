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
public class FriendsCircle extends BaseEntity implements Serializable {

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String userId = ""; // 用户id
	private String releaseInfo = ""; // 发布内容
	private String photo = ""; // 图片(后台管理用 不要轻易删)
	private String releaseDate = ""; // 发布时间
	private String aite = ""; // @
	private int commentCount = 0; // 评论次数
	private int okCount = 0; // 点赞次数
	private String implDate = ""; // 操作时间
	private int state = 0; // 状态
	private int deleteState = 1; //删除状态
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
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
	public String getReleaseInfo() {
		return releaseInfo;
	}
	public void setReleaseInfo(String releaseInfo) {
		this.releaseInfo = releaseInfo;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getAite() {
		return aite;
	}
	public void setAite(String aite) {
		this.aite = aite;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getOkCount() {
		return okCount;
	}
	public void setOkCount(int okCount) {
		this.okCount = okCount;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getDeleteState() {
		return deleteState;
	}
	public void setDeleteState(int deleteState) {
		this.deleteState = deleteState;
	}
	
	
	
	
}
