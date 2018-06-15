package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.FriendsCirclePhoto;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.entity.vo.UserEntityData;
import com.qin.crxl.comic.service.FriendsCirclePhotoService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class FriendCirclesPhotoServiceImpl extends
		BaseServiceImpl<FriendsCirclePhoto> implements FriendsCirclePhotoService {

	@Override
	public boolean addFriendCirclePhoto(String string,
			String  id,FriendsCircleData friendsCircleData) {
		// 添加图片
		boolean flag = false;
		try {
			String[] pp =string.split(",");
			FriendsCirclePhoto friendCirclePhoto = new FriendsCirclePhoto();
			friendCirclePhoto.setFriendCircleId(id);
			friendCirclePhoto.setImplDate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			friendCirclePhoto.setSrc(pp[0]);
			friendCirclePhoto.setUserId(friendsCircleData.getUserId());
			friendCirclePhoto.setWidth(pp[1]);
			friendCirclePhoto.setHigh(pp[2]);
			save(friendCirclePhoto);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
//	@Cacheable(value="FriendsCirclePhoto",key="#id")
	public List<FriendsCirclePhoto> getFriendsCirclePhotoByFriendsCircleId(
			String id) {
		// 查询该条朋友圈素有图片
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM FriendsCirclePhoto WHERE friendCircleId='" + id
				+ "'");
		List<FriendsCirclePhoto> list = SQL("FCP"+id,3600,sb.toString(),
				FriendsCirclePhoto.class);
		return list;
	}

	@Override
//	@CacheEvict(value="FriendsCirclePhoto",key="#id",allEntries=false)
	public boolean deleteByPhotoId(String id) {
		// 删除该条朋友圈图片
		boolean flag = false;
		try {
			StringBuffer sb = new StringBuffer();
//			sb.append("SELECT * FROM FriendsCirclePhoto WHERE friendCircleId='"
//					+ friendsCircleData.getId()+ "'");
			List<FriendsCirclePhoto> list = getFriendsCirclePhotoByFriendsCircleId(id);
			if (!ParaClick.clickList(list)) {
				flag = true;
				return flag;
			}
			for (int i = 0; i < list.size(); i++) {
				FriendsCirclePhoto friendCirclePhoto = super.get(list.get(i).getId());
				if (friendCirclePhoto == null) {
					return flag;
				}
				super.delete(list.get(i).getId());
			}
			JedisUtil.batchDel("FCP"+id);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public FriendsCirclePhoto getFriendCirclePhotoByUserAndFId(
			UserEntity userEntity3, String id) {
		// 根據用戶和朋友圈id查看
		List<FriendsCirclePhoto> list=super.SQL("SELECT * FROM FriendsCirclePhoto WHERE userId='"+userEntity3.getUserId()+"'  AND friendCircleId'"+id+"'", FriendsCirclePhoto.class);
		if (ParaClick.clickList(list)) {
			return list.get(0);
		}
		return null;
	}

}
