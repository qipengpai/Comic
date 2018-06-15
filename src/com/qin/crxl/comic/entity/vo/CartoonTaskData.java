package com.qin.crxl.comic.entity.vo;

public class CartoonTaskData extends BaseEntityVO{
    private String id="";
	private String taskName="";					//任務名  
	private String state="";       //任務狀態
	private String implDate="";     //操作時間
	private String taskAward="";         //任務獎勵
	private String taskType="";         //任務類型
	private String taskInfo="";         //任務類型
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTaskAward() {
		return taskAward;
	}
	public void setTaskAward(String taskAward) {
		this.taskAward = taskAward;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
 
	
}
