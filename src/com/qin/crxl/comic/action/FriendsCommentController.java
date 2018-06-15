package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.FriendsCircle;
import com.qin.crxl.comic.entity.FriendsComment;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCommentData;
import com.qin.crxl.comic.entity.vo.UserEntityReq;
import com.qin.crxl.comic.service.FriendsCircleService;
import com.qin.crxl.comic.service.FriendsCommentService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtils;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

@Controller
public class FriendsCommentController {
	
	@Autowired
	private FriendsCommentService friendsCommentService;
	@Autowired
	private UserService userService;
	@Autowired
	private FriendsCircleService friendCircleService;
	
	/**
	 * pp
	 * @Remarks app>混圈>查看评论
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_FRIENDS_CARTOONCOMMENTSET, method = RequestMethod.POST)
	public Model getAllCartoonCommentSetConmment(
			FriendsCommentData friendsCommentData) throws Exception {
		Model model = new Model();
		friendsCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(friendsCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
//		if (!ParaClick.clickString(friendsCommentData.getNowPage())) {
//			friendsCommentData.setNowPage("1");
//		}
	/*	FriendsCircle friendCircle=friendCircleService.get(friendsCommentData.getId());
		if (friendCircle==null) {
			return new Model(500, "查询信息数量异常");
		}
		UserEntity userEntity2 = userService.get(friendsCommentData.getUserId());*/
		List<Object> list2 = new ArrayList<Object>();
//		int num = friendsCommentService
//				.getFriendsCommentCount(friendsCommentData.getId());
//		if (num == 0) {
//			return new Model(200, "查询信息数量异常");
//		}
		List<FriendsComment> list = friendsCommentService
				.getALLFriendsCommentByCommentId(friendsCommentData.getId());
		if (!ParaClick.clickList(list)) {
			return new Model(200, "查询话集异常");
		}
		for (FriendsComment friendsComment : list) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			if (friendsComment.getCommentDate().length()>=19) {
				friendsComment.setCommentDate(DateUtils.showTimeText(DateUtils.getDate(friendsComment.getCommentDate(),"yyyy-MM-dd HH:mm")));
				friendsComment.setCommentInfo(StringToInt.toString(friendsComment.getCommentInfo()));
			}
			map.put("friendsComment", friendsComment);
			UserEntity user = userService.getUserInfoById(friendsComment.getUserId());
			UserEntityReq userEntityReq=new UserEntityReq();
			userEntityReq.setHeadimgurl(user.getHeadimgurl());
			userEntityReq.setUsername(StringToInt.toString(user.getUsername()));
			userEntityReq.setUserId(user.getUserId());
			map.put("user", userEntityReq);
			list2.add(map);
		}
		FriendsCircle friendsCircle=friendCircleService.get(list.get(0).getFriendCircleId());
		model.setError(200);
	//	model.setNowpage(Integer.parseInt(friendsCommentData.getNowPage()));
	//	model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		model.setSpare(friendsCircle.getCommentCount());
		return model;
	}

	/**
	 * pp
	 * @Remarks app>混圈>发表评论
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.RELEASE_FRIENDSCIRCLE_COMMENT, method = RequestMethod.POST)
	public Model addCartoonComment(FriendsCommentData friendsCommentData) throws Exception {
		friendsCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(friendsCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(friendsCommentData.getFriendCircleId())) {
			return new Model(404, "朋友圈id为空");
		}
		if (ParaClick.clickString(friendsCommentData.getCommentInfo())) {
			return new Model(404, "内容为空");
		}
		boolean flag = friendsCommentService.addFriendCircleComment(
				friendsCommentData.getFriendCircleId(),friendsCommentData.getCommentInfo(), userEntity.getUserId());
		if (!flag) {
			return new Model(500, "评论异常");
		}
		return new Model(200, "评论成功");
	}
}