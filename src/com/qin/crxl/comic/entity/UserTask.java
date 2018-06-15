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
public class UserTask extends BaseEntity implements Serializable {
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
	private String userId = ""; // 用戶id
	private int taskId = 0; // 任務id
	private int taskState = 0; // 任務狀態
	private String implDate = "";
	private int buttonState = 0;
	private int type = 0;
	private int sort = 0;

	public int getType() {
		return type;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getButtonState() {
		return buttonState;
	}

	public void setButtonState(int buttonState) {
		this.buttonState = buttonState;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getTaskState() {
		return taskState;
	}

	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImplDate() {
		return implDate;
	}

	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}

}
