package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllModel;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.entity.CartoonModel;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.FollowCartoon;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonAllModelData;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonHead;
import com.qin.crxl.comic.entity.vo.CartoonNew;
import com.qin.crxl.comic.service.CartoonAllModelService;
import com.qin.crxl.comic.service.CartoonAllTypeService;
import com.qin.crxl.comic.service.CartoonCommentService;
import com.qin.crxl.comic.service.CartoonModelService;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.service.CartoonSetService;
import com.qin.crxl.comic.service.CartoonTypeService;
import com.qin.crxl.comic.service.FollowCartoonService;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class CartoonController {

	@Autowired
	private CartoonService cartoonService;
	@Autowired
	private CartoonSetService cartoonSetService;
	@Autowired
	private UserService userService;
	@Autowired
	private HistoryRecordService historyRecordService;
	@Autowired
	private FollowCartoonService followCartoonService;
	@Autowired
	private CartoonCommentService cartoonCommentService;
	@Autowired
	private CartoonTypeService cartoonTypeService;
	@Autowired
	private CartoonAllTypeService cartoonAllTypeService;
	@Autowired
	private CartoonModelService cartoonModelService;
	@Autowired
	private CartoonAllModelService cartoonAllModelService;

	/**
	 * @author pp
	 * @throws Exception
	 * @date 2017年12月6日
	 * @Remarks 首页>最新
	 * @Tile={<h1></h1>
	 */

	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON, method = RequestMethod.POST)
	public Model getAllCartoon(CartoonData cartoonData) throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		Model model = new Model();
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getNewCartoonCount(cartoonData);
		if (num == 0) {
			return new Model(500, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getNewCartoon(cartoonData
				.getNowPage());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		List<Object> list2 = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			List<CartoonAllType> cartoonAllType = cartoonAllTypeService
					.getByCartoonId(list.get(i).getId());
			List<Object> list3 = new ArrayList<Object>();
			for (int j = 0; j < cartoonAllType.size(); j++) {
				Hashtable<String, Object> map2 = new Hashtable<String, Object>();
				CartoonType cartoonType = cartoonTypeService.get(cartoonAllType
						.get(j).getCartoonTypeId());
				map2.put("carrtoonType", cartoonType);
				list3.add(map2);
			}
			/* 查看关注人数 */
			// int followCartoon = followCartoonService
			// .getFollowCount(list.get(i).getId());
			// list.get(i).setFollowCount(followCartoon * 72);
			// int historyRecord =
			// historyRecordService.getWatchCount(list.get(i));
			// list.get(i).setPlayCount(historyRecord * 83);
			// int cartoonComment = cartoonCommentService
			// .getCartoonCommentCount(list.get(i));
			// list.get(i).setCommentCount(cartoonComment * 47);
			CartoonNew cartoonNew = new CartoonNew();
			cartoonNew.setCartoonAuthor(list.get(i).getCartoonAuthor());
			cartoonNew.setCartoonAuthorPic(list.get(i).getCartoonAuthor());
			cartoonNew.setCartoonName(list.get(i).getCartoonName());
			if (list.get(i).getCommentCount() < 10000) {
				cartoonNew.setCommentCount(list.get(i).getCommentCount() + "");
			} else {
				double n = (double) list.get(i).getCommentCount() / 10000;
				String num2 = n + "";
				cartoonNew.setCommentCount(num2.substring(0,
						num2.indexOf(".", 0) + 2)
						+ "万");
			}
			if (list.get(i).getFollowCount() < 10000) {
				cartoonNew.setFollowCount(list.get(i).getFollowCount() + "");
			} else {
				double n = (double) list.get(i).getFollowCount() / 10000;
				String num2 = n + "";
				cartoonNew.setFollowCount(num2.substring(0,
						num2.indexOf(".", 0) + 2)
						+ "万");
			}
			if (list.get(i).getPlayCount() < 10000) {
				cartoonNew.setPlayCount(list.get(i).getPlayCount() + "");
			} else {
				double n = (double) list.get(i).getPlayCount() / 10000;
				String num2 = n + "";
				cartoonNew.setPlayCount(num2.substring(0,
						num2.indexOf(".", 0) + 2) + "万");
			}
			cartoonNew.setId(list.get(i).getId());
			cartoonNew.setMainPhoto(list.get(i).getMainPhoto());
			cartoonNew.setUpdateTile(list.get(i).getUpdateTile());
			map.put("cartoon", cartoonNew);
			map.put("cartoonAllType", list3);
			list2.add(map);
		}

		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 5 - 1) / 5);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>热门漫画 >前三 *+
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_HOT_CARTOON, method = RequestMethod.POST)
	public Model getThreeHOTCartoon(CartoonData cartoonData) throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		List<Cartoon> list = cartoonService.getSixHotCartoon();
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询异常");
		}
		/*
		 * List<Object> list2 = new ArrayList<Object>(); for (int i = 0; i <
		 * list.size(); i++) { Map<String, Object> map = new HashMap<String,
		 * Object>(); List<CartoonSet>
		 * cartoonSet=cartoonSetService.getByCartoon(list.get(i).getId());
		 * map.put("cartoonSet", cartoonSet); map.put("cartoon", list);
		 * list2.add(map); }
		 */
		return new Model(200, list);
	}

	/**
	 * @auther pp
	 * @Remarks 发现>热门漫画>查看更多>全部
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_HOT_CARTOON_MORE, method = RequestMethod.POST)
	public Model getMoreAllCartoon(CartoonData cartoonData) throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		CartoonType cartoonType2 = cartoonTypeService.get(cartoonData
				.getCartoonType());
		if (cartoonType2 == null) {
			return new Model(500, "无匹配类型");
		}
		if (cartoonType2.getCartoonType().equals("全部")) {
			cartoonData.setCartoonType("");
		}
		List<Object> list2 = new ArrayList<Object>();
		int num = cartoonService.getALLCartoonCount(
				cartoonData.getCartoonType(), cartoonData.getFirsrtType());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getALLCartoon(
				cartoonData.getCartoonType(), cartoonData.getFirsrtType(),
				cartoonData.getNowPage());
		if (!ParaClick.clickList(list)) {
			return new Model(200, "查询话集异常");
		}
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			// List<Object> list3 = new ArrayList<Object>();
			// List<CartoonAllType>
			// cartoonAllType=cartoonAllTypeService.getByCartoonId(list.get(i).getId());
			// for (int j = 0; j < cartoonAllType.size(); j++) {
			// Hashtable<String, Object> map2 = new Hashtable<String, Object>();
			// CartoonType
			// cartoonType=cartoonTypeService.get(cartoonAllType.get(j).getCartoonTypeId());
			// map2.put("carrtoonType", cartoonType);
			// list3.add(map2);
			// }
			// map.put("cartoonAllType", list3);
			map.put("cartoon", list.get(i));
			list2.add(map);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 12 - 1) / 12);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>热门漫画>查看更多>热门 + + //备用
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_CARTOON_MORE_HOT, method = RequestMethod.POST)
	public Model getCartoonByhot(CartoonData cartoonData) throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		List<Object> list2 = new ArrayList<Object>();
		int num = cartoonService.getALLHotCartoonCount(cartoonData);
		if (num == 0) {
			return new Model(500, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getALLHotCartoon(cartoonData);
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			List<Object> list3 = new ArrayList<Object>();
			List<CartoonAllType> cartoonAllType = cartoonAllTypeService
					.getByCartoonId(list.get(i).getId());
			for (int j = 0; j < cartoonAllType.size(); j++) {
				Hashtable<String, Object> map2 = new Hashtable<String, Object>();
				CartoonType cartoonType = cartoonTypeService.get(cartoonAllType
						.get(j).getCartoonTypeId());
				map2.put("carrtoonType", cartoonType);
				list3.add(map2);
			}
			map.put("cartoonAllType", list3);
			map.put("cartoon", list.get(i));
			list2.add(map);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>热门漫画>查看更多>分类查询
	 * @throws Exception
	 * */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = ActionUrl.GET_CARTOON_MORE_BYTYPE, method =
	 * RequestMethod.POST) public Model getCartoonById(HttpSession session,
	 * CartoonData cartoonData) throws Exception { Model model = new Model();
	 * cartoonData.clickUser(); UserEntity userEntity =
	 * userService.get(cartoonData.getUserId()); if (userEntity == null) {
	 * return new Model(404, "无用户"); } List<Object> list2 = new
	 * ArrayList<Object>(); int followCartoon = 0; int historyRecord = 0; int
	 * cartoonComment = 0; int num =
	 * cartoonService.getALLCartoonbyTypeCount(cartoonData); if (num == 0) {
	 * return new Model(500, "未查询到信息"); } List<Cartoon> list =
	 * cartoonService.getALLCartoonbyType(cartoonData); if
	 * (!ParaClick.clickList(list)) { return new Model(500, "查询话集异常"); } for
	 * (int i = 0; i < list.size(); i++) { Map<String, Object> map = new
	 * HashMap<String, Object>(); String pp = list.get(i).getCartoonType();
	 * 
	 * String[] aa = pp.split("|"); map.put("carrtoonType", aa);
	 * 
	 * 
	 * 查看关注人数 followCartoon =
	 * followCartoonService.getFollowCount(list.get(i).getId());
	 * list.get(i).setFollowCount(followCartoon * 72); historyRecord =
	 * historyRecordService.getWatchCount(list.get(i));
	 * list.get(i).setPlayCount(historyRecord * 83); cartoonComment =
	 * cartoonCommentService.getCartoonCommentCount(list .get(i));
	 * list.get(i).setCommentCount(cartoonComment * 47); map.put("carrtoon",
	 * list); list2.add(map); } model.setError(200);
	 * model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
	 * model.setTotalpage((num + 10 - 1) / 10); model.setObj(list2); return
	 * model; }
	 */

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>漫画表头
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_CARTOON_HEAD_BYID, method = RequestMethod.POST)
	public Model getCartoonHeadById(CartoonData cartoonData) throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		Cartoon cartoon = cartoonService.get(cartoonData.getId());
		if (cartoon == null) {
			return new Model(404, "无此漫画");
		}
		List<Object> list3 = new ArrayList<Object>();
		List<CartoonAllType> cartoonAllType = cartoonAllTypeService
				.getByCartoonId(cartoon.getId());
		for (CartoonAllType cartoonAllType2 : cartoonAllType) {
			Hashtable<String, Object> map2 = new Hashtable<String, Object>();
			CartoonType cartoonType = cartoonTypeService.get(cartoonAllType2
					.getCartoonTypeId());
			map2.put("carrtoonType", cartoonType);
			list3.add(map2);
		}
		map.put("cartoonAllType", list3);
		CartoonHead cartoonHead = new CartoonHead();
		cartoonHead.setCartoonAuthor(cartoon.getCartoonAuthor());
		cartoonHead.setCartoonAuthorPic(cartoon.getCartoonAuthorPic());
		cartoonHead.setCartoonName(cartoon.getCartoonName());
		cartoonHead.setId(cartoon.getId());
		cartoonHead.setIntroduction(cartoon.getIntroduction());
		cartoonHead.setSerialState(cartoon.getSerialState());
		cartoonHead.setUpdateTile(cartoon.getUpdateTile());
		cartoonHead.setIntroduc(cartoon.getIntroduc());
		cartoonHead.setSharePhoto(cartoon.getSharePhoto());
		if (cartoon.getCommentCount() < 10000) {
			cartoonHead.setCommentCount(cartoon.getCommentCount() + "");
		} else {
			double n = (double) cartoon.getCommentCount() / 10000;
			String num2 = n + "";
			cartoonHead.setCommentCount(num2.substring(0,
					num2.indexOf(".", 0) + 2) + "万");
		}
		if (cartoon.getFollowCount() < 10000) {
			cartoonHead.setFollowCount(cartoon.getFollowCount() + "");
		} else {
			double n = (double) cartoon.getFollowCount() / 10000;
			String num2 = n + "";
			cartoonHead.setFollowCount(num2.substring(0,
					num2.indexOf(".", 0) + 2) + "万");
		}
		if (cartoon.getPlayCount() < 10000) {
			cartoonHead.setPlayCount(cartoon.getPlayCount() + "");
		} else {
			double n = (double) cartoon.getPlayCount() / 10000;
			String num2 = n + "";
			cartoonHead
					.setPlayCount(num2.substring(0, num2.indexOf(".", 0) + 2)
							+ "万");
		}
		map.put("cartoon", cartoonHead);
		// 查看用户是否关注该剧
		List<FollowCartoon> followCartoon2 = followCartoonService
				.getFollowByid(cartoonData.getId(), userEntity.getUserId());
		if (!ParaClick.clickList(followCartoon2)) {
			map.put("followCartoon", 0);
		} else {
			map.put("followCartoon", 1);
		}
		return new Model(200, map);
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>猜你喜欢>
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_CARTOON_LOVE, method = RequestMethod.POST)
	public Model getCartoonByhobby(CartoonData cartoonData) throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getALLCartoonbyTypeThreeCount(cartoonData);
		if (num == 0) {
			return new Model(406, "未查询到信息");
		}
		List<Cartoon> list = cartoonService
				.getALLCartoonbyTypeThree(cartoonData);
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 3 - 1) / 3);
		model.setObj(list);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>热门漫画>查看更多>排行榜(热卖榜)
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON_HOTSHOW_RANKING, method = RequestMethod.POST)
	public Model getAllCartoonByRMRankingList(CartoonData cartoonData)
			throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getALLCartoonCountByRankingList(cartoonData);
		if (num == 0) {
			return new Model(500, "未查询到信息");
		}
		List<Cartoon> list = cartoonService
				.getALLCartoonByRMRankingList(cartoonData);
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>热门漫画>查看更多>总排行榜 + +(人气榜)
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON_RANKING, method = RequestMethod.POST)
	public Model getAllCartoonByRankingList(CartoonData cartoonData)
			throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		// List<Hashtable<String,Object>> list2 = new
		// ArrayList<Hashtable<String,Object>>();
		int num = cartoonService.getALLCartoonCountByRankingList(cartoonData);
		if (num == 0) {
			return new Model(500, "未查询到信息");
		}
		List<Cartoon> list = cartoonService
				.getALLCartoonByRankingList(cartoonData);
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		// for (int i = 0; i < list.size(); i++) {
		// Hashtable<String, Object> map = new Hashtable<String, Object>();
		// List<Object> list3 = new ArrayList<Object>();
		// List<CartoonAllType>
		// cartoonAllType=cartoonAllTypeService.getByCartoonId(list.get(i).getId());
		// for (int j = 0; j < cartoonAllType.size(); j++) {
		// Hashtable<String, Object> map2 = new Hashtable<String, Object>();
		// CartoonType
		// cartoonType=cartoonTypeService.get(cartoonAllType.get(j).getCartoonTypeId());
		// map2.put("carrtoonType", cartoonType);
		// list3.add(map2);
		// }
		// map.put("cartoonAllType", list3);
		// map.put("cartoon", list.get(i));
		// list2.add(map);
		// }
		/*
		 * // 进行降序排列 Collections.sort(list2, new Comparator<Map<String,Object>>
		 * (){
		 * 
		 * @Override public int compare(Map<String, Object> o1, Map<String,
		 * Object> o2) { // 排序 Cartoon ca=(Cartoon) o1.get("carrtoon"); Cartoon
		 * ca2=(Cartoon) o2.get("carrtoon"); Integer
		 * tag1=(Integer)ca.getPlayCount(); Integer
		 * tag2=(Integer)ca2.getPlayCount(); return tag1.compareTo(tag2); }
		 * 
		 * });
		 */
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>热门漫画>查看更多>排行榜前三 +
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON_RANKING_THREE, method = RequestMethod.POST)
	public Model getAllCartoonByThree(CartoonData cartoonData) throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		List<Cartoon> list = cartoonService
				.getALLCartoonByRankingListThree(cartoonData);
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			// List<Object> list3 = new ArrayList<Object>();
			// List<CartoonAllType>
			// cartoonAllType=cartoonAllTypeService.getByCartoonId(list.get(i).getId());
			// for (int j = 0; j < cartoonAllType.size(); j++) {
			// Hashtable<String, Object> map2 = new Hashtable<String, Object>();
			// CartoonType
			// cartoonType=cartoonTypeService.get(cartoonAllType.get(j).getCartoonTypeId());
			// map2.put("carrtoonType", cartoonType);
			// list3.add(map2);
			// }
			// map.put("cartoonAllType", list3);
			map.put("cartoon", list.get(i));
			list2.add(map);
		}
		/*
		 * // 进行降序排列 Collections.sort(list2, new Comparator<Map<String,Object>>
		 * (){
		 * 
		 * @Override public int compare(Map<String, Object> o1, Map<String,
		 * Object> o2) { // 排序 Cartoon ca=(Cartoon) o1.get("carrtoon"); Cartoon
		 * ca2=(Cartoon) o2.get("carrtoon"); Integer
		 * tag1=(Integer)ca.getPlayCount(); Integer
		 * tag2=(Integer)ca2.getPlayCount(); return tag1.compareTo(tag2); }
		 * 
		 * }); List<Map<String,Object>> lis我t5 = new
		 * ArrayList<Map<String,Object>>(); list5.add(list2.get(0));
		 * list5.add(list2.get(1)); list5.add(list2.get(2));
		 */
		model.setError(200);
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 更新数据>全部
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.BACKSUPS_CARTOON_DATA, method = RequestMethod.POST)
	public Model getMoreAllCartoon() throws Exception {
		boolean flag = cartoonService.backupsDatabases();
		if (!flag) {
			System.out.println("controller更新数据失败");
			return new Model(500, "500");
		}
		System.out.println("controller更新数据成功");
		return new Model(200, "200");
	}

	/**
	 * pp
	 * 
	 * @Remarks 根據模塊查詢漫畫列表 排序
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOON_BY_MODEL, method = RequestMethod.POST)
	public Model selectCartoonByModel(CartoonAllModelData cartoonAllModelData)
			throws Exception {
		Model model = new Model();
		if (ParaClick.clickString(cartoonAllModelData.getNowpage())) {
			cartoonAllModelData.setNowpage("1");
		}
		int num = cartoonAllModelService
				.getThisCartoonModelBycartoonModelIdNum(cartoonAllModelData);
		List<CartoonAllModel> list2 = cartoonAllModelService
				.getThisCartoonModelBycartoonModelId(
						cartoonAllModelData.getCartoonModelId(),
						cartoonAllModelData.getNowpage());
		if (!ParaClick.clickList(list2)) {
			return new Model(500, "该模块暂无漫画");
		}
		List<Object> list = new ArrayList<Object>();
		for (CartoonAllModel cartoonAllModel : list2) {
			Hashtable<String, Object> cartoonAndType = new Hashtable<String, Object>();
			cartoonAndType.put("cartoon",
					cartoonService.get(cartoonAllModel.getCartoonId()));
			cartoonAndType.put("cartoonModel", cartoonAllModel);
			// 查看漫画所属所有模块
			List<CartoonAllModel> cartoonAllModel2 = cartoonAllModelService
					.getThisCartoonModel(cartoonAllModel.getCartoonId());
			List<String> cartoonModelStr = new ArrayList<String>();
			if (ParaClick.clickList(cartoonAllModel2)) {
				for (CartoonAllModel cartoonAllModel3 : cartoonAllModel2) {
					CartoonModel cartoonModel = cartoonModelService
							.get(cartoonAllModel3.getCartoonModelId());
					cartoonModelStr.add(cartoonModel.getModel());
				}
				// 将此漫画的所有模塊存入用于返回
				cartoonAndType.put("cartoonAllModel", cartoonModelStr);
			} else {
				cartoonAndType.put("cartoonAllModel", "");
			}
			list.add(cartoonAndType);
		}

		model.setError(200);
		model.setMsg("操作成功");
		model.setNowpage(Integer.parseInt(cartoonAllModelData.getNowpage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setTotalNum(num);
		model.setObj(list);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 模块查询漫画
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON_BYMODEL, method = RequestMethod.POST)
	public Model getAllCartoonByModel(CartoonData cartoonData) throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		List<CartoonModel> list2 = cartoonModelService.getAllCartoonModel();
		if (!ParaClick.clickList(list2)) {
			return new Model(500, "无數據");
		}
		List<Object> list = new ArrayList<Object>();
		for (CartoonModel cartoonModel : list2) {
			Hashtable<String, Object> table = new Hashtable<String, Object>();
			List<CartoonAllModel> cartoonAllModel = cartoonAllModelService
					.getCartoonBeforeSix(cartoonModel.getId());
			if (ParaClick.clickList(cartoonAllModel)) {
				table.put("CartoonModel", cartoonModel);
				List<CartoonNew> cartoonList = new ArrayList<CartoonNew>();
				for (CartoonAllModel cartoonAllModel2 : cartoonAllModel) {
					Cartoon cartoon = cartoonService.get(cartoonAllModel2
							.getCartoonId());
					if (cartoon == null) {
						return new Model(500, "无數據");
					}
					CartoonNew cartoonNew = new CartoonNew();
					cartoonNew.setMidelPhoto(cartoon.getMidelPhoto());
					cartoonNew.setId(cartoon.getId());
					cartoonNew.setCartoonName(cartoon.getCartoonName());
					cartoonList.add(cartoonNew);
				}
				table.put("Cartoon", cartoonList);
				list.add(table);
			}
		}
		model.setError(200);
		model.setMsg("操作成功");
		model.setObj(list);
		return model;

	}

	/**
	 * pp
	 * 
	 * @Remarks 模块查询漫画(更多)
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOON_BYMODEL_MORE, method = RequestMethod.POST)
	public Model getAllMoreCartoonByModel(CartoonData cartoonData)
			throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		List<CartoonNew> list = new ArrayList<CartoonNew>();
		int num = cartoonService
				.getCartoonMoreCount(cartoonData.getCartoonModelId());
		// 查看该模块更多漫画
		List<Cartoon> cartoonList = cartoonService
				.getCartoonMore(cartoonData.getCartoonModelId(),cartoonData.getNowPage());
		if (!ParaClick.clickList(cartoonList)) {
			return new Model(500, "无数据");
		}
		for (Cartoon cartoon : cartoonList) {
			CartoonNew cartoonNew = new CartoonNew();
			cartoonNew.setCartoonAuthor(cartoon.getCartoonAuthor());
			cartoonNew.setCartoonAuthorPic(cartoon.getCartoonAuthor());
			cartoonNew.setCartoonName(cartoon.getCartoonName());
			if (cartoon.getCommentCount() < 10000) {
				cartoonNew.setCommentCount(cartoon.getCommentCount() + "");
			} else {
				double n = (double) cartoon.getCommentCount() / 10000;
				String num2 = n + "";
				cartoonNew.setCommentCount(num2.substring(0,
						num2.indexOf(".", 0) + 2)
						+ "万");
			}
			if (cartoon.getFollowCount() < 10000) {
				cartoonNew.setFollowCount(cartoon.getFollowCount() + "");
			} else {
				double n = (double) cartoon.getFollowCount() / 10000;
				String num2 = n + "";
				cartoonNew.setFollowCount(num2.substring(0,
						num2.indexOf(".", 0) + 2)
						+ "万");
			}
			if (cartoon.getPlayCount() < 10000) {
				cartoonNew.setPlayCount(cartoon.getPlayCount() + "");
			} else {
				double n = (double) cartoon.getPlayCount() / 10000;
				String num2 = n + "";
				cartoonNew.setPlayCount(num2.substring(0,
						num2.indexOf(".", 0) + 2) + "万");
			}
			cartoonNew.setId(cartoon.getId());
			cartoonNew.setSmallPhoto(cartoon.getSmallPhoto());
			cartoonNew.setUpdateTile(cartoon.getUpdateTile());
			
			list.add(cartoonNew);
		}
		model.setError(200);
		model.setMsg("操作成功");
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setTotalNum(num);
		model.setObj(list);
		return model;

	}
	
	/**
	 * pp
	 * 
	 * @Remarks 免费漫画
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_FREE_CARTOON, method = RequestMethod.POST)
	public Model getAllFreeCartoon(CartoonData cartoonData)
			throws Exception {
		Model model = new Model();
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData
				.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		List<CartoonNew> list = new ArrayList<CartoonNew>();
		int num = cartoonService
				.getFreeCartoonMoreCount();
		// 查看所有免费漫画
		List<Cartoon> cartoonAll = cartoonService
				.getFreeCartoonMore(cartoonData.getNowPage());
		if (!ParaClick.clickList(cartoonAll)) {
			return new Model(500, "无数据");
		}
		for (Cartoon cartoon : cartoonAll) {
			CartoonNew cartoonNew = new CartoonNew();
			cartoonNew.setCartoonAuthor(cartoon.getCartoonAuthor());
			cartoonNew.setCartoonAuthorPic(cartoon.getCartoonAuthor());
			cartoonNew.setCartoonName(cartoon.getCartoonName());
			if (cartoon.getCommentCount() < 10000) {
				cartoonNew.setCommentCount(cartoon.getCommentCount() + "");
			} else {
				double n = (double) cartoon.getCommentCount() / 10000;
				String num2 = n + "";
				cartoonNew.setCommentCount(num2.substring(0,
						num2.indexOf(".", 0) + 2)
						+ "万");
			}
			if (cartoon.getFollowCount() < 10000) {
				cartoonNew.setFollowCount(cartoon.getFollowCount() + "");
			} else {
				double n = (double) cartoon.getFollowCount() / 10000;
				String num2 = n + "";
				cartoonNew.setFollowCount(num2.substring(0,
						num2.indexOf(".", 0) + 2)
						+ "万");
			}
			if (cartoon.getPlayCount() < 10000) {
				cartoonNew.setPlayCount(cartoon.getPlayCount() + "");
			} else {
				double n = (double) cartoon.getPlayCount() / 10000;
				String num2 = n + "";
				cartoonNew.setPlayCount(num2.substring(0,
						num2.indexOf(".", 0) + 2) + "万");
			}
			cartoonNew.setId(cartoon.getId());
			cartoonNew.setSmallPhoto(cartoon.getSmallPhoto());
			cartoonNew.setUpdateTile(cartoon.getUpdateTile());
			list.add(cartoonNew);
		}
		model.setError(200);
		model.setMsg("操作成功");
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setTotalNum(num);
		model.setObj(list);
		return model;

	}
}
