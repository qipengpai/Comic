package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.DistributionCover;
import com.qin.crxl.comic.entity.DistributorCartoonModel;
import com.qin.crxl.comic.entity.vo.DistributionCoverData;
import com.qin.crxl.comic.entity.vo.DistributorCartoonModelData;
import com.qin.crxl.comic.service.DistributionCoverServcie;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class DistributionCoverController {

	
	@Autowired
	private DistributionCoverServcie distributionCoverServcie;
	
	/**
	 * 登录
	 * @author pengpai
	 * @date 2018/3/2 16:25
	 * @param []
	 * @return void
	 */
	
//	@ResponseBody
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public Model login(DistributionCoverData distributionCoverData)
//			throws Exception {
//		//  查看模板列表
//		DistributionCover distributionCover = distributionCoverServcie.getByLogin(distributionCoverData.getCartoonId(),distributionCoverData.getImplDate());
//		if (distributionCover==null) {
//			return new Model(500, "查询失败 ");
//		}
//		Model model = new Model();
//		model.setError(200);
//		model.setObj(distributionCover);
//		model.setMsg("查询成功");
//		return model;
//	}
	
	
	/**
	 * 增加漫画推广模板
	 * @author pengpai
	 * @date 2018/3/2 16:25
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_ADD_DISTRIBUTION_CARTOONMODEL, method = RequestMethod.POST)
	public Model addDistributorTitle(DistributionCoverData distributionCoverData)
			throws Exception {
		if (ParaClick.clickString(distributionCoverData.getCartoonCover())) {
			return new Model(500, "请输入魔板");
		}
		//  增加漫画推广标题
		boolean flag = distributionCoverServcie.addDistributorModel(distributionCoverData.getCartoonCover());
		if (!flag) {
			return new Model(500, "增加失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("增加成功");
		return model;
	}
	/**
	 * 修改漫画推广模板
	 * @author pengpai
	 * @date 2018/3/2 20:10
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_UPDATE_DISTRIBUTION_CARTOONMODEL, method = RequestMethod.POST)
	public Model updateDistributorTitle(DistributionCoverData distributionCoverData)
			throws Exception {
		if (ParaClick.clickString(distributionCoverData.getId())) {
			return new Model(500, "请输入id");
		}
		//  修改魔板内容
		boolean flag = distributionCoverServcie.updateDistributorModel(distributionCoverData);
		if (!flag) {
			return new Model(500, "修改失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("修改成功");
		return model;
	}
	/**
	 * 删除漫画推广模板
	 * @author pengpai
	 * @date 2018/3/2 20:25
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_DELETE_DISTRIBUTION_CARTOONMODEL, method = RequestMethod.POST)
	public Model deleteDistributorTitle(DistributionCoverData distributionCoverData)
			throws Exception {
		if (ParaClick.clickString(distributionCoverData.getId())) {
			return new Model(500, "请输入id");
		}
		//  查看渠道是否存在
		boolean flag = distributionCoverServcie.deleteDistributorNews(distributionCoverData.getId());
		if (!flag) {
			return new Model(500, "删除失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("删除成功");
		return model;
	}
	/**
	 * 查询漫画推广模板根据id
	 * @author pengpai
	 * @date 2018/3/2 20:42
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_SELECT_DISTRIBUTION_CARTOONMODEL_BYID, method = RequestMethod.POST)
	public Model selectDistributorTitleById(DistributionCoverData distributionCoverData)
			throws Exception {
		if (ParaClick.clickString(distributionCoverData.getId())) {
			return new Model(500, "id为空 ");
		}
		//  查看模板列表
		DistributionCover distributionCover = distributionCoverServcie.get(distributionCoverData.getId());
		if (distributionCover==null) {
			return new Model(500, "查询失败 ");
		}
		Model model = new Model();
		model.setError(200);
		model.setObj(distributionCover);
		model.setMsg("查询成功");
		return model;
	}
	
	/**
	 * 查询漫画推广模板
	 * @author pengpai
	 * @date 2018/3/2 20:45
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_SELECT_DISTRIBUTION_CARTOONMODEL, method = RequestMethod.POST)
	public Model selectDistributorTitle(DistributionCoverData distributionCoverData)
			throws Exception {
		if (ParaClick.clickString(distributionCoverData.getNowPage())) {
			distributionCoverData.setNowPage("1");
		}
		if (ParaClick.clickString(distributionCoverData.getPageNum())) {
			distributionCoverData.setPageNum("15");
		}
		//  查看漫画推广模板数量
		int num =distributionCoverServcie.selectDistributorModelCount();
		if (num==0) {
			return new Model(500, "查询失败 ");
		}
		//  查看漫画推广模板列表
		List<DistributionCover> list = distributionCoverServcie.selectDistributorModel(distributionCoverData.getNowPage(),distributionCoverData.getPageNum());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询失败 ");
		}
		Model model = new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(distributionCoverData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(distributionCoverData.getPageNum()) - 1) / Integer.parseInt(distributionCoverData.getPageNum()));
		model.setObj(list);
		model.setTotalNum(num);
		model.setMsg("查询成功");
		return model;
	}
}
