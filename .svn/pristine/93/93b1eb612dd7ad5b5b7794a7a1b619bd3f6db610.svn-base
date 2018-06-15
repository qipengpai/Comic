package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.DistributionCartoonTitle;
import com.qin.crxl.comic.entity.vo.DistributorCartoonTitleData;
import com.qin.crxl.comic.service.DistributionCartoonTitleService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class DistributionCartoonTitleController {

	@Autowired
	private DistributionCartoonTitleService distributionCartoonTitleServiceImpl;
	
	/**
	 * 增加漫画推广标题
	 * @author pengpai
	 * @date 2018/3/1 16:25
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_ADD_DISTRIBUTION_CARTOONTITLE, method = RequestMethod.POST)
	public Model addDistributorTitle(DistributorCartoonTitleData distributorCartoonTitleData)
			throws Exception {
		if (ParaClick.clickString(distributorCartoonTitleData.getCartoonId())) {
			return new Model(500, "请输入漫画id");
		}
		if (ParaClick.clickString(distributorCartoonTitleData.getCartoonTitle())) {
			return new Model(500, "请输入标题");
		}
		//  增加漫画推广标题
		boolean flag = distributionCartoonTitleServiceImpl.addDistributorTitle(distributorCartoonTitleData.getCartoonId(),distributorCartoonTitleData.getCartoonTitle());
		if (!flag) {
			return new Model(500, "增加失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("增加成功");
		return model;
	}
	/**
	 * 修改漫画推广标题
	 * @author pengpai
	 * @date 2018/3/1 20:10
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_UPDATE_DISTRIBUTION_CARTOONTITLE, method = RequestMethod.POST)
	public Model updateDistributorTitle(DistributorCartoonTitleData distributorCartoonTitleData)
			throws Exception {
		if (ParaClick.clickString(distributorCartoonTitleData.getId())) {
			return new Model(500, "请输入id");
		}
		//  查看渠道是否存在
		boolean flag = distributionCartoonTitleServiceImpl.updateDistributorTitle(distributorCartoonTitleData);
		if (!flag) {
			return new Model(500, "修改失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("修改成功");
		return model;
	}
	/**
	 * 删除漫画推广标题
	 * @author pengpai
	 * @date 2018/3/1 20:25
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_DELETE_DISTRIBUTION_CARTOONTITLE, method = RequestMethod.POST)
	public Model deleteDistributorTitle(DistributorCartoonTitleData distributorCartoonTitleData)
			throws Exception {
		if (ParaClick.clickString(distributorCartoonTitleData.getId())) {
			return new Model(500, "请输入id");
		}
		//  查看渠道是否存在
		boolean flag = distributionCartoonTitleServiceImpl.deleteDistributorNews(distributorCartoonTitleData.getId());
		if (!flag) {
			return new Model(500, "删除失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("删除成功");
		return model;
	}
	/**
	 * 查询漫画推广标题根据id
	 * @author pengpai
	 * @date 2018/3/1 20:42
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_SELECT_DISTRIBUTION_CARTOONTITLE_BYID, method = RequestMethod.POST)
	public Model selectDistributorTitleById(DistributorCartoonTitleData distributorCartoonTitleData)
			throws Exception {
		if (ParaClick.clickString(distributorCartoonTitleData.getId())) {
			return new Model(500, "id为空 ");
		}
		//  查看渠道公告列表
		DistributionCartoonTitle distributorNews = distributionCartoonTitleServiceImpl.get(distributorCartoonTitleData.getId());
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
	 * 查询漫画推广标题
	 * @author pengpai
	 * @date 2018/3/1 20:45
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_SELECT_DISTRIBUTION_CARTOONTITLE, method = RequestMethod.POST)
	public Model selectDistributorTitle(DistributorCartoonTitleData distributorCartoonTitleData)
			throws Exception {
		if (ParaClick.clickString(distributorCartoonTitleData.getNowPage())) {
			distributorCartoonTitleData.setNowPage("1");
		}
		if (ParaClick.clickString(distributorCartoonTitleData.getPageNum())) {
			distributorCartoonTitleData.setPageNum("15");
		}
		//  查看分销商结算单数量
		int num =distributionCartoonTitleServiceImpl.selectDistributorTitleCount(distributorCartoonTitleData.getCondition(),distributorCartoonTitleData.getNowPage(),distributorCartoonTitleData.getPageNum(),distributorCartoonTitleData.getCartoonId());
		if (num==0) {
			return new Model(500, "查询失败 ");
		}
		//  查看分销商结算单数量列表
		List<DistributionCartoonTitle> list = distributionCartoonTitleServiceImpl.selectDistributorTitle(distributorCartoonTitleData.getCondition(),distributorCartoonTitleData.getNowPage(),distributorCartoonTitleData.getPageNum(),distributorCartoonTitleData.getCartoonId());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询失败 ");
		}
		Model model = new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(distributorCartoonTitleData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(distributorCartoonTitleData.getPageNum()) - 1) / Integer.parseInt(distributorCartoonTitleData.getPageNum()));
		model.setObj(list);
		model.setTotalNum(num);
		model.setMsg("查询成功");
		return model;
	}
}
