package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.FriendsCircle;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.entity.vo.FriendsCommentData;
import com.qin.crxl.comic.entity.vo.UserEntityData;

@Service
@Transactional
public interface FriendsCircleService extends BaseService<FriendsCircle>{

	FriendsCircle userUpdateHeadPortrict(FriendsCircleData friendsCircleData);

	boolean deleteFriendsCircle(FriendsCircleData friendsCircleData,
			UserEntity userEntity);

	List<FriendsCircle> getAllFriendcircle(FriendsCircleData friendsCircleData);

	int getAllFriendcircleNum(FriendsCircleData friendsCircleData);

	List<FriendsCircle> getHotFriendcircle(FriendsCircleData friendsCircleData);

//	boolean addFriendCircleCommentNum(FriendsCommentData friendsCommentData);

//	boolean addFriendVeryOkCount(FriendsCircleData friendsCircleData);

//	int getMyAllFriendcircleNum(FriendsCircleData friendsCircleData, UserEntity userEntity);

	List<FriendsCircle> getMyAllFriendcircle(String string);

	int getMyAllFriendcircleNum(String userId);

//	FriendsCircle getById(String id);

	
	
}