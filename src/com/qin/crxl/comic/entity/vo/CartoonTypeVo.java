package com.qin.crxl.comic.entity.vo;

public class CartoonTypeVo extends BaseEntityVO {

	private String id ;
	private String cartoonType;
	private String  click;
	private String 	showNum;
	private String sort;
	private String sortNum;//用于调整排序标记
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartoonType() {
		return cartoonType;
	}
	public void setCartoonType(String cartoonType) {
		this.cartoonType = cartoonType;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public String getShowNum() {
		return showNum;
	}
	public void setShowNum(String showNum) {
		this.showNum = showNum;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortNum() {
		return sortNum;
	}
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	
	
}