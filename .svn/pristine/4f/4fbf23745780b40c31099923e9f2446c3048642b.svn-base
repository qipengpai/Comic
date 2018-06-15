package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.DistributionCartoonTitle;
import com.qin.crxl.comic.entity.vo.DistributorCartoonTitleData;

@Service
@Transactional
public interface DistributionCartoonTitleService extends BaseService<DistributionCartoonTitle>{

	boolean addDistributorTitle(String cartoonId, String cartoonTitle);

	boolean updateDistributorTitle(DistributorCartoonTitleData distributorCartoonTitleData);

	boolean deleteDistributorNews(String id);

	int selectDistributorTitleCount(String condition, String nowPage,
			String pageNum, String string);

	List<DistributionCartoonTitle> selectDistributorTitle(String condition,
			String nowPage, String pageNum, String string);

	
}
