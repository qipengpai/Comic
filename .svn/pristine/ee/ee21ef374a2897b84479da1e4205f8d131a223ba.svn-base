package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.DistributorNews;
import com.qin.crxl.comic.entity.vo.DistributorNewsData;

@Service
@Transactional
public interface DistributorNewsService extends BaseService<DistributorNews>{

	boolean addDistributorNews(String content, String title);

	boolean updateDistributorNews(DistributorNewsData distributorNewsData);

	boolean deleteDistributorNews(String id);

	List<DistributorNews> selectDistributorNews(String condition,
			String nowPage, String pageNum);

	int selectDistributorNewsCount(String condition, String nowPage,
			String pageNum);

	
}
