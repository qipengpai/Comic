package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonComment;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;

@Service
@Transactional
public interface CartoonCommentService  extends BaseService<CartoonComment>{

	int getCartoonCommentCount(Cartoon cartoon);

//	List<CartoonComment> getALLCartoonComment(CartoonCommentData cartoonCommentData);
	
	int getAllCartoonCommentCount(CartoonCommentData cartoonCommentData);

	int getCartoonComment2Count(String id, String string);
	
	boolean addCartoonComment(CartoonCommentData cartoonCommentData,UserEntity userEntity)throws Exception ;

	boolean addCartoonCommentSetConmment(CartoonCommentData cartoonCommentData,
			UserEntity userEntity, String string);
	
	List<CartoonComment> getALLCartoonCommentByCommentId(
			String id,String nowPage, String string);

	List<CartoonComment> getCartoonCommentTwo(
			String string, String string2);

//	boolean addCartoonCommentSetConmmentCount(
//			CartoonCommentData cartoonCommentData);

	boolean addOkCount(String id);

	boolean subductionOkCount(String id);

	List<CartoonComment> getALLCartoonComment(String cartoonId, String bestNew,
			String nowPage);



}
