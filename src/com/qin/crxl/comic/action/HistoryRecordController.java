package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.HistoryRecord;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.service.CartoonSetService;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class HistoryRecordController {

	@Autowired
	private UserService userService;
	@Autowired
	private CartoonService cartoonService;
	@Autowired
	private HistoryRecordService historyRecordService;
	@Autowired
	private CartoonSetService cartoonSetService;
	
	/**
	 * pp 
	 * h5>個人中心>歷史紀錄  fore
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_HISTORY_RECORD, method = RequestMethod.POST)
	public Model getHistoryRecord(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (!ParaClick.clickString(cartoonData.getId())) {
			return new Model(500, "ID为空 ");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		Model model =new Model();
		int num =historyRecordService.getAllHistoryRecordCount(userEntity,cartoonData);
		if(num==0){
			return new Model(500, "暫無記錄或查詢失敗  ");
		}
		List<Object>  historyRecord= historyRecordService.getAllHistoryRecord(userEntity,cartoonData);
		if (!ParaClick.clickList(historyRecord)) {
			return new Model(500, "暫無記錄或查詢失敗 ");
		}
		List<Object> list2 =new ArrayList<Object>();
		for (Object object : historyRecord) {
			Cartoon cartoon=cartoonService.get(object.toString());
			list2.add(cartoon);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}
	
	/**
	 * pp 
	 * ios>個人中心>歷史紀錄  fore
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_HISTORY_RECORD, method = RequestMethod.POST)
	public Model getHistoryRecordByIos(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (!ParaClick.clickString(cartoonData.getId())) {
			return new Model(500, "ID为空 ");
		}
		if (ParaClick.clickString(cartoonData.getNowPage())) {
			cartoonData.setNowPage("1");
		}
		Model model =new Model();
		int num =historyRecordService.getAllHistoryRecordCountByIos(userEntity,cartoonData);
		if(num==0){
			return new Model(500, "暫無記錄或查詢失敗  ");
		}
		List<Object>  historyRecord= historyRecordService.getAllHistoryRecordByIos(userEntity,cartoonData);
		if (!ParaClick.clickList(historyRecord)) {
			return new Model(500, "暫無記錄或查詢失敗 ");
		}
		List<Object> list2 =new ArrayList<Object>();
		for (Object object : historyRecord) {
			Cartoon cartoon=cartoonService.get(object.toString());
			list2.add(cartoon);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list2);
		return model;
	}
	/**
	 * pp 
	 * app>續看 
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_GOON_HISTORY_RECORD, method = RequestMethod.POST)
	public Model getGoOnHistoryRecord(CartoonData cartoonData)
			throws Exception {
		cartoonData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(cartoonData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(cartoonData.getId())) {
			return new Model(500, "ID为空 ");
		}
		Model model =new Model();
		List<HistoryRecord> historyRecord= historyRecordService.getCartoonHistoryRecord(userEntity,cartoonData);
		if (!ParaClick.clickList(historyRecord)) {
			List<CartoonSet>  cartoonSet=cartoonSetService.getByCartoon(cartoonData.getId());
			model.setError(200);
			model.setMsg("1");
			model.setObj(cartoonSet.get(0));
			return model;
		}
		CartoonSet cartoonSet=cartoonSetService.get(historyRecord.get(0).getCartoonSetId());
		model.setError(200);
		model.setMsg("0");
		model.setObj(cartoonSet);
		return model;
	}
}