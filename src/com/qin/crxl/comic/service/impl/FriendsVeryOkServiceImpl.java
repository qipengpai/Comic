package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.FriendsVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.FriendsCommentService;
import com.qin.crxl.comic.service.FriendsVeryOkService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class FriendsVeryOkServiceImpl extends BaseServiceImpl<FriendsVeryOk>
		implements FriendsVeryOkService {
	@Autowired
	private FriendsCommentService friendsCommentService;
	@Override
	public boolean deleteByFriendsCircleVeryOkId(
			FriendsCircleData friendsCircleData) {
		// 删除该朋友圈的所有点赞记录
		boolean flag=false;
		try {
			List<FriendsVeryOk> list=getByFriendsCircleId(friendsCircleData.getId());
			if (!ParaClick.clickList(list)) {
				flag=true;
				return flag;
			}
			for (int i = 0; i < list.size(); i++) {
				FriendsVeryOk friendVeryOk=super.get(list.get(i).getId());
				if (friendVeryOk==null) {
					return flag;
				}
				super.delete(list.get(i).getId());
			}
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	private List<FriendsVeryOk> getByFriendsCircleId(String id) {
		// TODO Auto-generated method stub
		List<FriendsVeryOk> list2= SQL("SELECT * FROM FriendsVeryOk WHERE friendCircleId='"+id+"'",FriendsVeryOk.class);
		return list2;
	}

	@Override
	public boolean addVeryOK(String id,
			String userId) throws Exception {
		// 给朋友圈点赞 
		boolean flag=false;
		try {
			FriendsVeryOk friendsVeryOk=new FriendsVeryOk();
			friendsVeryOk.setUserId(userId);
			friendsVeryOk.setOkState(1);
			friendsVeryOk.setFriendCircleId(id);
			friendsVeryOk.setOkDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			save(friendsVeryOk);
		/*	boolean flag2=friendCircleService.addFriendVeryOkCount(friendsCircleData);
			if(!flag2){
				throw new BusinessException("增加點贊次數");
			}*/
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE FriendsCircle SET okCount=(okCount+1) WHERE id='"+id+"'");
		return flag;
	}

	@Override
//	@Cacheable(value="FriendsVeryOk",key="#id.concat(#userId)")
	public List<FriendsVeryOk> getUserFriendsCircleVeryOk(String id,
			String userId) {
		// 查看该用户是否给本条朋友圈点赞
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM FriendsVeryOk WHERE userId='"+userId+"' AND friendCircleId = '"+id+"' ");
		List<FriendsVeryOk> list=SQL(sb.toString(),FriendsVeryOk.class);
		return list;
	}

	@Override
//	@CacheEvict(value="FriendsVeryOk",key="#id.concat(#userId)")
	public boolean deleteVeryOK(String id,
			String userId) throws Exception {
		// 取消点赞
		boolean flag = false;
		try {
			List<FriendsVeryOk> list3 =getUserFriendsCircleVeryOk(id, userId);
			if (!ParaClick.clickList(list3)) {
				return flag;
			}
			for (int i = 0; i < list3.size(); i++) {
				super.delete(list3.get(i).getId());
			}
			//boolean flag2=friendsCommentService.subductionOkCount(friendsCircleData.getId());
//			boolean flag2=friendsCommentService.subductionOkCount(friendsCircleData.getId());
//			if(!flag2){
//				throw new BusinessException("减少点赞数点赞异常");
//			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE FriendsCircle SET okCount=(okCount-1) WHERE id='"+id+"'");
		return flag;
	}

}
