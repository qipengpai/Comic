package com.qin.crxl.comic.entity.vo;

public class CartoonNew {
	private String id = "";
	private String cartoonName="";	 //剧名
	private String updateTile="";	 //更新标题
	private String cartoonAuthor=""; //作者
	private String cartoonAuthorPic="";//作者头像
	private String mainPhoto = "";	 //首页主图
	private String commentCount ="";     //评论次数
	private String playCount ="";        //播放次数
	private String followCount ="";      //关注人数
	private String smallPhoto ="";      //关注人数
	private String midelPhoto = "";	 //中图     
	
	
	public String getMidelPhoto() {
		return midelPhoto;
	}
	public void setMidelPhoto(String midelPhoto) {
		this.midelPhoto = midelPhoto;
	}
	public String getSmallPhoto() {
		return smallPhoto;
	}
	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
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
	public String getMainPhoto() {
		return mainPhoto;
	}
	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
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
	
	
	
}
