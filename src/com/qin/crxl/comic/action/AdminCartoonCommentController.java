package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonComment;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;
import com.qin.crxl.comic.service.AdminCartoonCommentService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

/**
 * 用户对漫画评论表控制类
 * @author cui
 *
 */
@Controller
public class AdminCartoonCommentController {
	@Autowired
	private AdminCartoonCommentService adminCartoonCommentservice;
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONCOMMENT, method = RequestMethod.POST)
	public Model selectCartoonComment(CartoonCommentData cartoonCommentData){
		if(!ParaClick.clickString(cartoonCommentData.getStarTime())&&!ParaClick.clickString(cartoonCommentData.getEndTime())){
			Long start = 0L;
			Long end = 0L;
			//开始时间
			start = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonCommentData.getStarTime() + " 00:00:00").getTime();
			// 结束时间
			end = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonCommentData.getEndTime() + " 23:59:59").getTime();
			if (start > end) {
				return new Model(500, "开始时间不能大于结束时间");
			}
		}
		
		int num=0;
		if(ParaClick.clickString(cartoonCommentData.getNowPage())){
			cartoonCommentData.setNowPage("1");
		}
		if(ParaClick.clickString(cartoonCommentData.getPageNum())){
			cartoonCommentData.setPageNum("10");
		}
		List<Object[]> cartoonComment = adminCartoonCommentservice.selectCartoonComment(cartoonCommentData);
		if(!ParaClick.clickList(cartoonComment)){
			return new Model(500,"查询失败");
		}
		num=adminCartoonCommentservice.getCount(cartoonCommentData);
		for(int i=0;i<cartoonComment.size();i++){
			try {
				cartoonComment.get(i)[5]=StringToInt.toString((String)cartoonComment.get(i)[5]);
				int commentNum = adminCartoonCommentservice.getCommentNum((String)cartoonComment.get(i)[0]);
				cartoonComment.get(i)[10]=commentNum;
				cartoonComment.get(i)[4]=StringToInt.toString((String)cartoonComment.get(i)[4]);
			} catch (Exception e) {
				cartoonComment.get(i)[4]="潮人用户";
			}
		}
		Model model=new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonCommentData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(cartoonCommentData.getPageNum()) - 1) / Integer.parseInt(cartoonCommentData.getPageNum()));
		model.setObj(cartoonComment);
		model.setTotalNum(num);
		return model;
	}
	//修改漫画评论是否可让用户看到
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONCOMMENT, method = RequestMethod.POST)
	public Model updateState(CartoonCommentData cartoonCommentData){
		CartoonComment cartoonComment=adminCartoonCommentservice.get(cartoonCommentData.getId());
		
		boolean bool = adminCartoonCommentservice.updateCartoonComment(cartoonCommentData,cartoonComment.getCartoonId());
		if(bool){
			return new Model(200,"修改成功");
		}
		return new Model(500,"修改失败");
	}
	
	//查询漫画评论的评论
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONCOMMENT_CCOMMENT, method = RequestMethod.POST)
	public Model selectCartoonCommentAndComment(CartoonCommentData cartoonCommentData){
		if(!ParaClick.clickString(cartoonCommentData.getStarTime())&&!ParaClick.clickString(cartoonCommentData.getEndTime())){
			Long start = 0L;
			Long end = 0L;
			//开始时间
			start = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonCommentData.getStarTime() + " 00:00:00").getTime();
			// 结束时间
			end = DateUtil.getdate_yyyy_MM_dd_00_00_00(
					cartoonCommentData.getEndTime() + " 23:59:59").getTime();
			if (start > end) {
				return new Model(500, "开始时间不能大于结束时间");
			}
		}
		
		int num=0;
		if(ParaClick.clickString(cartoonCommentData.getNowPage())){
			cartoonCommentData.setNowPage("1");
		}
		if(ParaClick.clickString(cartoonCommentData.getPageNum())){
			cartoonCommentData.setPageNum("10");
		}
		List<Object[]> cartoonComment = adminCartoonCommentservice.selectCartoonCommentAndComment(cartoonCommentData);
		if(!ParaClick.clickList(cartoonComment)){
			return new Model(500,"查询失败");
		}
		num=adminCartoonCommentservice.getCartoonCommentAndCommentCount(cartoonCommentData);
		for(int i=0;i<cartoonComment.size();i++){
			try {
				cartoonComment.get(i)[3]=StringToInt.toString((String)cartoonComment.get(i)[3]);
				cartoonComment.get(i)[2]=StringToInt.toString((String)cartoonComment.get(i)[2]);
			} catch (Exception e) {
				cartoonComment.get(i)[2]="潮人用户";
			}
		}
		Model model=new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonCommentData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(cartoonCommentData.getPageNum()) - 1) / Integer.parseInt(cartoonCommentData.getPageNum()));
		model.setObj(cartoonComment);
		model.setTotalNum(num);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONCOMMENT_CCOMMENT, method = RequestMethod.POST)
	public Model updateStateCommentAndComment(CartoonCommentData cartoonCommentData){
		if(ParaClick.clickString(cartoonCommentData.getId())){
			return new Model(500,"id为空");
		}
		if(ParaClick.clickString(cartoonCommentData.getDeleteCartoonComment())){
			return new Model(500,"状态为空");
		}
		CartoonComment cartoonComment = getCartoonCommentById(cartoonCommentData.getId());
		if (cartoonComment == null) {
			return new Model(500, "评论為空");
		}
		boolean bool = adminCartoonCommentservice.updateCartoonCommentAndComment(cartoonCommentData,cartoonComment.getCartoonId());
		if(bool){
			return new Model(200,"修改成功");
		}
		return new Model(500,"修改失败");
	}
	
	public CartoonComment getCartoonCommentById(String id){
		CartoonComment cartoonComment = adminCartoonCommentservice
				.get(id);
		return cartoonComment;
	}
}