package com.qin.crxl.comic.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.DistributionCartoonTitle;
import com.qin.crxl.comic.entity.DistributorWithdrawals;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.DistributorService;
import com.qin.crxl.comic.service.DistributorWithdrawalsService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class DistributorWithdrawalsServiceImpl extends
		BaseServiceImpl<DistributorWithdrawals> implements
		DistributorWithdrawalsService {

	@Autowired
	private DistributorService distributorService;

	@Override
	public boolean addDistributorWithdrawals(List<Object[]> numSum, String id,
			double proportion, String string) {
		// 生成待结算单
		boolean flag = false;
		// 获取昨天的日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			DistributorWithdrawals distributorWithdrawals = new DistributorWithdrawals();
			distributorWithdrawals.setDistridutionId(id);
			distributorWithdrawals.setImpldate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			distributorWithdrawals.setWithdrawalsDate(yesterday + " 12:23:34");
			distributorWithdrawals
					.setOrderSum(Integer.parseInt(numSum.get(0)[1].toString()));
			distributorWithdrawals.setRemarks(string);
			distributorWithdrawals.setTodayRecharge(Double.parseDouble(numSum
					.get(0)[0].toString()));
			distributorWithdrawals.setWithdrawalsState(0);
			distributorWithdrawals.setProportion(proportion);
			double sum=Double.parseDouble(df.format((Double
					.parseDouble(numSum.get(0)[0].toString()) - (Double
					.parseDouble(numSum.get(0)[0].toString()) * 0.01))
					* proportion));
			distributorWithdrawals
					.setWithdrawalsMoney(sum);
			boolean flag2=distributorService.updateReCharge(sum,id);
			if (!flag2) {
				return flag;
			}
			save(distributorWithdrawals);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public int selectDistributorWithdrawalsCount(String condition,
			String withdrawalsState, String startDate, String endDate) {
		// 查看分销商结算单数量
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM DistributorWithdrawals WHERE 1=1 ");
		if (!ParaClick.clickString(withdrawalsState)) {
			sb.append(" AND withdrawalsState='" + withdrawalsState + "'");
		}
		if (!ParaClick.clickString(condition)) {
			sb.append(" AND ((distridutionId LIKE '%" + condition
					+ "%')OR(remarks LIKE '%" + condition + "%'))");
		}
		sb.append("AND withdrawalsDate>='" + startDate
				+ " 00:00:00' AND  withdrawalsDate<='" + endDate
				+ " 23:59:59' ");
		System.out.println(sb.toString());
		return super.gettotalpage(sb.toString());
	}

	@Override
	public List<DistributorWithdrawals> selectDistributorWithdrawals(
			String condition, String nowPage, String pageNum,
			String withdrawalsState, String startDate, String endDate) {
		// 查看分销商结算单列表
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM DistributorWithdrawals WHERE 1=1 AND withdrawalsMoney>0 ");
		if (!ParaClick.clickString(withdrawalsState)) {
			sb.append(" AND withdrawalsState='" + withdrawalsState + "'");
		}
		if (!ParaClick.clickString(condition)) {
			sb.append(" AND ((distridutionId LIKE '%" + condition
					+ "%')OR(remarks LIKE '%" + condition + "%')) ");
		}
		sb.append(" AND withdrawalsDate>='" + startDate
				+ " 00:00:00' AND  withdrawalsDate<='" + endDate
				+ " 23:59:59' ");
		sb.append(" ORDER BY impldate DESC LIMIT "
				+ (Integer.parseInt(nowPage) - 1) * Integer.parseInt(pageNum)
				+ "," + Integer.parseInt(pageNum) + "");
		return SQL(sb.toString(), DistributorWithdrawals.class);
	}

	@Override
	public boolean finishDistributorWithdrawals(String id) {
		// 结算订单
		boolean flag = false;
		try {
			DistributorWithdrawals distributorWithdrawals = get(id);
			if (distributorWithdrawals.getWithdrawalsState() == 1) {
				return flag;
			}
			distributorWithdrawals.setWithdrawalsState(1);
			boolean flag2=distributorService.updateOverReCharge(distributorWithdrawals.getWithdrawalsMoney(),distributorWithdrawals.getDistridutionId());
			if (!flag2) {
				throw new BusinessException("异常");
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
}
