package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.MallCartoonOrder;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;

@Service
@Transactional
public interface MallCartoonOrderService extends BaseService<MallCartoonOrder>{

	boolean BuyThisCartoonSet(UserEntity userEntity,
			CartoonPhotoData cartoonPhotoData);

	MallCartoonOrder getHistory(String cartoonId, String id, String userId);

	boolean BuyThisCartoonSetByIos(String userId, String cartoonId, String id,
			int d);

	List<MallCartoonOrder> getByUser(String id, String userId);

}
