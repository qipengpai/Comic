package com.qin.crxl.comic.entity.vo;

public class CartoonPhotoData extends BaseEntityVO{

	private String id ="";
	private String cartoonId = "";	 //漫画id
	private String cartoonSetId="";  //每话id
	private String photoUrl = "";	 //图片路径
	private String sort = "";	 	     //序号
	private String implDate="";      //操作日期
	private String condition = "";	 //查询条件
	private String nowPage = "";	 	     //页数
	private String pageNum ="";        //每页容量
	private String vip ="";        //每话vip
	private String price ="";        //每话價格
	private String up ="";        //每话價格
	private String moneyState ="";        //每话價格
	private String photoHeight="";  //图片高度
	private String photoWidth="";       //图片宽度
	
	public String getMoneyState() {
		return moneyState;
	}
	public void setMoneyState(String moneyState) {
		this.moneyState = moneyState;
	}
	public String getUp() {
		return up;
	}
	public void setUp(String up) {
		this.up = up;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
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
	public String getCartoonSetId() {
		return cartoonSetId;
	}
	public void setCartoonSetId(String cartoonSetId) {
		this.cartoonSetId = cartoonSetId;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	public String getPhotoHeight() {
		return photoHeight;
	}
	public void setPhotoHeight(String photoHeight) {
		this.photoHeight = photoHeight;
	}
	public String getPhotoWidth() {
		return photoWidth;
	}
	public void setPhotoWidth(String photoWidth) {
		this.photoWidth = photoWidth;
	}
	
	
	
}
