package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.SearchHistory;
import com.qin.crxl.comic.service.SearchHistoryService;
@Service
public class SearchHistoryServiceImpl extends BaseServiceImpl<SearchHistory> implements SearchHistoryService {

	@Override
	public List<SearchHistory> getHistory(String userId) {
		List<SearchHistory> list= SQL("SELECT * from SearchHistory where userid='"+userId+"'  ORDER BY ImplDate DESC LIMIT 0,8", SearchHistory.class);
		
		return null;
	}

}
