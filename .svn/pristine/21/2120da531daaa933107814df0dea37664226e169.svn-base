package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.FriendsComment;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.entity.vo.FriendsCommentData;

@Service
@Transactional
public interface FriendsCommentService extends BaseService<FriendsComment>{

	boolean deleteByFriendsCircleId(FriendsCircleData friendsCircleData);

	List<FriendsComment> getByFriendsCircleId();

	int getFriendsCommentCount(String string);

//	List<FriendsComment> getALLFriendsCommentByCommentId(
//			FriendsCommentData friendsCommentData);

	boolean addFriendCircleComment(String string,
			String string2, String string3)throws Exception ;

	boolean subductionOkCount(String id);

	List<FriendsComment> getALLFriendsCommentByCommentId(String id);

}
