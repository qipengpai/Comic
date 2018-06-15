package com.qin.crxl.comic.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Feedback;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FeedBackData;
import com.qin.crxl.comic.service.AdminFeedbackService;
import com.qin.crxl.comic.service.AdminUserEntityService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

/**
 * 反馈表控制类
 * @author cui
 *
 */
@Controller
public class AdminFeedbackController {
	@Autowired
	private AdminFeedbackService adminFeedbackService;
	@Autowired
	private AdminUserEntityService  adminUserEntityService;
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_FEEDBACK, method = RequestMethod.POST)
	public Model selectFeedback(FeedBackData feedBackData){
		int num=0;
		if(ParaClick.clickString(feedBackData.getNowPage())){
			feedBackData.setNowPage("1");
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(feedBackData.getNowPage());
		if (!isNum.matches()) {
			return new Model(500, "请输入数字！！好吗？？");
		}
		if(ParaClick.clickString(feedBackData.getPageNum())){
			feedBackData.setPageNum("10");
		}
		List<Feedback> feedbackList = adminFeedbackService.selectFeedback(feedBackData);
		for(int i=0;i<feedbackList.size();i++){
			UserEntity user = adminUserEntityService.get(feedbackList.get(i).getUserId());
			feedbackList.get(i).setObj(StringToInt.toString(user.getUsername()));
		}
		Model model=new Model();
		if(ParaClick.clickList(feedbackList)){
			num=adminFeedbackService.getCount(feedBackData);
			model.setError(200);
			model.setNowpage(Integer.parseInt(feedBackData.getNowPage()));
			model.setTotalpage((num + Integer.parseInt(feedBackData.getPageNum()) - 1) / Integer.parseInt(feedBackData.getPageNum()));
			model.setObj(feedbackList);
			model.setTotalNum(num);
			return model;
		}
		return new Model(500,"查询失败");
	}
}
