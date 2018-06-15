package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonSetComment;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;
import com.qin.crxl.comic.service.AdminCartoonSetCommentService;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;
@Service
public class AdminCartoonSetCommentServiceImpl extends
		BaseServiceImpl<CartoonSetComment> implements
		AdminCartoonSetCommentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectCartoonSetComment(
			CartoonSetCommentData cartoonSetCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,u.openid,u.nickname,a.commentInfo,a.commentDate,a.commentCount,a.okCount,a.deleteCartoonSetComment,a.sort from CartoonSetComment a, CartoonSet c,UserEntity u where a.cartoonSetId=c.id and a.userId=u.userId and a.cartoonSetId='"+cartoonSetCommentData.getCartoonSetId()+"'");
		if (!ParaClick.clickString(cartoonSetCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonSetCommentData.getCondition()+"%' or u.nickName like '%"+cartoonSetCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonSetCommentData.getCommentInfo()+"%')");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getStarTime())&&!ParaClick.clickString(cartoonSetCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonSetCommentData.getStarTime()+" 00:00:00' and '"+ cartoonSetCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getDeleteCartoonSetComment())){
			sb.append(" AND a.deleteCartoonSetComment="+ cartoonSetCommentData.getDeleteCartoonSetComment().trim());
		}
		sb.append(" order by a.deleteCartoonSetComment DESC,a.commentDate DESC LIMIT "
				+ (Integer.parseInt(cartoonSetCommentData.getNowPage().trim()) - 1) * Integer.parseInt(cartoonSetCommentData.getPageNum()) + ","
				+ Integer.parseInt(cartoonSetCommentData.getPageNum()));
		System.out.println(sb.toString());
		List<Object[]> list=null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getCount(CartoonSetCommentData cartoonSetCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from CartoonSetComment a, CartoonSet c,UserEntity u where a.cartoonSetId=c.id and a.userId=u.userId and c.id='"+cartoonSetCommentData.getCartoonSetId()+"'");
		if (!ParaClick.clickString(cartoonSetCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonSetCommentData.getCondition()+"%' or u.nickName like '%"+cartoonSetCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonSetCommentData.getCommentInfo()+"%')");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getStarTime())&&!ParaClick.clickString(cartoonSetCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonSetCommentData.getStarTime()+" 00:00:00' and '"+ cartoonSetCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getDeleteCartoonSetComment())){
			sb.append(" AND a.deleteCartoonComment="+ cartoonSetCommentData.getDeleteCartoonSetComment().trim());
		}
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gettotalpage;
	}

	@Override
	public boolean updateCartoonSetCommentState(
			CartoonSetCommentData cartoonSetCommentData,String cartoonSetId) {
		CartoonSetComment cartoonSetComment=null;
		try {
			cartoonSetComment=super.get(cartoonSetCommentData.getId());
			if(ParaClick.clickObj(cartoonSetComment)){
				cartoonSetComment.setDeleteCartoonSetComment(Integer.parseInt(cartoonSetCommentData.getDeleteCartoonSetComment().trim()));
				return true;
			}
			JedisUtil.batchDel("CSC-"+cartoonSetId);
			JedisUtil.batchDel("CSCS-"+cartoonSetId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getCommentNum(String commentId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from CartoonSetComment where commentId='"+commentId+"'");
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectCartoonCommentAndComment(
			CartoonSetCommentData cartoonSetCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select a.id,u.openid,u.nickname,a.commentInfo,a.commentDate,a.commentCount,a.okCount,a.deleteCartoonSetComment from CartoonSetComment a,UserEntity u where a.userId=u.userId and a.commentId='"+cartoonSetCommentData.getId()+"'");
		if (!ParaClick.clickString(cartoonSetCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonSetCommentData.getCondition()+"%' or u.nickName like '%"+cartoonSetCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonSetCommentData.getCommentInfo()+"%')");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getStarTime())&&!ParaClick.clickString(cartoonSetCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonSetCommentData.getStarTime()+" 00:00:00' and '"+ cartoonSetCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getDeleteCartoonSetComment())){
			sb.append(" AND a.deleteCartoonSetComment="+ cartoonSetCommentData.getDeleteCartoonSetComment().trim());
		}
		sb.append(" order by a.deleteCartoonSetComment DESC,a.commentDate DESC LIMIT "
				+ (Integer.parseInt(cartoonSetCommentData.getNowPage()) - 1) * Integer.parseInt(cartoonSetCommentData.getPageNum()) + ","
				+ Integer.parseInt(cartoonSetCommentData.getPageNum()));
		List<Object[]> list=null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getCartoonCommentAndCommentCount(
			CartoonSetCommentData cartoonSetCommentData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from CartoonSetComment a,UserEntity u where a.userId=u.userId and a.commentId='"+cartoonSetCommentData.getId()+"'");
		if (!ParaClick.clickString(cartoonSetCommentData.getCondition())) {
			sb.append(" AND (u.openid like '%" + cartoonSetCommentData.getCondition()+"%' or u.nickName like '%"+cartoonSetCommentData.getCondition()+"%' or a.commentInfo like '%"+cartoonSetCommentData.getCommentInfo()+"%')");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getStarTime())&&!ParaClick.clickString(cartoonSetCommentData.getEndTime())){
			sb.append(" and a.commentDate BETWEEN '"+cartoonSetCommentData.getStarTime()+" 00:00:00' and '"+ cartoonSetCommentData.getEndTime()+" 23:59:59'");
		}
		if(!ParaClick.clickString(cartoonSetCommentData.getDeleteCartoonSetComment())){
			sb.append(" AND a.deleteCartoonComment="+ cartoonSetCommentData.getDeleteCartoonSetComment().trim());
		}
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gettotalpage;
	}

	@Override
	public boolean updateCommentAndComment(
			CartoonSetCommentData cartoonSetCommentData) {
		CartoonSetComment cartoonSetComment=null;
		try {
			cartoonSetComment=super.get(cartoonSetCommentData.getId());
			if(!ParaClick.clickObj(cartoonSetComment)){
				return false;
			}
			cartoonSetComment.setDeleteCartoonSetComment(Integer.parseInt(cartoonSetCommentData.getDeleteCartoonSetComment().trim()));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
