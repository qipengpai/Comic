package com.qin.crxl.comic.entity.vo;

public class CartoonTypeData extends
 BaseEntityVO{
	
	private String id ="";
	private String cartoonType="";
	private String  click="";
	private String 	showNum=" ";
	private String  nowPage="";
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
	public String getNowPage() {
		return nowPage;
	}
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

}
