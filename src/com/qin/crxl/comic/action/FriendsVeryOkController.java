package com.qin.crxl.comic.action;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.FriendsCircle;
import com.qin.crxl.comic.entity.FriendsVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;
import com.qin.crxl.comic.service.FriendsCircleService;
import com.qin.crxl.comic.service.FriendsVeryOkService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class FriendsVeryOkController {

	@Autowired
	private FriendsVeryOkService friendsVeryOkService;
	@Autowired
	private UserService userService;
	@Autowired
	private FriendsCircleService friendCircleService;
	
	/**
	 * pp
	 * @Remarks app>混圈>点赞朋友圈 
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_FRIENDSCIRCLE_VERYOK, method = RequestMethod.POST)
	public Model addFriendsCommentOkCount(FriendsCircleData friendsCircleData) throws Exception {
		friendsCircleData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(friendsCircleData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(friendsCircleData.getId())) {
			return new Model(404, "评论id为空");
		}
		if (Integer.parseInt(friendsCircleData.getVeryOk()) == 1) {
			boolean flag = friendsVeryOkService.deleteVeryOK(
					friendsCircleData.getId(), userEntity.getUserId());
			if (!flag) {
				return new Model(500, "点赞异常");
			}
		}else{
			boolean flag = friendsVeryOkService.addVeryOK(
					friendsCircleData.getId(),userEntity.getUserId());
			if (!flag) {
				return new Model(500, "点赞异常");
			}
		}
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		FriendsCircle friendsCircle = friendCircleService
				.get(friendsCircleData.getId());
		if (friendsCircle == null) {
			return new Model(500, "朋友圈为空");
		}
		List<FriendsVeryOk> list3 = friendsVeryOkService
				.getUserFriendsCircleVeryOk(friendsCircleData.getId(), userEntity.getUserId());
		if (!ParaClick.clickList(list3)) {
			map.put("veryOk", 0);
		} else {
			map.put("veryOk", 1);
		}
		/*
		 * int
		 * count=cartoonCommentVeryOkService.getUserVseryOkCount(cartoonComment
		 * .getId()); cartoonComment.setOkCount(count);
		 */
		map.put("okCount", friendsCircle.getOkCount());
		return new Model(200, map);
	}
	
}
