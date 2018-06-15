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
public class DistributorNews extends BaseEntity implements Serializable {

	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String title = ""; //标题
	private String ReleaseDate = "";//发布日期
	private String implDate = ""; //操作日期
	private String content = ""; //发布内容
	private int state = 0; //发布内容
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReleaseDate() {
		return ReleaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
