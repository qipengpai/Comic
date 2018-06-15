package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CommentVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;

@Service
@Transactional
public interface CommentVeryOkServcie extends BaseService<CommentVeryOk>{

	List<CommentVeryOk> getUserCartoonCommentSetVeryOk(String id,
			String string);

	int getUserSetVseryOkCount(String id, UserEntity userEntity);

	List<CommentVeryOk> getUserCartoonCommentVeryOk(String id,
			UserEntity userEntity);

	int getUserVseryOkCount(String id, UserEntity userEntity);

	boolean addVeryOK(String string,
			String string2) throws Exception ;

	boolean addCommentVeryOk(CartoonSetCommentData cartoonSetCommentData);

	boolean deleteVeryOK(String string,
			String string2);

}
