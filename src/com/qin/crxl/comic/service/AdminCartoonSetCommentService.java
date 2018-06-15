package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonSetComment;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;


/**
 * 用户对话的评论接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminCartoonSetCommentService extends BaseService<CartoonSetComment>{
	//查询用户对话的评论
	List<Object[]> selectCartoonSetComment(CartoonSetCommentData cartoonSetCommentData);
	
	//查询用户对话的评论的数量
	int getCount(CartoonSetCommentData cartoonSetCommentData);
	
	//修改评论的状态（用户可观看状态）
	boolean updateCartoonSetCommentState(CartoonSetCommentData cartoonSetCommentData, String string);
	
	//查询漫画话评论的评论数量
	int getCommentNum(String commentId);
	
	
	
	//查询漫画话评论的评论
	List<Object[]> selectCartoonCommentAndComment(CartoonSetCommentData cartoonSetCommentData);
		
	//查询漫画话评论的评论数量
	int getCartoonCommentAndCommentCount(CartoonSetCommentData cartoonSetCommentData);
	
	//修改漫画话评论的评论
	boolean updateCommentAndComment(CartoonSetCommentData cartoonSetCommentData);
	
	
}