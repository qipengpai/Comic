package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Banner;
import com.qin.crxl.comic.entity.Barrage;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.BannerData;
import com.qin.crxl.comic.entity.vo.BarrageData;
import com.qin.crxl.comic.service.BarrageService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class BarrageController {

	@Autowired
	private BarrageService barrageService;
	@Autowired
	private UserService userService;
	
	/**
	 * @author 10747
	 * @date 2017年12月8日
	 * @Remarks
	 * @Tile={<h1>comic增加弹幕 </h1>}
	 * @describe={
	 * <p>添加方法：</p >
	 * <ul>
	 * <li>...</li>
	 * <li>...</li>
	 * </ul>}
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_BARRAGE, method = RequestMethod.POST)
	public Model addBarrage(BarrageData bannerData) throws Exception {
		bannerData.clickUser();
		UserEntity userEntity=userService.getUserInfoById(bannerData.getUserId());
		if (userEntity==null) {
			return new Model(404,"无用户");
		}
		if (ParaClick.clickString(bannerData.getContentInfo())) {
			return new Model(500,"无内容");
		}
		Barrage barrage = barrageService.addComicBarrage(bannerData);
		if (barrage==null) {
			return new Model(500, "查询barrage失败");
		}
		return new Model(200, barrage);
	}
	/**
	 * @author 10747
	 * @date 2017年12月8日
	 * @Remarks
	 * @Tile={<h1>comic查看弹幕 </h1>}
	 * @describe={
	 * <p>添加方法：</p >
	 * <ul>
	 * <li>...</li>
	 * <li>...</li>
	 * </ul>}
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_BARRAGE, method = RequestMethod.POST)
	public Model getBarrage(BarrageData bannerData) throws Exception {
		bannerData.clickUser();
		UserEntity userEntity=userService.getUserInfoById(bannerData.getUserId());
		if (userEntity==null) {
			return new Model(404,"无用户");
		}
		List<Barrage> list= barrageService.getComicBarrage(bannerData);
		if (!ParaClick.clickList(list)) {
			return new Model(200, "暂无内容");
		}
		return new Model(200, list);
	}
}
