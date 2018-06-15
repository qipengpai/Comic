package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonSetComment;
import com.qin.crxl.comic.entity.vo.CartoonSetCommentData;
import com.qin.crxl.comic.service.AdminCartoonSetCommentService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

/**
 * 用户对话的评论控制类
 * 
 * @author cui
 * 
 */
@Controller
public class AdminCartoonSetCommentController {

	@Autowired
	private AdminCartoonSetCommentService adminCartoonSetCommentservice;

	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONSETCOMMENT, method = RequestMethod.POST)
	public Model selectCartoonSetComment(
			CartoonSetCommentData cartoonSetCommentData) {
		if (!ParaClick.clickString(cartoonSetCommentData.getStarTime())
				&& !ParaClick.clickString(cartoonSetCommentData.getEndTime())) {
			Long start = 0L;
			Long end = 0L;
			// 开始时间
			start = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonSetCommentData.getStarTime() + " 00:00:00")
					.getTime();
			// 结束时间
			end = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonSetCommentData.getEndTime() + " 23:59:59").getTime();
			if (start > end) {
				return new Model(500, "开始时间不能大于结束时间");
			}
		}
		int num = 0;
		if (ParaClick.clickString(cartoonSetCommentData.getNowPage())) {
			cartoonSetCommentData.setNowPage("1");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getPageNum())) {
			cartoonSetCommentData.setPageNum("10");
		}
		List<Object[]> cartoonSetCommentList = adminCartoonSetCommentservice
				.selectCartoonSetComment(cartoonSetCommentData);
		if (!ParaClick.clickList(cartoonSetCommentList)) {
			return new Model(500, "查询失败");
		}
		num = adminCartoonSetCommentservice.getCount(cartoonSetCommentData);
		for (int i = 0; i < cartoonSetCommentList.size(); i++) {
			try {
				int commentNum = adminCartoonSetCommentservice.getCommentNum((String)cartoonSetCommentList.get(i)[0]);
				cartoonSetCommentList.get(i)[8]=commentNum;
				cartoonSetCommentList.get(i)[3] = StringToInt
						.toString((String) cartoonSetCommentList.get(i)[3]);
				cartoonSetCommentList.get(i)[2] = StringToInt
						.toString((String) cartoonSetCommentList.get(i)[2]);
			} catch (Exception e) {
				cartoonSetCommentList.get(i)[2] = "潮人用户";
			}
		}
		Model model = new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonSetCommentData.getNowPage().trim()));
		model.setTotalpage((num
				+ Integer.parseInt(cartoonSetCommentData.getPageNum()) - 1)
				/ Integer.parseInt(cartoonSetCommentData.getPageNum()));
		model.setObj(cartoonSetCommentList);
		model.setTotalNum(num);
		return model;
	}

	// 修改漫画评论是否可让用户看到
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONSETCOMMENT, method = RequestMethod.POST)
	public Model updateState(CartoonSetCommentData cartoonSetCommentData) {
		CartoonSetComment cartoonSetComment=adminCartoonSetCommentservice.get(cartoonSetCommentData.getId());
		
		boolean bool = adminCartoonSetCommentservice
				.updateCartoonSetCommentState(cartoonSetCommentData,cartoonSetComment.getCartoonSetId());
		if (bool) {
			return new Model(200, "修改成功");
		}
		return new Model(500, "修改失败");
	}

	
	//查询漫画的话的评论的评论
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONSETCOMMENT_COMMENT, method = RequestMethod.POST)
	public Model selectCartoonSetCommentAndComment(
			CartoonSetCommentData cartoonSetCommentData) {
		if (!ParaClick.clickString(cartoonSetCommentData.getStarTime())
				&& !ParaClick.clickString(cartoonSetCommentData.getEndTime())) {
			Long start = 0L;
			Long end = 0L;
			// 开始时间
			start = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonSetCommentData.getStarTime() + " 00:00:00")
					.getTime();
			// 结束时间
			end = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonSetCommentData.getEndTime() + " 23:59:59").getTime();
			if (start > end) {
				return new Model(500, "开始时间不能大于结束时间");
			}
		}
		int num = 0;
		if (ParaClick.clickString(cartoonSetCommentData.getNowPage())) {
			cartoonSetCommentData.setNowPage("1");
		}
		if (ParaClick.clickString(cartoonSetCommentData.getPageNum())) {
			cartoonSetCommentData.setPageNum("10");
		}
		List<Object[]> cartoonSetCommentList = adminCartoonSetCommentservice
				.selectCartoonCommentAndComment(cartoonSetCommentData);
		if (!ParaClick.clickList(cartoonSetCommentList)) {
			return new Model(500, "查询失败");
		}
		num = adminCartoonSetCommentservice.getCartoonCommentAndCommentCount(cartoonSetCommentData);
		for (int i = 0; i < cartoonSetCommentList.size(); i++) {
			try {
				cartoonSetCommentList.get(i)[3] = StringToInt
						.toString((String) cartoonSetCommentList.get(i)[3]);
				cartoonSetCommentList.get(i)[2] = StringToInt
						.toString((String) cartoonSetCommentList.get(i)[2]);
			} catch (Exception e) {
				cartoonSetCommentList.get(i)[2] = "潮人用户";
			}
		}
		Model model = new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonSetCommentData.getNowPage()));
		model.setTotalpage((num
				+ Integer.parseInt(cartoonSetCommentData.getPageNum()) - 1)
				/ Integer.parseInt(cartoonSetCommentData.getPageNum()));
		model.setObj(cartoonSetCommentList);
		model.setTotalNum(num);
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONSETCOMMENT_COMMENT, method = RequestMethod.POST)
	public Model updateStateCommentAndComment(CartoonSetCommentData cartoonSetCommentData) {
		if(ParaClick.clickString(cartoonSetCommentData.getId())){
			return new Model(500,"id为空");
		}
		if(ParaClick.clickString(cartoonSetCommentData.getDeleteCartoonSetComment())){
			return new Model(500,"状态为空");
		}
		boolean bool = adminCartoonSetCommentservice
				.updateCommentAndComment(cartoonSetCommentData);
		if (bool) {
			return new Model(200, "修改成功");
		}
		return new Model(500, "修改失败");
	}
	
}
