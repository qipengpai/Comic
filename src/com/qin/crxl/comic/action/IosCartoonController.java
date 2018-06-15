 package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonNew;
import com.qin.crxl.comic.service.CartoonAllTypeService;
import com.qin.crxl.comic.service.CartoonCommentService;
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
public class IosCartoonController {

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
	
	
	
	
	/**
	 * @author pp
	 * @throws Exception
	 * @date 2017年12月6日
	 * @Remarks 首页>最新
	 * @Tile={<h1></h1>
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_ALL_CARTOON, method = RequestMethod.POST)
	public Model getAllCartoonByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getNewCartoonCountIos(cartoonData,userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getNewCartoonByIos(cartoonData.getNowPage(),userEntity.getPlatformIndex());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		List<Object> list2 = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			List<CartoonAllType> cartoonAllType=cartoonAllTypeService.getByCartoonId(list.get(i).getId());
			List<Object> list3 = new ArrayList<Object>();
			for (int j = 0; j < cartoonAllType.size(); j++) {
				Hashtable<String, Object> map2 = new Hashtable<String, Object>();
				CartoonType cartoonType=cartoonTypeService.get(cartoonAllType.get(j).getCartoonTypeId());
				map2.put("carrtoonType", cartoonType);
				list3.add(map2);
			}
			CartoonNew cartoonNew=new CartoonNew();
			cartoonNew.setCartoonAuthor(list.get(i).getCartoonAuthor());
			cartoonNew.setCartoonAuthorPic(list.get(i).getCartoonAuthor());
			cartoonNew.setCartoonName(list.get(i).getCartoonName());
			if (list.get(i).getCommentCount()<10000) {
				cartoonNew.setCommentCount(list.get(i).getCommentCount()+"");
			}else{
				double n = (double)list.get(i).getCommentCount()/10000;
				String num2=n+"";
				cartoonNew.setCommentCount( num2.substring(0, num2.indexOf(".", 0) + 2)+"万");
			}
			if (list.get(i).getFollowCount()<10000) {
				cartoonNew.setFollowCount(list.get(i).getFollowCount()+"");
			}else{
				double n = (double)list.get(i).getFollowCount()/10000;
				String num2=n+"";
				cartoonNew.setFollowCount( num2.substring(0, num2.indexOf(".", 0) + 2)+"万");
			}
			if (list.get(i).getPlayCount()<10000) {
				cartoonNew.setPlayCount(list.get(i).getPlayCount()+"");
			}else{
				double n = (double)list.get(i).getPlayCount()/10000;
				String num2=n+"";
				cartoonNew.setPlayCount( num2.substring(0, num2.indexOf(".", 0) + 2)+"万");
			}
			cartoonNew.setId(list.get(i).getId());
			cartoonNew.setMainPhoto(list.get(i).getMainPhoto());
			cartoonNew.setUpdateTile(list.get(i).getUpdateTile());
			map.put("cartoon", cartoonNew);
			map.put("cartoonAllType",list3);
			list2.add(map); 
		}
	
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}

	/**
	 * @auther  pp
	 * @Remarks 发现>热门漫画>查看更多>全部
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_ALL_HOT_CARTOON_MORE, method = RequestMethod.POST)
	public Model getMoreAllCartoonByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		CartoonType cartoonType2=cartoonTypeService.get(cartoonData.getCartoonType());
		if (cartoonType2==null) {
			return new Model(200, "无匹配类型");
		}
		if (cartoonType2.getCartoonType().equals("全部")) {
			cartoonData.setCartoonType("");
		}
		List<Object> list2 = new ArrayList<Object>();
		int num = cartoonService.getALLCartoonCountByIos(cartoonData.getCartoonType(),cartoonData.getFirsrtType(),userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getALLCartoonByIos(cartoonData.getCartoonType(),cartoonData.getFirsrtType(),cartoonData.getNowPage(),userEntity.getPlatformIndex());
		if (!ParaClick.clickList(list)) {
			return new Model(200, "查询话集异常");
		}
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
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
	 * @Remarks 发现>热门漫画 >前12*+
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_ALL_HOT_CARTOON, method = RequestMethod.POST)
	public Model getThreeHOTCartoonByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getNewCartoonCountIos(cartoonData,userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getSixHotCartoonByIos(cartoonData.getNowPage(),userEntity.getPlatformIndex());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询异常");
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 12 - 1) / 12);
		model.setObj(list);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 发现>详情>猜你喜欢>
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_CARTOON_LOVE, method = RequestMethod.POST)
	public Model getCartoonByhobbyByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getALLCartoonbyTypeThreeCountByIos(cartoonData,userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getALLCartoonbyTypeThreeByIos(cartoonData,userEntity.getPlatformIndex());
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
	 * @Remarks 发现>热门漫画>查看更多>排行榜(热卖榜)
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_ALL_CARTOON_HOTSHOW_RANKING, method = RequestMethod.POST)
	public Model getAllCartoonByRMRankingListByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if(ParaClick.clickString(cartoonData.getNowPage())){
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getALLCartoonCountByRankingListByIos(cartoonData,userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getALLCartoonByRMRankingListByIos(cartoonData,userEntity.getPlatformIndex());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 12 - 1) / 12);
		model.setObj(list);
		return model;
	}

	/**
	 * pp
	 * @Remarks 发现>热门漫画>查看更多>总排行榜 + +(人气榜)
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_ALL_CARTOON_RANKING, method = RequestMethod.POST)
	public Model getAllCartoonByRankingListByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if(ParaClick.clickString(cartoonData.getNowPage())){
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getALLCartoonCountByRankingListByIos(cartoonData,userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getALLCartoonByRankingListByIos(cartoonData,userEntity.getPlatformIndex());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 12 - 1) / 12);
		model.setObj(list);
		return model;
	}
	/**
	 * @author pp
	 * @throws Exception
	 * @date 2017年12月6日
	 * @Remarks 最新
	 * @Tile={<h1></h1>
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_ALL_CARTOON_NEW, method = RequestMethod.POST)
	public Model getAllCartoonByIosNew(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				cartoonData.getUserId(), cartoonData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		Model model = new Model();
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		int num = cartoonService.getNewCartoonCountIos(cartoonData,userEntity.getPlatformIndex());
		if (num == 0) {
			return new Model(200, "未查询到信息");
		}
		List<Cartoon> list = cartoonService.getNewCartoonByIosNew(cartoonData.getNowPage(),userEntity.getPlatformIndex());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询话集异常");
		}
		List<Object> list2 = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
//			List<CartoonAllType> cartoonAllType=cartoonAllTypeService.getByCartoonId(list.get(i).getId());
//			List<Object> list3 = new ArrayList<Object>();
//			for (int j = 0; j < cartoonAllType.size(); j++) {
//				Hashtable<String, Object> map2 = new Hashtable<String, Object>();
//				CartoonType cartoonType=cartoonTypeService.get(cartoonAllType.get(j).getCartoonTypeId());
//				map2.put("carrtoonType", cartoonType);
//				list3.add(map2);
//			}
			CartoonNew cartoonNew=new CartoonNew();
			cartoonNew.setCartoonAuthor(list.get(i).getCartoonAuthor());
			cartoonNew.setCartoonAuthorPic(list.get(i).getCartoonAuthor());
			cartoonNew.setCartoonName(list.get(i).getCartoonName());
			if (list.get(i).getCommentCount()<10000) {
				cartoonNew.setCommentCount(list.get(i).getCommentCount()+"");
			}else{
				double n = (double)list.get(i).getCommentCount()/10000;
				String num2=n+"";
				cartoonNew.setCommentCount( num2.substring(0, num2.indexOf(".", 0) + 2)+"万");
			}
			if (list.get(i).getFollowCount()<10000) {
				cartoonNew.setFollowCount(list.get(i).getFollowCount()+"");
			}else{
				double n = (double)list.get(i).getFollowCount()/10000;
				String num2=n+"";
				cartoonNew.setFollowCount( num2.substring(0, num2.indexOf(".", 0) + 2)+"万");
			}
			if (list.get(i).getPlayCount()<10000) {
				cartoonNew.setPlayCount(list.get(i).getPlayCount()+"");
			}else{
				double n = (double)list.get(i).getPlayCount()/10000;
				String num2=n+"";
				cartoonNew.setPlayCount( num2.substring(0, num2.indexOf(".", 0) + 2)+"万");
			}
			cartoonNew.setId(list.get(i).getId());
			cartoonNew.setMainPhoto(list.get(i).getMainPhoto());
			cartoonNew.setUpdateTile(list.get(i).getUpdateTile());
			cartoonNew.setSmallPhoto(list.get(i).getSmallPhoto());
			map.put("cartoon", cartoonNew);
//			map.put("cartoonAllType",list3);
			list2.add(map);
		}
	
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}
}
