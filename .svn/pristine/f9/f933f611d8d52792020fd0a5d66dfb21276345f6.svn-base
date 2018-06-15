package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonCommentVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;

@Transactional
@Service
public interface CartoonCommentVeryOkService extends BaseService<CartoonCommentVeryOk>{

	List<CartoonCommentVeryOk> getUserCartoonCommentVeryOk(String id,
			String string);

	int getUserVseryOkCount(String id);

	boolean addVeryOK(String string,
			String string2, String string3);

	boolean deleteVeryOK(String string,
			String string2, String string3);

}
