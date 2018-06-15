package com.qin.crxl.comic.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.DistributorOrder;
import com.qin.crxl.comic.service.DistributorOrderService;
import com.qin.crxl.comic.service.DistributorService;
import com.qin.crxl.comic.service.DistributorTotalYmdService;

@Service
public class DistributorOrderImpl extends BaseServiceImpl<DistributorOrder>
		implements DistributorOrderService {

	@Autowired
	private DistributorService distributorService;

	@Autowired
	private DistributorTotalYmdService distributorTotalYmdService;

	@Override
	public boolean addDistributorOrderTotal(List<Object[]> numSum, String id, List<Object[]> numSum2) {
		// 增加统计
		boolean flag = false;
		DecimalFormat df = new DecimalFormat("#.0000");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		try {
			DistributorOrder distributorOrderTotal = new DistributorOrder();
			distributorOrderTotal.setDistridutionId(id);
			distributorOrderTotal.setImplDate(yesterday);
		
			distributorOrderTotal.setOrdinaryRecharge(Double.parseDouble(numSum
					.get(0)[0].toString()));
			distributorOrderTotal.setOrdinaryRechargeNum(Integer
					.parseInt(numSum.get(0)[1].toString()));
			distributorOrderTotal.setVipRecharge(Double.parseDouble(numSum2
					.get(0)[0].toString()));
			distributorOrderTotal.setVipRechargeNum(Integer.parseInt(numSum2
					.get(0)[1].toString()));
			distributorOrderTotal.setRechargePersonNum(Integer.parseInt(numSum
					.get(0)[2].toString())
					+ Integer.parseInt(numSum2.get(0)[2].toString()));
			Double sum=Double.parseDouble(numSum.get(0)[0]+"") + Double.parseDouble(numSum2.get(0)[0]+"");
			if (sum<=0) {
				distributorOrderTotal.setPerCapitaRecharge(0.0);
			}else{
				Double perSum=Double.parseDouble(numSum.get(0)[2]+"") + Double.parseDouble(numSum2.get(0)[2]+"");
				distributorOrderTotal
						.setPerCapitaRecharge(sum/perSum);
			}
			distributorOrderTotal.setTotalType(1);
			distributorOrderTotal
					.setRecharge(sum);
			save(distributorOrderTotal);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

}
