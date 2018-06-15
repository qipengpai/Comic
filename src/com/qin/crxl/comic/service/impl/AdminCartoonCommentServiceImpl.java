package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonComment;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;
import com.qin.crxl.comic.service.AdminCartoonCommentService;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;
@Service
public class AdminCartoonCommentServiceImpl extends BaseServiceImpl<CartoonComment> implements
		AdminCartoonCommentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectCartoonComment(
			CartoonCommentData cartoonCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,a.cartoonId,c.cartoonName,u.openid,u.nickname,a.commentInfo,a.commentDate,a.commentCount,a.okCount,a.deleteCartoonComment,a.sort from CartoonComment a,Cartoon c,UserEntity u where a.cartoonId=c.id and a.userId=u.userId and c.id='"+cartoonCommentData.getCartoonId()+"'");
		if (!ParaClick.clickString(cartoonCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or c.cartoonName like '%"+cartoonCommentData.getCondition()+"%')");
		}
		if(!ParaClick.clickString(cartoonCommentData.getStarTime())&&!ParaClick.clickString(cartoonCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonCommentData.getStarTime()+" 00:00:00' and '"+ cartoonCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonCommentData.getDeleteCartoonComment())){
			sb.append(" AND a.deleteCartoonComment="+ cartoonCommentData.getDeleteCartoonComment().trim());
		}
		sb.append(" order by a.deleteCartoonComment DESC,a.commentDate DESC LIMIT "
				+ (Integer.parseInt(cartoonCommentData.getNowPage()) - 1) * Integer.parseInt(cartoonCommentData.getPageNum()) + ","
				+ Integer.parseInt(cartoonCommentData.getPageNum()));
		List<Object[]> list=null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public CartoonComment selectCartoonCommentById(
			CartoonCommentData cartoonCommentData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCartoonComment(CartoonCommentData cartoonCommentData,String cartoonId) {
		CartoonComment cartoonComment=null;
		try {
			cartoonComment=super.get(cartoonCommentData.getId());
			if(ParaClick.clickObj(cartoonComment)){
				cartoonComment.setDeleteCartoonComment(Integer.parseInt(cartoonCommentData.getDeleteCartoonComment().trim()));
				return true;
			}
			JedisUtil.batchDel("CC-"+cartoonId);
			JedisUtil.batchDel("CCS-"+cartoonId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public int getCount(CartoonCommentData cartoonCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from CartoonComment a,Cartoon c,UserEntity u where a.cartoonId=c.id and a.userId=u.userId and c.id='"+cartoonCommentData.getCartoonId()+"'");
		if (!ParaClick.clickString(cartoonCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonCommentData.getCommentInfo()+"%')");
		}
		if(!ParaClick.clickString(cartoonCommentData.getStarTime())&&!ParaClick.clickString(cartoonCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonCommentData.getStarTime()+" 00:00:00' and '"+ cartoonCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonCommentData.getDeleteCartoonComment())){
			sb.append(" AND c.cartoonAuthor like '%" + cartoonCommentData.getDeleteCartoonComment().trim()+"%'");
		}
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}

	@Override
	public int getCommentNum(String commentId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from CartoonComment where commentId='"+commentId+"'");
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}
	//查询漫画评论的评论
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectCartoonCommentAndComment(
			CartoonCommentData cartoonCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,u.openid,u.nickname,a.commentInfo,a.commentDate,a.commentCount,a.okCount,a.deleteCartoonComment,a.sort from CartoonComment a,UserEntity u where a.userId=u.userId and a.commentId='"+cartoonCommentData.getId()+"'");
		if (!ParaClick.clickString(cartoonCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or c.cartoonName like '%"+cartoonCommentData.getCondition()+"%')");
		}
		if(!ParaClick.clickString(cartoonCommentData.getStarTime())&&!ParaClick.clickString(cartoonCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonCommentData.getStarTime()+" 00:00:00' and '"+ cartoonCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonCommentData.getDeleteCartoonComment())){
			sb.append(" AND a.deleteCartoonComment="+ cartoonCommentData.getDeleteCartoonComment().trim());
		}
		sb.append(" order by a.deleteCartoonComment DESC,a.commentDate DESC LIMIT "
				+ (Integer.parseInt(cartoonCommentData.getNowPage()) - 1) * Integer.parseInt(cartoonCommentData.getPageNum()) + ","
				+ Integer.parseInt(cartoonCommentData.getPageNum()));
		System.out.println(sb.toString());
		List<Object[]> list=null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}
	//查询漫画评论的评论数量
	@Override
	public int getCartoonCommentAndCommentCount(
			CartoonCommentData cartoonCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from CartoonComment a,UserEntity u where a.userId=u.userId and a.commentId='"+cartoonCommentData.getId()+"'");
		if (!ParaClick.clickString(cartoonCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonCommentData.getCondition()+"%' or u.nickName like '%"+cartoonCommentData.getCondition()+"%' or c.cartoonName like '%"+cartoonCommentData.getCondition()+"%')");
		}
		if(!ParaClick.clickString(cartoonCommentData.getStarTime())&&!ParaClick.clickString(cartoonCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonCommentData.getStarTime()+" 00:00:00' and '"+ cartoonCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonCommentData.getDeleteCartoonComment())){
			sb.append(" AND a.deleteCartoonComment="+ cartoonCommentData.getDeleteCartoonComment().trim());
		}
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}
	
	//修改漫画评论的评论的状态
	@Override
//	@CacheEvict(value="CartoonComment",allEntries=true)
	public boolean updateCartoonCommentAndComment(
			CartoonCommentData cartoonCommentData,String cartoonId) {
		CartoonComment cartoonComment=null;
		try {
			cartoonComment=super.get(cartoonCommentData.getId());
			if(!ParaClick.clickObj(cartoonComment)){
				return false;
			}
			cartoonComment.setDeleteCartoonComment(Integer.parseInt(cartoonCommentData.getDeleteCartoonComment().trim()));
			//////////redis
			JedisUtil.del("CCS-"+cartoonId+"-"+cartoonCommentData.getId()+"-Two");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
