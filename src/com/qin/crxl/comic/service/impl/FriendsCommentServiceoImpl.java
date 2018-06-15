package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.FriendsComment;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.entity.vo.FriendsCommentData;
import com.qin.crxl.comic.service.FriendsCommentService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;


@Service
public class FriendsCommentServiceoImpl extends BaseServiceImpl<FriendsComment>
		implements FriendsCommentService {
/*	@Autowired
	private FriendsCircleService friendCircleService;
	*/
	
	@Override
	public boolean deleteByFriendsCircleId(FriendsCircleData friendsCircleData) {
		// 删除朋友圈下面的评论
		boolean flag=true;
		try {
			List<FriendsComment> list=SQL("SELECT * FROM FriendsComment WHERE friendCircleId ='"+friendsCircleData.getId()+"'",FriendsComment.class);
			if (!ParaClick.clickList(list)) {
				flag= true;
				return flag;
			}
			for (int i = 0; i < list.size(); i++) {
				FriendsComment friendComment=super.get(list.get(i).getId());
				if (friendComment==null) {
					return flag;
				}
				super.delete(list.get(i).getId());
			}
			flag= true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public List<FriendsComment> getByFriendsCircleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
//	@Cacheable(value="FriendsComment",key="#root.methodName.concat(#id)")
	public int getFriendsCommentCount(String id) {
		// 根据朋友圈id查看所有评论的总和
		StringBuffer sb=new  StringBuffer();
		sb.append("SELECT COUNT(*) FROM FriendsComment WHERE friendCircleId='"+id+"'");
		int num= super.gettotalpage(sb.toString());
		return num;
	}

	@Override
//	@Cacheable(value="FriendsComment",key="#id")
	public List<FriendsComment> getALLFriendsCommentByCommentId(String id) {
		// 根据朋友圈id查看所有评论
		StringBuffer sb=new  StringBuffer();
		sb.append("SELECT * FROM FriendsComment WHERE friendCircleId='"+id+"'");
		sb.append("  ORDER BY commentDate DESC  ");
		List<FriendsComment> list=SQL(sb.toString(),FriendsComment.class);
		return list;
	}

	@Override
//	@CacheEvict(value="FriendsComment",key="#friendCircleId",allEntries=false)
	public boolean addFriendCircleComment(String friendCircleId,String commentInfo,
			String userId) throws Exception {
		// 评论朋友圈
		boolean flag=false;
		try {
			FriendsComment friendsComment=new FriendsComment();
			friendsComment.setFriendCircleId(friendCircleId);
			friendsComment.setCommentDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			friendsComment.setCommentInfo(StringToInt.toInt(commentInfo));
			friendsComment.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			friendsComment.setUserId(userId);
			save(friendsComment);
			/*boolean flag2 =friendCircleService.addFriendCircleCommentNum(friendsCommentData);
			if (!flag2) {
				throw new BusinessException("评论异常");
			}*/
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE FriendsCircle SET commentCount=(commentCount+1) WHERE id='"+friendCircleId+"'");
		return flag;
	}

	@Override
	public boolean subductionOkCount(String id) {
		//	取消点赞
		boolean flag=false;
		try {
			FriendsComment friendsComment=super.get(id);
			friendsComment.setOkCount(friendsComment.getOkCount()-1);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

}
