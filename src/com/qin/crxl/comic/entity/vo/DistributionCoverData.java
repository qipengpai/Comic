package com.qin.crxl.comic.entity.vo;

public class DistributionCoverData {

	private String id = "";
	private String cartoonId = ""; //漫画Id
	private String cartoonCover = "";//文案封面
	private String implDate = ""; //操作日期
	private String state = ""; //状态
	private String sort = ""; //排序
	private String nowPage ="";     //当前页
	private String pageNum ="";        //每页容量
	private String condition = "";           //查询条件
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getCartoonCover() {
		return cartoonCover;
	}
	public void setCartoonCover(String cartoonCover) {
		this.cartoonCover = cartoonCover;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getNowPage() {
		return nowPage;
	}
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
}
