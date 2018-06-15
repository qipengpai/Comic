package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.DistributorNews;
import com.qin.crxl.comic.entity.vo.DistributorNewsData;
import com.qin.crxl.comic.service.DistributorNewsService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class DistributorNewsController {

	@Autowired
	private DistributorNewsService distributorNewsService;
	
	/**
	 * 增加公告
	 * @author pengpai
	 * @date 2018/2/27 16:25
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_ADD_DISTRIBUTIO_NEWS, method = RequestMethod.POST)
	public Model addDistributorNews(DistributorNewsData distributorNewsData)
			throws Exception {
		if (ParaClick.clickString(distributorNewsData.getContent())) {
			return new Model(500, "请输入内容");
		}
		if (ParaClick.clickString(distributorNewsData.getTitle())) {
			return new Model(500, "请输入标题");
		}
		//  查看渠道是否存在
		boolean flag = distributorNewsService.addDistributorNews(distributorNewsData.getContent(),distributorNewsData.getTitle());
		if (!flag) {
			return new Model(500, "增加失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("增加成功");
		return model;
	}
	/**
	 * 修改分销商公告
	 * @author pengpai
	 * @date 2018/2/27 20:10
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_UPDATE_DISTRIBUTIO_NEWS, method = RequestMethod.POST)
	public Model updateDistributorNews(DistributorNewsData distributorNewsData)
			throws Exception {
		if (ParaClick.clickString(distributorNewsData.getId())) {
			return new Model(500, "请输入id");
		}
		//  查看渠道是否存在
		boolean flag = distributorNewsService.updateDistributorNews(distributorNewsData);
		if (!flag) {
			return new Model(500, "修改失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("修改成功");
		return model;
	}
	/**
	 * 删除分销商公告
	 * @author pengpai
	 * @date 2018/2/27 20:25
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_DELETE_DISTRIBUTIO_NEWS_BYID, method = RequestMethod.POST)
	public Model deleteDistributorNews(DistributorNewsData distributorNewsData)
			throws Exception {
		if (ParaClick.clickString(distributorNewsData.getId())) {
			return new Model(500, "请输入id");
		}
		//  查看渠道是否存在
		boolean flag = distributorNewsService.deleteDistributorNews(distributorNewsData.getId());
		if (!flag) {
			return new Model(500, "删除失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("删除成功");
		return model;
	}
	/**
	 * 查询分销商公告根据id
	 * @author pengpai
	 * @date 2018/2/27 20:42
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_SELECT_DISTRIBUTIO_NEWS_BYID, method = RequestMethod.POST)
	public Model selectDistributorNewsById(DistributorNewsData distributorNewsData)
			throws Exception {
		if (ParaClick.clickString(distributorNewsData.getId())) {
			return new Model(500, "id为空 ");
		}
		//  查看渠道公告列表
		DistributorNews distributorNews = distributorNewsService.get(distributorNewsData.getId());
		if (distributorNews==null) {
			return new Model(500, "查询失败 ");
		}
		Model model = new Model();
		model.setError(200);
		model.setObj(distributorNews);
		model.setMsg("查询成功");
		return model;
	}
	
	/**
	 * 查询分销商公告
	 * @author pengpai
	 * @date 2018/2/27 20:45
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_SELECT_DISTRIBUTIO_NEWS, method = RequestMethod.POST)
	public Model selectDistributorNews(DistributorNewsData distributorNewsData)
			throws Exception {
		if (ParaClick.clickString(distributorNewsData.getNowPage())) {
			distributorNewsData.setNowPage("1");
		}
		if (ParaClick.clickString(distributorNewsData.getPageNum())) {
			distributorNewsData.setPageNum("15");
		}
		//  查看渠道公告数量
		int num =distributorNewsService.selectDistributorNewsCount(distributorNewsData.getCondition(),distributorNewsData.getNowPage(),distributorNewsData.getPageNum());
		if (num==0) {
			return new Model(500, "查询失败 ");
		}
		//  查看渠道公告列表
		List<DistributorNews> list = distributorNewsService.selectDistributorNews(distributorNewsData.getCondition(),distributorNewsData.getNowPage(),distributorNewsData.getPageNum());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询失败 ");
		}
		Model model = new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(distributorNewsData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(distributorNewsData.getPageNum()) - 1) / Integer.parseInt(distributorNewsData.getPageNum()));
		model.setObj(list);
		model.setTotalNum(num);
		model.setMsg("查询成功");
		return model;
	}
	
}
