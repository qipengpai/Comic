package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;
import com.qin.crxl.comic.entity.vo.CartoonSetData;
@Service
@Transactional
public interface CartoonSetService  extends BaseService<CartoonSet> {

	List<CartoonSet> getByCartoonId( CartoonData cartoonData);

	int getPageNum(CartoonData cartoonData);

	CartoonSet getCarrtoonBySetId(CartoonSetData cartoonSetData);

	List<CartoonSet> getByCartoon(String id);

/*	boolean addCartoonSetCommentCount(
			CartoonSetCommentData cartoonSetCommentData);
*/
	boolean addVeryOKCount(CartoonSetData cartoonSetData);

	CartoonSet getBySort(int i, String string);

	List<CartoonSet> getByCartoonIdforFollow(String id);

	boolean addPlayCount(String string);

	boolean addPlayCount2(CartoonSet cartoonSet);

	boolean addPlayCount3(String cartoonSetId);


}
