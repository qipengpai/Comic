package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.HistorySearch;
import com.qin.crxl.comic.entity.UserEntity;

@Service
@Transactional
public interface HistorySearchServcie extends BaseService<HistorySearch>{
	
	List<HistorySearch> getHistory(String userId);

	boolean addHistory(String content, String userId);
}