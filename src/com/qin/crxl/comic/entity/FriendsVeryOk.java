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
public class FriendsVeryOk extends BaseEntity implements Serializable {
	
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String userId ="";           //用户id
	private String friendCircleId ="";        //朋友圈评论id
	private String okDate = "";          //点赞时间
	private int okState = 0;          //点赞状态
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
	public String getOkDate() {
		return okDate;
	}
	public void setOkDate(String okDate) {
		this.okDate = okDate;
	}
	public int getOkState() {
		return okState;
	}
	public void setOkState(int okState) {
		this.okState = okState;
	}
	
	
	
	
}
