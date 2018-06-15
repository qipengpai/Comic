package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonComment;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;
/**
 * 漫画评论接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminCartoonCommentService extends BaseService<CartoonComment>{

	//查询所有漫画的评论
	List<Object[]> selectCartoonComment(CartoonCommentData cartoonCommentData);
	
	//查询漫画评论（id）
	CartoonComment selectCartoonCommentById(CartoonCommentData cartoonCommentData);
	
	//修改漫画评论
	boolean updateCartoonComment(CartoonCommentData cartoonCommentData, String string);
	
	//查询漫画评论数量
	int getCount(CartoonCommentData cartoonCommentData);
	
	//查询漫画评论的评论数量
	int getCommentNum(String commentId);
	
	//查询漫画评论的评论
	List<Object[]> selectCartoonCommentAndComment(CartoonCommentData cartoonCommentData);
	
	//查询漫画评论的评论数量
	int getCartoonCommentAndCommentCount(CartoonCommentData cartoonCommentData);
	
	//修改漫画评论的评论的状态
	boolean updateCartoonCommentAndComment(CartoonCommentData cartoonCommentData, String string);
}
