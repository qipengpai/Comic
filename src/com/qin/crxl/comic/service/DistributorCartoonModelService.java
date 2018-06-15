package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.DistributionCartoonTitle;
import com.qin.crxl.comic.entity.DistributorCartoonModel;
import com.qin.crxl.comic.entity.vo.DistributorCartoonModelData;

@Service
@Transactional
public interface DistributorCartoonModelService extends BaseService<DistributorCartoonModel>{

	boolean addDistributorModel(String cartoonId, String string);

	boolean updateDistributorModel(
			DistributorCartoonModelData distributorCartoonModelData);

	boolean deleteDistributorNews(String id);

	int selectDistributorModelCount(String modelType);

	List<DistributorCartoonModel> selectDistributorModel(String modelType,
			String nowPage, String pageNum);


	
}
