package com.qin.crxl.comic.entity.vo;

public class NewsData extends BaseEntityVO{
	private String id ="";
	private String content = "";      //消息内容
	private String title="";	      //消息标题
	private String type="";	          //消息类型
	private String sendDate="";       //发送时间
	private String implDate="";       //操作时间
	private String state="";      		 //消息状态
	private String deleteState="";       //删除状态
	private String nowPage="";       //当前页
	private String pageNum ="";        //每页容量
	
	public String getNowPage() {
		return nowPage;
	}
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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
	public String getDeleteState() {
		return deleteState;
	}
	public void setDeleteState(String deleteState) {
		this.deleteState = deleteState;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
