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
public class CartoonAllModel extends BaseEntity implements Serializable {
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable = false, columnDefinition = "VARCHAR(32) default 0")
	private String id = uuid.getUUID();
	private String CartoonModelId;// 漫画类型Id
	private String CartoonId;// 漫画Id
	private int sort;// 排序

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCartoonModelId() {
		return CartoonModelId;
	}

	public void setCartoonModelId(String cartoonModelId) {
		CartoonModelId = cartoonModelId;
	}

	public String getCartoonId() {
		return CartoonId;
	}

	public void setCartoonId(String cartoonId) {
		CartoonId = cartoonId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
