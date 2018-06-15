package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.DistributionCartoonTitle;
import com.qin.crxl.comic.entity.DistributorWithdrawals;

@Service
@Transactional
public interface DistributorWithdrawalsService extends BaseService<DistributorWithdrawals>{

	boolean addDistributorWithdrawals(List<Object[]> numSum, String id,
			double proportion, String string);

	int selectDistributorWithdrawalsCount(String condition, String withdrawalsState, String startDate, String endDate);

	List<DistributorWithdrawals> selectDistributorWithdrawals(String condition,
			String nowPage, String pageNum, String withdrawalsState, String startDate, String endDate);

	boolean finishDistributorWithdrawals(String id);

	

}
