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
import com.qin.crxl.comic.entity.CartoonSetComment;
import com.qin.crxl.comic.entity.CommentVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;
import com.qin.crxl.comic.entity.vo.UserEntityReq;
import com.qin.crxl.comic.service.CartoonSetCommentService;
import com.qin.crxl.comic.service.CartoonSetService;
import com.qin.crxl.comic.service.CommentVeryOkServcie;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtils;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

@Controller
public class CartoonSetCommentController {

	@Autowired
	private CartoonSetCommentService cartoonSetCommentService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentVeryOkServcie commentVeryOkServcie;
	@Autowired
	private CartoonSetService cartoonSetService;

	/**
	 * pp
	 * 
	 * @Remarks 每话>查看评论> + + +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMCOMMENT_SON, method = RequestMethod.POST)
	public Model getAllCartoonCommentSon(
			CartoonSetCommentData cartoonSetCommentData) throws Exception {
		Model model = new Model();
		List<Hashtable<String, Object>> list2 = new ArrayList<Hashtable<String, Object>>();
		cartoonSetCommentData.clickUser();
		UserEntity userEntity = userService
				.getUserInfoById(cartoonSetCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getNowPage())) {
			cartoonSetCommentData.setNowPage("1");
		}
		// cartoonSetId over by cartoonId
		int num = cartoonSetCommentService.getAllCartoonSetCommentCount(
				cartoonSetCommentData.getCartoonId(),
				cartoonSetCommentData.getCartoonSetId());
		if (num == 0) {
			return new Model(200, "信息数量為空");
		}
		List<CartoonSetComment> list = cartoonSetCommentService
				.getALLCartoonSetComment(cartoonSetCommentData.getCartoonId(),
						cartoonSetCommentData.getCartoonSetId(),
						cartoonSetCommentData.getBestNew(),
						cartoonSetCommentData.getNowPage());
		if (!ParaClick.clickList(list)) {
			return new Model(200, "查询评论集异常");
		}
		for (CartoonSetComment cartoonSetComment : list) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			List<CartoonSetComment> list4 = cartoonSetCommentService.getCartoonSetCommentTwo(cartoonSetComment.getCartoonSetId(),cartoonSetComment.getId());
			List<Object> list5 = new ArrayList<Object>();
			for (CartoonSetComment cartoonSetComment2 : list4) {
				Hashtable<String, Object> map2 = new Hashtable<String, Object>();
				UserEntity userentity = userService
						.getUserInfoById(cartoonSetComment2.getUserId());
				UserEntityReq userEntityReq = new UserEntityReq();
				userEntityReq.setHeadimgurl(userentity.getHeadimgurl());
				userEntityReq.setUsername(StringToInt.toString(userentity
						.getUsername()));
				userEntityReq.setUserId(userentity.getUserId());
				if (cartoonSetComment2.getCommentDate().length() >= 19) {
					cartoonSetComment2.setCommentDate(DateUtils
							.showTimeText(DateUtils.getDate(
									cartoonSetComment2.getCommentDate(),
									"yyyy-MM-dd HH:mm")));
					cartoonSetComment2.setCommentInfo(StringToInt
							.toString(cartoonSetComment2.getCommentInfo()));
				}
				map2.put("user", userEntityReq);
				map2.put("cartoonCommentson", cartoonSetComment2);
				list5.add(map2);
			}
			UserEntity user = userService.getUserInfoById(cartoonSetComment
					.getUserId());
			UserEntityReq userEntityReq = new UserEntityReq();
			userEntityReq.setHeadimgurl(user.getHeadimgurl());
			userEntityReq.setUsername(StringToInt.toString(user.getUsername()));
			userEntityReq.setUserId(user.getUserId());
			map.put("user", userEntityReq);
			List<CommentVeryOk> list3 = commentVeryOkServcie
					.getUserCartoonCommentSetVeryOk(cartoonSetComment.getId(),
							userEntity.getUserId());
			if (!ParaClick.clickList(list3)) {
				map.put("veryOk", 0);
			} else {
				map.put("veryOk", 1);
			}

			if (cartoonSetComment.getCommentDate().length() >= 19) {
				cartoonSetComment.setCommentDate(DateUtils
						.showTimeText(DateUtils.getDate(
								cartoonSetComment.getCommentDate(),
								"yyyy-MM-dd HH:mm")));
				cartoonSetComment.setCommentInfo(StringToInt
						.toString(cartoonSetComment.getCommentInfo()));
			}
			map.put("cartoonComment", cartoonSetComment);
			map.put("list", list5);
			list2.add(map);
		}
		/*
		 * if (Integer.parseInt(cartoonSetCommentData.getBastNew()) == 0) {
		 * Collections.sort(list2, new Comparator<Map<String, Object>>() {
		 * 
		 * @Override public int compare(Map<String, Object> o1, Map<String,
		 * Object> o2) { // 排序 CartoonSetComment ca = (CartoonSetComment) o1
		 * .get("cartoonSetComment"); CartoonSetComment ca2 =
		 * (CartoonSetComment) o2 .get("cartoonSetComment"); Integer tag1 = new
		 * Integer(ca.getOkCount()); Integer tag2 = new
		 * Integer(ca2.getOkCount()); return tag1.compareTo(tag2); } }); }
		 */
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonSetCommentData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话>查看评论> 根據id查詢評論
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMCOMMENT_SON_BYID, method = RequestMethod.POST)
	public Model getAllCartoonCommentSonById(
			CartoonSetCommentData cartoonSetCommentData) throws Exception {
		cartoonSetCommentData.clickUser();
		UserEntity userEntity = userService
				.getUserInfoById(cartoonSetCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		CartoonSetComment cartoonSetComment = cartoonSetCommentService
				.get(cartoonSetCommentData.getId());
		UserEntity userEntity2 = userService.getUserInfoById(cartoonSetComment
				.getUserId());
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		List<CommentVeryOk> list3 = commentVeryOkServcie
				.getUserCartoonCommentSetVeryOk(cartoonSetComment.getId(),
						userEntity.getUserId());
		if (!ParaClick.clickList(list3)) {
			map.put("veryOk", 0);
		} else {
			map.put("veryOk", 1);
		}
		if (cartoonSetComment.getCommentDate().length() >= 19) {
			cartoonSetComment.setCommentDate(DateUtils.showTimeText(DateUtils
					.getDate(cartoonSetComment.getCommentDate(),
							"yyyy-MM-dd HH:mm")));
			cartoonSetComment.setCommentInfo(StringToInt
					.toString(cartoonSetComment.getCommentInfo()));
		}
		UserEntityReq userEntityReq = new UserEntityReq();
		userEntityReq.setHeadimgurl(userEntity2.getHeadimgurl());
		userEntityReq.setUsername(StringToInt.toString(userEntity2
				.getUsername()));
		userEntityReq.setUserId(userEntity2.getUserId());
		map.put("cartoonSetComment", cartoonSetComment);
		map.put("userEntity", userEntityReq);
		return new Model(200, map);
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话>发表评论> + +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOOMCOMMENT_SON, method = RequestMethod.POST)
	public Model addCartoonComment(HttpSession session,
			CartoonSetCommentData cartoonSetCommentData) throws Exception {
		cartoonSetCommentData.clickUser();
		UserEntity userEntity = userService
				.getUserInfoById(cartoonSetCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getCartoonId())) {
			return new Model(404, "漫画id为空");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getCartoonSetId())) {
			return new Model(404, "每話id为空");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getCommentInfo())) {
			return new Model(404, "内容为空");
		}
		boolean flag = cartoonSetCommentService.addCartoonSetComment(
				cartoonSetCommentData, userEntity);
		if (!flag) {
			return new Model(500, "评论异常");
		}
		return new Model(200, "评论成功");
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话>查看评论>查看评论的评论 +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMCOMMENTSET_SON, method = RequestMethod.POST)
	public Model getAllCartoonCommentSetConmmentSon(
			CartoonSetCommentData cartoonSetCommentData) throws Exception {
		Model model = new Model();
		cartoonSetCommentData.clickUser();
		UserEntity userEntity = userService
				.getUserInfoById(cartoonSetCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		 CartoonSetComment cartoonSetComment3 = cartoonSetCommentService
		 .get(cartoonSetCommentData.getId());
		// UserEntity userEntity2 =
		// userService.get(cartoonSetComment.getUserId());
		List<Object> list2 = new ArrayList<Object>();
		int num = cartoonSetCommentService
				.getCartoonSetComment2Count(cartoonSetComment3.getCartoonSetId(),cartoonSetCommentData.getId());
		if (num == 0) {
			return new Model(200, "暂无评论信息");
		}
		List<CartoonSetComment> list = cartoonSetCommentService
				.getALLCartoonSetCommentByCommentId(cartoonSetComment3.getCartoonSetId(),
						cartoonSetCommentData.getId(),
						cartoonSetCommentData.getNowPage());
		if (!ParaClick.clickList(list)) {
			return new Model(200, "暂无评论信息");
		}
		for (CartoonSetComment cartoonSetComment : list) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			if (cartoonSetComment.getCommentDate().length() >= 19) {
				cartoonSetComment.setCommentDate(DateUtils
						.showTimeText(DateUtils.getDate(
								cartoonSetComment.getCommentDate(),
								"yyyy-MM-dd HH:mm")));
				cartoonSetComment.setCommentInfo(StringToInt
						.toString(cartoonSetComment.getCommentInfo()));
			}
			map.put("cartoonComment", cartoonSetComment);
			UserEntity user = userService.getUserInfoById(cartoonSetComment
					.getUserId());
			UserEntityReq userEntityReq = new UserEntityReq();
			userEntityReq.setHeadimgurl(user.getHeadimgurl());
			userEntityReq.setUsername(StringToInt.toString(user.getUsername()));
			userEntityReq.setUserId(user.getUserId());
			map.put("user", userEntityReq);
			list2.add(map);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonSetCommentData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话>评论>评论评论 + +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOOMCOMMENTSET_SON, method = RequestMethod.POST)
	public Model addCartoonCommentSetConmmentSon(HttpSession session,
			CartoonSetCommentData cartoonSetCommentData) throws Exception {
		cartoonSetCommentData.clickUser();
		UserEntity userEntity = userService
				.getUserInfoById(cartoonSetCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		// if (userEntity.getUserId() == cartoonSetCommentData.getUserId()) {
		// return new Model(403, "不能给自己评论");
		// }
		if (ParaClick.clickString(cartoonSetCommentData.getId())) {
			return new Model(404, "评论id为空");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getCommentInfo())) {
			return new Model(404, "内容为空");
		}
		CartoonSetComment cartoonSetComment = cartoonSetCommentService
				.get(cartoonSetCommentData.getId());
		if (cartoonSetComment == null) {
			return new Model(500, "评论為空");
		}
		boolean flag = cartoonSetCommentService.addCartoonCommentSetConmment(
				cartoonSetCommentData, userEntity.getUserId(),cartoonSetComment.getCartoonSetId());

		if (!flag) {
			return new Model(500, "评论异常");
		}
		return new Model(200, "评论成功");
	}

	/**
	 * pp
	 * 
	 * @Remarks 每话>评论>点赞评论 +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOOMCOMMENT_OK_SON, method = RequestMethod.POST)
	public Model addCartoonCommentOkCountSon(HttpSession session,
			CartoonSetCommentData cartoonSetCommentData) throws Exception {
		cartoonSetCommentData.clickUser();
		UserEntity userEntity = userService
				.getUserInfoById(cartoonSetCommentData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getId())) {
			return new Model(404, "评论id为空");
		}
		if (Integer.parseInt(cartoonSetCommentData.getVeryOk()) == 1) {
			boolean flag = commentVeryOkServcie.deleteVeryOK(
					cartoonSetCommentData.getId(), userEntity.getUserId());
			if (!flag) {
				return new Model(500, "点赞异常");
			}
		} else {
			boolean flag = commentVeryOkServcie.addVeryOK(
					cartoonSetCommentData.getId(), userEntity.getUserId());
			if (!flag) {
				return new Model(500, "点赞异常");
			}
		}
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		CartoonSetComment cartoonSetComment = cartoonSetCommentService
				.get(cartoonSetCommentData.getId());
		if (cartoonSetComment == null) {
			return new Model(500, "评论為空");
		}
		List<CommentVeryOk> list3 = commentVeryOkServcie
				.getUserCartoonCommentSetVeryOk(cartoonSetComment.getId(),
						userEntity.getUserId());
		if (!ParaClick.clickList(list3)) {
			map.put("veryOk", 0);
		} else {
			map.put("veryOk", 1);
		}
		/*
		 * int
		 * count=commentVeryOkServcie.getUserVseryOkCount(cartoonSetComment.getId
		 * (),userEntity); cartoonSetComment.setOkCount(count);
		 */

		map.put("cartoonComment", cartoonSetComment);
		return new Model(200, map);
	}
}
