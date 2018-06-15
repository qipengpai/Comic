package com.qin.crxl.comic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.qin.crxl.comic.base.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table
public class Cartoon extends BaseEntity implements Serializable{
//	private static final long serialVersionUID =5799566047874135643L;
	@SuppressWarnings("static-access")
	@Id
	@Column(nullable=false,columnDefinition="VARCHAR(32) default 0") 
	private String id = ran.generateString(32);
//	private String cartoonType = ""; //类型
	private String firstType="";	 //一级类型
	private String cartoonName="";	 //剧名
	private String updateTile="";	 //更新标题
	private String cartoonAuthor=""; //作者
	private String cartoonAuthorPic="";//作者头像
	private String updateType = "";	 //更新类型	
	private String mainPhoto = "";	 //首页主图
	private String introduction =""; //介绍图
	private String midelPhoto = "";	 //中图
	private String smallPhoto ="";   //小圖
	private String sharePhoto ="";   //分享图
	private String introduc="";	     //短介绍
	private int hot=0;               //热度
	private int sort = 0;	         //排序	
	private int commentCount =0;     //评论次数
	private int playCount =0;        //播放次数
	private int followCount =0;      //关注人数
	private int state = 0;           //漫画上架状态状态
	private String updateDate="";	 //更新时间
	private String implDate="";	     //操作时间
	private String serialState="";	     //連載類型 
	private int iosState = 0;           //ios漫画上架状态状态
	private int anroidState = 0;           //android漫画上架状态状态
	private int distributorState = 0;           //分销商漫画上架状态状态
	private int recommendationIndex = 0;           //推荐指数
	
	public String getSharePhoto() {
		return sharePhoto;
	}
	public void setSharePhoto(String sharePhoto) {
		this.sharePhoto = sharePhoto;
	}
	public String getSerialState() {
		return serialState;
	}
	public void setSerialState(String serialState) {
		this.serialState = serialState;
	}
	public String getFirstType() {
		return firstType;
	}
	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public String getCartoonType() {
//		return cartoonType;
//	}
//	public void setCartoonType(String cartoonType) {
//		this.cartoonType = cartoonType;
//	}
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
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getMainPhoto() {
		return mainPhoto;
	}
	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getIntroduc() {
		return introduc;
	}
	public void setIntroduc(String introduc) {
		this.introduc = introduc;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getImplDate() {
		return implDate;
	}
	public void setImplDate(String implDate) {
		this.implDate = implDate;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
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
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getIosState() {
		return iosState;
	}
	public void setIosState(int iosState) {
		this.iosState = iosState;
	}
	public int getAnroidState() {
		return anroidState;
	}
	public void setAnroidState(int anroidState) {
		this.anroidState = anroidState;
	}
	public int getDistributorState() {
		return distributorState;
	}
	public void setDistributorState(int distributorState) {
		this.distributorState = distributorState;
	}
	public int getRecommendationIndex() {
		return recommendationIndex;
	}
	public void setRecommendationIndex(int recommendationIndex) {
		this.recommendationIndex = recommendationIndex;
	}
	
}
