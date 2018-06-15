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
public class FriendsComment extends BaseEntity implements Serializable {

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String userId = ""; // 用户id
	private String friendCircleId = ""; // 朋友圈id
	private String commentInfo = ""; // 内容
	private String commentDate = ""; // 评论时间
	private String aite = ""; // @
	private int commentCount = 0; // 评论次数
	private int okCount = 0; // 点赞次数
	private int sort = 0; // 排序
	private String implDate = ""; // 操作时间
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
	public String getFriendCircleId() {
		return friendCircleId;
	}
	public void setFriendCircleId(String friendCircleId) {
		this.friendCircleId = friendCircleId;
	}
	public String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
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
	
	

}
