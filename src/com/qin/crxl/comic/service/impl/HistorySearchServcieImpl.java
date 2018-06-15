package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.HistorySearch;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.service.HistorySearchServcie;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class HistorySearchServcieImpl extends BaseServiceImpl<HistorySearch>
		implements HistorySearchServcie {

	// @Override
	// @Cacheable(value="HistorySearch",key="#userId")
	// public List<Object[]> getHistory(String userId) {
	// StringBuffer sb = new StringBuffer();
	// sb.append("SELECT distinct content from HistorySearch where userid='"+userId+"'  ORDER BY implDate DESC ");//ORDER
	// BY implDate DESC LIMIT 0,8
	// @SuppressWarnings("unchecked")
	// List<Object[]> list = super.getSessionFactory()
	// .createSQLQuery(sb.toString()).list();
	// return list;
	// }

	@Override
//	@CacheEvict(value="HistorySearch",key="#userId")
	public boolean addHistory(String content, String userId) {
		// 增加历史记录
		boolean flag = false;
		try {
	//		List<HistorySearch> list = getHistorySerach(userId,content);
			List<HistorySearch> list = super.SQL(
					"SELECT * FROM HistorySearch WHERE userId='" + userId
							+ "' AND content='" + content + "'",
					HistorySearch.class);
			if (ParaClick.clickList(list)) {
				for (HistorySearch historySearch : list) {
					historySearch.setImplDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
				}
				return true;
			}
			HistorySearch historySearch = new HistorySearch();
			historySearch.setContent(content);
			historySearch.setUserId(userId);
			historySearch.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			historySearch.setState(1);
			historySearch.setSearchDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			super.save(historySearch);
			List<HistorySearch> list2 = getHistory(userId);
			if (!ParaClick.clickList(list2)) {
				return flag;
			}
			if (list2.size() > 8) {
				for (int i = 8; i < list2.size(); i++) {
					super.delete(list2.get(i).getId());
				}
			}
			//////////redis
		//	JedisUtil.batchDel("HS-"+userId);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

	@Override
//	@Cacheable(value="HistorySearch",key="#userId")
	public List<HistorySearch> getHistory(String userId) {
		// 查询用户历史查询记录
		//////////redis
		List<HistorySearch> list2 = super.SQL("SELECT * FROM HistorySearch WHERE userId='" + userId
						+ "'  ORDER BY implDate DESC", HistorySearch.class);
//		List<HistorySearch> list2 = super.SQL("HS-"+userId,36000,
//		"SELECT * FROM HistorySearch WHERE userId='" + userId
//				+ "'  ORDER BY implDate DESC", HistorySearch.class);
		return list2;

	}

}
