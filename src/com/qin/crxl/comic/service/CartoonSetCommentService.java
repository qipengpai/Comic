package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonSetComment;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;

@Service
@Transactional
public interface CartoonSetCommentService extends BaseService<CartoonSetComment>{

//  int getAllCartoonSetCommentCount(CartoonSetCommentData cartoonSetCommentData);

	int getAllCartoonSetCommentCount(String cartoonId, String cartoonSetId);
	
	int getCartoonSetComment2Count(String string, String string2);

//	List<CartoonSetComment> getALLCartoonSetComment(
//			CartoonSetCommentData cartoonSetCommentData);
	
	List<CartoonSetComment> getALLCartoonSetComment(String cartoonId,
			String cartoonSetId, String bestNew, String nowPage);
	
	boolean addCartoonSetComment(CartoonSetCommentData cartoonSetCommentData,
			UserEntity userEntity)throws Exception;

//	List<CartoonSetComment> getALLCartoonSetCommentByCommentId(
//			CartoonSetCommentData cartoonSetCommentData);

	boolean addCartoonCommentSetConmment(
			CartoonSetCommentData cartoonSetCommentData, String string, String string2);

	boolean addCartoonCommentSetConmment(
			CartoonSetCommentData cartoonSetCommentData);

	/*boolean addCartoonCommentSetConmmentCount(
			CartoonSetCommentData cartoonSetCommentData);
*/
	boolean addVeryOkCount(String id);

	boolean subductionOkCount(String id);

	List<CartoonSetComment> getCartoonSetCommentTwo(String id, String string);

	List<CartoonSetComment> getALLCartoonSetCommentByCommentId(String string,String id,
			String nowPage);

	



	
}
