package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.News;
import com.qin.crxl.comic.entity.vo.NewsData;
import com.qin.crxl.comic.service.NewsService;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements
NewsService{

	@Override
//	@Cacheable(value="News",key="#root.methodName")
	public int getNewsNum() {
		// 查询消息总数
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM News WHERE state= 1 ");
		int num=super.gettotalpage("NewsNum",36000,sb.toString());
		return num;
	}

	@Override
//	@Cacheable(value="News",key="#nowPage")
	public List<News> getAllNews(String nowPage) {
		// 查询所有消息
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM News WHERE state= 1 ORDER BY implDate DESC LIMIT "+(Integer.parseInt(nowPage)-1)*10+",10");
		List<News> list=SQL("News"+nowPage,36000,sb.toString(),News.class);
 		return list;
	}

}
