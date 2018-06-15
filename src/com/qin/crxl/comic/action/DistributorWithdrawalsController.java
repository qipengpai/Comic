package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.DistributorWithdrawals;
import com.qin.crxl.comic.entity.vo.DistributorWithdrawalsData;
import com.qin.crxl.comic.service.DistributorWithdrawalsService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.DateUtil;

@Controller
public class DistributorWithdrawalsController {

	@Autowired
	private DistributorWithdrawalsService distributorWithdrawalsService;

	/**
	 * 查看分销商结算单
	 * @author pengpai
	 * @date 2018/3/2 10:33
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_WITHDRAWALS_GET, method = RequestMethod.POST)
	public Model getDistributorWithdrawals(
			DistributorWithdrawalsData distributorWithdrawalsData)
			throws Exception {
		if (ParaClick.clickString(distributorWithdrawalsData.getNowPage())) {
			distributorWithdrawalsData.setNowPage("1");
		}
		if (ParaClick.clickString(distributorWithdrawalsData.getPageNum())) {
			distributorWithdrawalsData.setPageNum("15");
		}
		if ("".equals(distributorWithdrawalsData.getStartDate())
				|| null == distributorWithdrawalsData.getStartDate()) {
			distributorWithdrawalsData.setStartDate(DateUtil.getYesterday());
			distributorWithdrawalsData.setEndDate(DateUtil.getYesterday());
		}
		Long start = 0L;
		Long end = 0L;
		start = DateUtil.getdate_yyyy_MM_dd_00_00_00(
				distributorWithdrawalsData.getStartDate() + " 00:00:00").getTime();
		//用户设置结束时间
		end = DateUtil.getdate_yyyy_MM_dd_00_00_00(
				distributorWithdrawalsData.getEndDate() + " 23:59:59").getTime();
		//当前系统结束时间
		long nowend = DateUtil.getdate_yyyy_MM_dd_00_00_00(
				DateUtil.getdate_yyyy_MM_dd() + " 23:59:59").getTime();
		if(end>nowend){
			return new Model(500, "结束时间不能大于当前日期");
		}
		if (start > end) {
			return new Model(500, "开始时间大于结束时间");
		}
		// 查看漫画结算单数量
		int num = distributorWithdrawalsService
				.selectDistributorWithdrawalsCount(distributorWithdrawalsData.getCondition(),distributorWithdrawalsData.getWithdrawalsState(),distributorWithdrawalsData.getStartDate(),distributorWithdrawalsData.getEndDate());
		if (num == 0) {
			return new Model(500, "查询失败 ");
		}
		// 查看漫画结算单列表
		List<DistributorWithdrawals> list = distributorWithdrawalsService
				.selectDistributorWithdrawals(
						distributorWithdrawalsData.getCondition(),
						distributorWithdrawalsData.getNowPage(),
						distributorWithdrawalsData.getPageNum(),distributorWithdrawalsData.getWithdrawalsState(),distributorWithdrawalsData.getStartDate(),distributorWithdrawalsData.getEndDate());
		if (!ParaClick.clickList(list)) {
			return new Model(500, "查询失败 ");
		}
		Model model = new Model();
		model.setError(200);
		model.setNowpage(Integer.parseInt(distributorWithdrawalsData
				.getNowPage()));
		model.setTotalpage((num
				+ Integer.parseInt(distributorWithdrawalsData.getPageNum()) - 1)
				/ Integer.parseInt(distributorWithdrawalsData.getPageNum()));
		model.setObj(list);
		model.setTotalNum(num);
		model.setMsg("查询成功");
		return model;
	}


	/**
	 * 打款后完成订单
	 * @author pengpai
	 * @date 2018/3/3 9:33
	 * @param []
	 * @return void
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DISTRIBUTOR_WITHDRAWALS_FINISH, method = RequestMethod.POST)
	public Model finishDistributorWithdrawals(DistributorWithdrawalsData distributorWithdrawalsData)
			throws Exception {
		if (ParaClick.clickString(distributorWithdrawalsData.getId())) {
			return new Model(500,"id为空");
		}
		boolean flag=distributorWithdrawalsService.finishDistributorWithdrawals(distributorWithdrawalsData.getId());
		if (!flag) {
			return new Model(500,"结算失败");
		}
		Model model = new Model();
		model.setError(200);
		model.setMsg("修改成功");
		return model;
	}
	
}
