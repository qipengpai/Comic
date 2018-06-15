package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.FriendsCirclePhoto;
import com.qin.crxl.comic.service.AdminFriendsCirclePhotoService;

@Service
public class AdminFriendCirclesPhotoServiceImpl extends
		BaseServiceImpl<FriendsCirclePhoto> implements AdminFriendsCirclePhotoService {

	@Override
	public List<FriendsCirclePhoto> selectFriendsCirclePhoto(
			String friendCircleId) {
		List<FriendsCirclePhoto> sql = SQL("select * from FriendsCirclePhoto where friendCircleId='"+friendCircleId+"'",FriendsCirclePhoto.class);
		return sql;
	}


}
