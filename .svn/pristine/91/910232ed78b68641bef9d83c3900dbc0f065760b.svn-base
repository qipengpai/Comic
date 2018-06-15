package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.FriendsCirclePhoto;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.entity.vo.UserEntityData;

@Service
@Transactional
public interface FriendsCirclePhotoService extends BaseService<FriendsCirclePhoto>{

	boolean addFriendCirclePhoto(String string, String string2, FriendsCircleData friendsCircleData);

	List<FriendsCirclePhoto> getFriendsCirclePhotoByFriendsCircleId(String id);

	boolean deleteByPhotoId(String string);

	FriendsCirclePhoto getFriendCirclePhotoByUserAndFId(UserEntity userEntity3,
			String id);

}
