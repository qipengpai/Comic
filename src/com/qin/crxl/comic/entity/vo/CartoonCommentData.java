package com.qin.crxl.comic.entity.vo;

public class CartoonCommentData extends BaseEntityVO{
	private String id;
	private String cartoonId;    //漫画id
	private String commentId;     //评论id
	private String commentInfo;    //内容
	private String commentDate;    //评论时间
	private String aite;	      //@	
	private String commentCount;     //评论次数
	private String okCount;	          //点赞次数
	private String sort;               //排序
	private String implDate;     //操作时间
	private String nowPage;     //当前页
	private String pageNum;        //每页容量
	private String condition;           //查询条件
	private String bestNew;           //是否最新
	private String veryOk;           //0点赞1取消点赞
	private String deleteCartoonComment; //漫画评论状态（用户是否可看  0--用户不可看   1--用户可看）
	
	
	
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
	public String getCartoonId() {
		return cartoonId;
	}
	public void setCartoonId(String cartoonId) {
		this.cartoonId = cartoonId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
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
	public String getDeleteCartoonComment() {
		return deleteCartoonComment;
	}
	public void setDeleteCartoonComment(String deleteCartoonComment) {
		this.deleteCartoonComment = deleteCartoonComment;
	}
	
	
}
