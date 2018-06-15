package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.News;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.NewsData;
import com.qin.crxl.comic.service.NewsService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtils;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private UserService userService;
	
	/**
	 * pp
	 * @Remarks app>個人中心>我的消息
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.USER_MY_SENDCODE, method = RequestMethod.POST)
	public Model userUplod(NewsData newsData) throws Exception {
		newsData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(newsData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if (ParaClick.clickString(newsData.getNowPage())) {
			newsData.setNowPage("1");
		}
		Model model=new Model();
		int num =newsService.getNewsNum();
		if (num==0) {
			return new Model(500,"暫無消息");
		}
		List<News> list=newsService.getAllNews(newsData.getNowPage());
		if (!ParaClick.clickList(list)) {
			return new Model(500,"暫無消息");
		}
		for (News news : list) {
			news.setSendDate(DateUtils.showTimeText(DateUtils.getDate(news.getSendDate(), "yyyy-MM-dd HH:mm")));
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(newsData.getNowPage()));
		model.setTotalpage((num + 10 - 1) / 10);
		model.setObj(list);
		return model;
	}
	
	/**
	 * pp
	 * @Remarks PC>個人中心>增加消息
	 * @throws Exception
	 * */
//	@ResponseBody
//	@RequestMapping(value = ActionUrl.ADD_SENDCODE, method = RequestMethod.POST)
//	public Model a(NewsData newsData) throws Exception {
//		newsData.clickUser();
//		UserEntity userEntity = userService.getUserInfoById(newsData.getUserId());
//		if (userEntity == null) {
//			return new Model(404, "无用户");
//		}
//		Model model=new Model();
//		int num =newsService.getNewsNum();
//		if (num==0) {
//			return new Model(500,"暫無消息");
//		}
//		List<News> list=newsService.getAllNews(newsData);
//		if (!ParaClick.clickList(list)) {
//			return new Model(500,"暫無消息");
//		}
//		model.setError(200);
//		model.setNowpage(Integer.parseInt(newsData.getNowPage()));
//		model.setTotalpage((num + 10 - 1) / 10);
//		model.setObj(list);
//		return model;
//	}
}