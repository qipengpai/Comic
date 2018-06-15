package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.News;
import com.qin.crxl.comic.entity.vo.NewsData;

/**
 * 消息接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminNewsService extends BaseService<News> {
	//增加消息
	boolean addNews(NewsData newsData);
	
	//删除消息
	boolean deleteNews(NewsData newsData);
	
	//修改消息
	boolean updateNews(NewsData newsData);
	
	//查询消息
	List<News> selectNews(NewsData newsData);
	
	//查询消息（id）
	News selectNewsById(NewsData newsData);
	
	
	//计算消息数量
	int getCountNews(NewsData newsData); 

}
