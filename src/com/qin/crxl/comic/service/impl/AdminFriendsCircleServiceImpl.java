package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.FriendsCircle;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.service.AdminFriendsCircleService;
import com.qin.crxl.comic.service.FriendsCirclePhotoService;
import com.qin.crxl.comic.service.FriendsCommentService;
import com.qin.crxl.comic.service.FriendsVeryOkService;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class AdminFriendsCircleServiceImpl extends
		BaseServiceImpl<FriendsCircle> implements AdminFriendsCircleService {
	@Autowired
	private FriendsCirclePhotoService friendCirclePhotoService;
	@Autowired
	private FriendsCommentService friendCommentService;
	@Autowired
	private FriendsVeryOkService friendVeryOkService;


	@Override
	public int getAllFriendcircleNum(FriendsCircleData friendsCircleData) {
		// 查看广场朋友圈条数
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM FriendsCircle f,UserEntity u WHERE f.userId=u.userId");
		if (!ParaClick.clickString(friendsCircleData.getCondition())) {
			sb.append(" and (u.userId like '%"
					+ friendsCircleData.getCondition()
					+ "%' or u.openId like '%"
					+ friendsCircleData.getCondition()
					+ "%' or f.releaseInfo like '%"
					+ friendsCircleData.getCondition() + "%')");
		}
		if(!ParaClick.clickString(friendsCircleData.getDeleteState())){
			sb.append(" and f.deleteState="+friendsCircleData.getDeleteState());
		}
		if (!ParaClick.clickString(friendsCircleData.getStarTime())
				&& !ParaClick.clickString(friendsCircleData.getEndTime())) {
			sb.append(" and f.releaseDate BETWEEN '"
					+ friendsCircleData.getStarTime() + " 00:00:00' and '"
					+ friendsCircleData.getEndTime() + " 23:59:59'");
		}
		System.out.println(sb.toString());
		int num = super.gettotalpage(sb.toString());
		return num;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllFriendcircle(FriendsCircleData friendsCircleData) {
		// 查看广场朋友圈
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT f.id,u.userId,u.username,f.releaseInfo,f.commentCount,f.releaseDate,f.deleteState,f.Photo FROM FriendsCircle f,UserEntity u WHERE f.userId=u.userId");
		if (!ParaClick.clickString(friendsCircleData.getCondition())) {
			sb.append(" and (u.userId like '%"
					+ friendsCircleData.getCondition()
					+ "%' or u.username like '%"
					+ friendsCircleData.getCondition()
					+ "%' or f.releaseInfo like '%"
					+ friendsCircleData.getCondition() + "%')");
		}
		if(!ParaClick.clickString(friendsCircleData.getDeleteState())){
			sb.append(" and f.deleteState="+friendsCircleData.getDeleteState());
		}
		if (!ParaClick.clickString(friendsCircleData.getStarTime())
				&& !ParaClick.clickString(friendsCircleData.getEndTime())) {
			sb.append(" and f.releaseDate BETWEEN '"
					+ friendsCircleData.getStarTime() + " 00:00:00' and '"
					+ friendsCircleData.getEndTime() + " 23:59:59'");
		}
		sb.append(" ORDER BY f.deleteState DESC,f.releaseDate DESC LIMIT "
				+ (Integer.parseInt(friendsCircleData.getNowPage()) - 1)
				* Integer.parseInt(friendsCircleData.getPageNum()) + ","
				+ Integer.parseInt(friendsCircleData.getPageNum()));
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public boolean updateFriendcircleState(FriendsCircleData friendsCircleData) {
		FriendsCircle friendsCircle=null;
		try {
			friendsCircle=super.get(friendsCircleData.getId());
			if(!ParaClick.clickObj(friendsCircle)){
				return false;
			}
			if(ParaClick.clickString(friendsCircleData.getDeleteState())){
				return false;
			}
			friendsCircle.setDeleteState(Integer.parseInt(friendsCircleData.getDeleteState().trim()));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
