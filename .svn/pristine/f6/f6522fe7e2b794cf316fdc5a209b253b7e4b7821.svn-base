package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonComment;
import com.qin.crxl.comic.entity.CartoonCommentVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;
import com.qin.crxl.comic.entity.vo.UserEntityReq;
import com.qin.crxl.comic.service.CartoonCommentService;
import com.qin.crxl.comic.service.CartoonCommentVeryOkService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtils;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

@Controller
public class CartoonCommentController {

	@Autowired
	private UserService userService;
	@Autowired
	private CartoonCommentService cartoonCommenService;
	@Autowired
	private CartoonCommentVeryOkService cartoonCommentVeryOkService;

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>查看评论> + + + -
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMCOMMENT, method = RequestMethod.POST)
	public Model getAllCartoonComment(CartoonCommentData cartoonCommentData)
			throws Exception {
		Model model = new Model();
		List<Hashtable<String, Object>> list2 = new ArrayList<Hashtable<String, Object>>();
		cartoonCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonCommentData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}

		int num = cartoonCommenService
				.getAllCartoonCommentCount(cartoonCommentData);
		if (num == 0) {
			return new Model(200, "查询信息数量异常");
		}
		List<CartoonComment> list = cartoonCommenService.getALLCartoonComment(
				cartoonCommentData.getCartoonId(),
				cartoonCommentData.getBestNew(),
				cartoonCommentData.getNowPage());
		if (!ParaClick.clickList(list)) {
			return new Model(200, "查询评论集异常");
		}
		for (CartoonComment cartoonComment2 : list) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			List<CartoonComment> cartoonComment = cartoonCommenService
					.getCartoonCommentTwo(cartoonComment2.getId(),
							cartoonComment2.getCartoonId());
			List<Object> list4 = new ArrayList<Object>();
			for (CartoonComment cartoonComment3 : cartoonComment) {
				Hashtable<String, Object> map2 = new Hashtable<String, Object>();
				UserEntity userentity = userService
						.getUserInfoById(cartoonComment3.getUserId());
				UserEntityReq userEntityReq = new UserEntityReq();
				userEntityReq.setHeadimgurl(userentity.getHeadimgurl());
				userEntityReq.setUsername(StringToInt.toString(userentity
						.getUsername()));
				userEntityReq.setUserId(userentity.getUserId());
				if (cartoonComment3.getCommentDate().length() >= 19) {
					cartoonComment3.setCommentDate(DateUtils
							.showTimeText(DateUtils.getDate(
									cartoonComment3.getCommentDate(),
									"yyyy-MM-dd HH:mm")));
					cartoonComment3.setCommentInfo(StringToInt
							.toString(cartoonComment3.getCommentInfo()));
				}
				map2.put("user", userEntityReq);
				map2.put("cartoonCommentson", cartoonComment3);
				list4.add(map2);
			}
			map.put("list", list4);
			UserEntity user = userService.getUserInfoById(cartoonComment2
					.getUserId());
			UserEntityReq userEntityReq = new UserEntityReq();
			if (!ParaClick.clickString(user.getHeadimgurl())) {
				userEntityReq.setHeadimgurl(user.getHeadimgurl());
			}
			if (!ParaClick.clickString(user.getUsername())) {
				userEntityReq.setUsername(StringToInt.toString(user
						.getUsername()));
			}
			userEntityReq.setUserId(user.getUserId());
			map.put("user", userEntityReq);
			List<CartoonCommentVeryOk> list3 = cartoonCommentVeryOkService
					.getUserCartoonCommentVeryOk(cartoonComment2.getId(),
							userEntity.getUserId());
			if (!ParaClick.clickList(list3)) {
				map.put("veryOk", 0);
			} else {
				map.put("veryOk", 1);
			}
			// list.get(i).setCommentDate(DateUtil.getDayDiff(list.get(i).getCommentDate().substring(0,
			// list.get(i).getCommentDate().lastIndexOf(" ")),DateUtil.getdate_yyyy_MM_dd())+" "+list.get(i).getCommentDate().substring(11,16));
			if (cartoonComment2.getCommentDate().length() >= 19) {
				cartoonComment2.setCommentDate(DateUtils.showTimeText(DateUtils
						.getDate(cartoonComment2.getCommentDate(),
								"yyyy-MM-dd HH:mm")));
				cartoonComment2.setCommentInfo(StringToInt
						.toString(cartoonComment2.getCommentInfo()));
			}
			map.put("cartoonComment", cartoonComment2);
			list2.add(map);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonCommentData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 漫畫>查看评论> 根據id查詢評論
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMCOMMENT_BYID, method = RequestMethod.POST)
	public Model getAllCartoonCommentById(CartoonCommentData cartoonCommentData)
			throws Exception {
		cartoonCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonCommentData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		UserEntityReq userEntityReq = new UserEntityReq();
		CartoonComment cartoonComment = cartoonCommenService
				.get(cartoonCommentData.getId());
		UserEntity userEntity2 = userService.getUserInfoById(cartoonComment
				.getUserId());
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		List<CartoonCommentVeryOk> list3 = cartoonCommentVeryOkService
				.getUserCartoonCommentVeryOk(cartoonComment.getId(),
						userEntity.getUserId());
		if (!ParaClick.clickList(list3)) {
			map.put("veryOk", 0);
		} else {
			map.put("veryOk", 1);
		}
		// 修改时间的格式
		cartoonComment.setCommentDate(DateUtils.showTimeText(DateUtils.getDate(
				cartoonComment.getCommentDate(), "yyyy-MM-dd HH:mm")));
		cartoonComment.setCommentInfo(StringToInt.toString(cartoonComment
				.getCommentInfo()));
		userEntityReq.setHeadimgurl(userEntity2.getHeadimgurl());
		userEntityReq.setUsername(StringToInt.toString(userEntity2
				.getUsername()));
		userEntityReq.setUserId(userEntity2.getUserId());
		map.put("cartoonSetComment", cartoonComment);
		map.put("userEntity", userEntityReq);
		return new Model(200, map);
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>发表评论> + + +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOOMCOMMENT, method = RequestMethod.POST)
	public Model addCartoonComment(CartoonCommentData cartoonCommentData)
			throws Exception {
		cartoonCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonCommentData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonCommentData.getCartoonId())) {
			return new Model(404, "漫画id为空");
		}
		if (ParaClick.clickString(cartoonCommentData.getCommentInfo())) {
			return new Model(404, "内容为空");
		}
		boolean flag = cartoonCommenService.addCartoonComment(
				cartoonCommentData, userEntity);
		if (!flag) {
			return new Model(500, "评论异常");
		}
		return new Model(200, "评论成功");
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>查看评论>查看评论的评论 +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMCOMMENTSET, method = RequestMethod.POST)
	public Model getAllCartoonCommentSetConmment(
			CartoonCommentData cartoonCommentData) throws Exception {
		Model model = new Model();
		cartoonCommentData.clickUser();
		UserEntity userEntity = userService.get(cartoonCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonCommentData.getNowPage())) {
			cartoonCommentData.setNowPage("1");
		}
		CartoonComment cartoonComment0 = cartoonCommenService
				.get(cartoonCommentData.getId());
		if (cartoonComment0 == null) {
			return new Model(500, "评论為空");
		}
		List<Object> list2 = new ArrayList<Object>();
		int num = cartoonCommenService.getCartoonComment2Count(
				cartoonCommentData.getId(), cartoonComment0.getCartoonId());
		if (num == 0) {
			return new Model(500, "暂无评论");
		}
		List<CartoonComment> list = cartoonCommenService
				.getALLCartoonCommentByCommentId(cartoonCommentData.getId(),
						cartoonCommentData.getNowPage(),
						cartoonComment0.getCartoonId());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		for (CartoonComment cartoonComment : list) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			if (cartoonComment.getCommentDate().length() >= 19) {
				cartoonComment.setCommentDate(DateUtils.showTimeText(DateUtils
						.getDate(cartoonComment.getCommentDate(),
								"yyyy-MM-dd HH:mm")));
				cartoonComment.setCommentInfo(StringToInt
						.toString(cartoonComment.getCommentInfo()));
			}
			map.put("cartoonComment", cartoonComment);
			UserEntity user = userService.getUserInfoById(cartoonComment
					.getUserId());
			UserEntityReq userEntityReq = new UserEntityReq();
			userEntityReq.setHeadimgurl(user.getHeadimgurl());
			userEntityReq.setUsername(StringToInt.toString(user.getUsername()));
			userEntityReq.setUserId(user.getUserId());
			map.put("user", userEntityReq);
			list2.add(map);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonCommentData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>发表评论>评论评论 + +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOOMCOMMENTSET, method = RequestMethod.POST)
	public Model addCartoonCommentSetConmment(HttpSession session,
			CartoonCommentData cartoonCommentData) throws Exception {
		cartoonCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonCommentData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		/*
		 * if (userEntity.getUserId() == cartoonCommentData.getUserId()) {
		 * return new Model(403, "不能给自己评论"); }
		 */
		if (ParaClick.clickString(cartoonCommentData.getId())) {
			return new Model(404, "评论id为空");
		}
		if (ParaClick.clickString(cartoonCommentData.getCommentInfo())) {
			return new Model(404, "内容为空");
		}
		CartoonComment cartoonComment = cartoonCommenService
				.get(cartoonCommentData.getId());
		if (cartoonComment == null) {
			return new Model(500, "评论為空");
		}
		boolean flag = cartoonCommenService.addCartoonCommentSetConmment(
				cartoonCommentData, userEntity, cartoonComment.getCartoonId());
		if (!flag) {
			return new Model(500, "评论异常");
		}
		return new Model(200, "评论成功");
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>发表评论>点赞评论 /取消点赞 + +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOOMCOMMENT_OK, method = RequestMethod.POST)
	public Model addCartoonCommentOkCount(HttpSession session,
			CartoonCommentData cartoonCommentData) throws Exception {
		cartoonCommentData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonCommentData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonCommentData.getId())) {
			return new Model(404, "评论id为空");
		}
		CartoonComment cartoonComment = cartoonCommenService
				.get(cartoonCommentData.getId());
		if (cartoonComment == null) {
			return new Model(500, "评论為空");
		}
		if (Integer.parseInt(cartoonCommentData.getVeryOk()) == 1) {
			boolean flag = cartoonCommentVeryOkService.deleteVeryOK(
					cartoonCommentData.getId(), userEntity.getUserId(),
					cartoonComment.getCartoonId());
			if (!flag) {
				return new Model(500, "取消点赞异常");
			}
		} else {
			boolean flag = cartoonCommentVeryOkService.addVeryOK(
					cartoonCommentData.getId(), userEntity.getUserId(),
					cartoonComment.getCartoonId());
			if (!flag) {
				return new Model(500, "点赞异常");
			}
		}
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		List<CartoonCommentVeryOk> list3 = cartoonCommentVeryOkService
				.getUserCartoonCommentVeryOk(cartoonCommentData.getId(),
						userEntity.getUserId());
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
		map.put("cartoonComment", cartoonComment);
		return new Model(200, map);
	}

}
