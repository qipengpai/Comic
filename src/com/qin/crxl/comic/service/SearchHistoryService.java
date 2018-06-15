package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.SearchHistory;

@Service
@Transactional
public interface SearchHistoryService extends BaseService<SearchHistory> {

	List<SearchHistory> getHistory(String userId);

}
