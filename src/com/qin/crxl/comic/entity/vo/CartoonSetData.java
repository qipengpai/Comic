package com.qin.crxl.comic.entity.vo;

public class CartoonSetData extends BaseEntityVO{
	
	private String id = "";	 //id
	private String showPhoto = "";	 //展示图
	private String titile="";        //标题
	private String details="";     //介绍
	private String updateDate = "";	 //更新日期
	private String implDate = "";	 //操作时间
	private String cartoonId="";	 //漫画id
	private String barrageCount="";	 //弹幕次数
	private String commentCount ="";     //评论次数
	private String playCount ="";        //播放次数
	private String okCount ="";      //人数
	private String sort="";	 			 //序列
	private String vip="";	 			 //是否vip
	private String price="";	         //价格
	private String moneyState="";	     //付费状态
	private String watchState="";	     //观看状态
	private String nowPage ="";     //当前页
	private String pageNum ="";        //每页容量
	private String condition = "";           //查询条件
	private String veryOk = "";           //查询条件
	private String back;//话的排序规则（正--1  倒--0）
	
	
	
	public String getVeryOk() {
		return veryOk;
	}
	public void setVeryOk(String veryOk) {
		this.veryOk = veryOk;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShowPhoto() {
		return showPhoto;
	}
	public void setShowPhoto(String showPhoto) {
		this.showPhoto = showPhoto;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}

	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getBarrageCount() {
		return barrageCount;
	}
	public void setBarrageCount(String barrageCount) {
		this.barrageCount = barrageCount;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	public String getPlayCount() {
		return playCount;
	}
	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}
	public String getOkCount() {
		return okCount;
	}
	public void setOkCount(String okCount) {
		this.okCount = okCount;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMoneyState() {
		return moneyState;
	}
	public void setMoneyState(String moneyState) {
		this.moneyState = moneyState;
	}
	public String getWatchState() {
		return watchState;
	}
	public void setWatchState(String watchState) {
		this.watchState = watchState;
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
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	
	
}
