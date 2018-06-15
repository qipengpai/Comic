package com.qin.crxl.comic.entity.vo;

public class CartoonSetCommentData extends BaseEntityVO{
	private String id = "";
	private String cartoonSetId = ""; //每话id
	//private String userId="";	      //用户id
	private String commentId ="";     //评论id
	private String commentInfo="";    //内容
	private String commentDate="";    //评论时间
	private String aite = "";	      //@	
	private String commenCount = "";     //评论次数
	private String okCount="";	          //点赞次数
	private String sort="";               //排序
	private String implDate = "";     //操作时间
	private String nowPage ="";     //当前页
	private String pageNum ="";        //每页容量
	private String condition = "";           //查询条件
	private String bestNew = "";           //是否最新
	private String veryOk = "";           //是否最新
	private String cartoonId = ""; //每话id ------------ 
	private String deleteCartoonSetComment; //漫画的每话评论状态（用户是否可看  0--用户不可看   1--用户可看）


	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getVeryOk() {
		return veryOk;
	}
	public void setVeryOk(String veryOk) {
		this.veryOk = veryOk;
	}
	
	public String getBestNew() {
		return bestNew;
	}
	public void setBestNew(String bestNew) {
		this.bestNew = bestNew;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartoonSetId() {
		return cartoonSetId;
	}
	public void setCartoonSetId(String cartoonSetId) {
		this.cartoonSetId = cartoonSetId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	
	public String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getAite() {
		return aite;
	}
	public void setAite(String aite) {
		this.aite = aite;
	}
	public String getCommenCount() {
		return commenCount;
	}
	public void setCommenCount(String commenCount) {
		this.commenCount = commenCount;
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
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
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

	public String getDeleteCartoonSetComment() {
		return deleteCartoonSetComment;
	}
	public void setDeleteCartoonSetComment(String deleteCartoonSetComment) {
		this.deleteCartoonSetComment = deleteCartoonSetComment;
	}

	
}
