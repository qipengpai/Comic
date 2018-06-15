package com.qin.crxl.comic.entity.vo;

public class CartoonHead {

	private String id = "";
	private String cartoonName="";	 //剧名
	private String updateTile="";	 //更新标题
	private String cartoonAuthor=""; //作者
	private String cartoonAuthorPic="";//作者头像
	private String introduction =""; //介绍图
	private String commentCount ="";     //评论次数
	private String playCount ="";        //播放次数
	private String followCount ="";      //关注人数
	private String serialState="";	     //連載類型 
	private String introduc="";	     //短介绍
	private String sharePhoto="";	     //短介绍
	
	
	
	public String getSharePhoto() {
		return sharePhoto;
	}
	public void setSharePhoto(String sharePhoto) {
		this.sharePhoto = sharePhoto;
	}
	public String getIntroduc() {
		return introduc;
	}
	public void setIntroduc(String introduc) {
		this.introduc = introduc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCartoonName() {
		return cartoonName;
	}
	public void setCartoonName(String cartoonName) {
		this.cartoonName = cartoonName;
	}
	public String getUpdateTile() {
		return updateTile;
	}
	public void setUpdateTile(String updateTile) {
		this.updateTile = updateTile;
	}
	public String getCartoonAuthor() {
		return cartoonAuthor;
	}
	public void setCartoonAuthor(String cartoonAuthor) {
		this.cartoonAuthor = cartoonAuthor;
	}
	public String getCartoonAuthorPic() {
		return cartoonAuthorPic;
	}
	public void setCartoonAuthorPic(String cartoonAuthorPic) {
		this.cartoonAuthorPic = cartoonAuthorPic;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
	public String getFollowCount() {
		return followCount;
	}
	public void setFollowCount(String followCount) {
		this.followCount = followCount;
	}
	public String getSerialState() {
		return serialState;
	}
	public void setSerialState(String serialState) {
		this.serialState = serialState;
	}
	
	
}