package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.FriendsCircle;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;

@Service
@Transactional
public interface AdminFriendsCircleService extends BaseService<FriendsCircle>{

	//查询所有用户混圈
	List<Object[]> getAllFriendcircle(FriendsCircleData friendsCircleData);
	//查询混圈的数量
	int getAllFriendcircleNum(FriendsCircleData friendsCircleData);
	//修改用户混圈的状态
	boolean updateFriendcircleState(FriendsCircleData friendsCircleData);
	

	
}
