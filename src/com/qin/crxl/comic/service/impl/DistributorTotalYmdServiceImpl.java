package com.qin.crxl.comic.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.DistributorTotalYmd;
import com.qin.crxl.comic.service.DistributorTotalYmdService;
import com.qin.crxl.comic.tool.DateUtil;

@Service
public class DistributorTotalYmdServiceImpl extends BaseServiceImpl<DistributorTotalYmd> implements DistributorTotalYmdService{

	@Override
	public boolean addDistributorOrderTotal(List<Object[]> numSum, String id,int i, List<Object[]> numSum2) {
		// 增加最近本月和总充值
		boolean flag=false;
		DecimalFormat df = new DecimalFormat("#.00");
		try {
			DistributorTotalYmd distributorTotalYmd=new DistributorTotalYmd();
			distributorTotalYmd.setDistridutionId(id);
			distributorTotalYmd.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			distributorTotalYmd.setOrdinaryRecharge(Double.parseDouble(numSum.get(0)[0].toString()));
			distributorTotalYmd.setOrdinaryRechargeNum(Integer.parseInt(numSum.get(0)[1].toString()));
			distributorTotalYmd.setVipRecharge(Double.parseDouble(numSum2.get(0)[0].toString()));
			distributorTotalYmd.setVipRechargeNum(Integer.parseInt(numSum2.get(0)[1].toString()));
			distributorTotalYmd.setRechargePersonNum(Integer.parseInt(numSum.get(0)[2].toString())+Integer.parseInt(numSum2.get(0)[2].toString()));
			Double sum=Double.parseDouble(numSum.get(0)[0].toString())+Double.parseDouble(numSum2.get(0)[0].toString());
			if (sum==0) {
				distributorTotalYmd
				.setPerCapitaRecharge(0.0);
			}else{
				Double sum2= Double.parseDouble(numSum.get(0)[2].toString()) + Double.parseDouble(numSum2.get(0)[2].toString());
				distributorTotalYmd
					.setPerCapitaRecharge(sum/sum2);
			}
			distributorTotalYmd.setTotalType(i);
			distributorTotalYmd.setRecharge(sum);
			save(distributorTotalYmd);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

}
