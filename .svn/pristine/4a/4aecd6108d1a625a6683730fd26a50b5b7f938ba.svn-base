package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Distributor;
import com.qin.crxl.comic.entity.vo.DistributorData;

@Service
@Transactional
public interface DistributorService extends BaseService<Distributor>{

	List<Distributor> getByQd(String qd);

	List<Distributor> getByUserName(String userName);

	boolean registerDistributor(DistributorData distributorData);

	boolean updateDistributorInfo(DistributorData distributorData);

	int getDistributorNum(String condition);

	List<Distributor> getDistributor(String nowPage, String condition, String pagenum);

	boolean updateReCharge(double sum, String id);

	boolean updateOverReCharge(double withdrawalsMoney, String id);


	
	
}
