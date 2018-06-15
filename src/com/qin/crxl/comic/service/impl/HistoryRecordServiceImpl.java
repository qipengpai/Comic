package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.HistoryRecord;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class HistoryRecordServiceImpl extends BaseServiceImpl<HistoryRecord>
		implements HistoryRecordService {

	@Override
	public int getWatchCount(Cartoon cartoon) {
		// 查看漫画观看人数
		int num = super
				.gettotalpage("SELECT COUNT(*) FROM HistoryRecord WHERE cartoonId='"
						+ cartoon.getId() + "'");
		return num;
	}

	@Override
	// @CacheEvict(value = "HistoryRecord", key =
	// "#cartoonId.concat(#cartoonSetId).concat(#userId)")
	public boolean addhistoryRecord(String cartoonId, String cartoonSetId,
			String userId) {
		// 增加歷史紀錄
		boolean flag = false;
		try {
			if (ParaClick.clickString(cartoonId)) {
				return flag;
			}
			if (ParaClick.clickString(cartoonSetId)) {
				return flag;
			}
			if (ParaClick.clickString(userId)) {
				return flag;
			}
			// List<HistoryRecord> list = getWatchStateByUserAndCartoonSetId(
			// cartoonId, cartoonSetId, userId);
			// if (!ParaClick.clickList(list)) {
			HistoryRecord historyRecord = new HistoryRecord();
			historyRecord.setCartoonId(cartoonId);
			historyRecord.setCartoonSetId(cartoonSetId);
			historyRecord.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			historyRecord.setUserId(userId);
			historyRecord.setWatchState(2);
			save(historyRecord);
			// }
			// else {
			// for (int i = 0; i < list.size(); i++) {
			// HistoryRecord historyRecord = super
			// .get(list.get(i).getId());
			// historyRecord.setImplDate(DateUtil
			// .getdate_yyyy_MM_dd_HH_MM_SS());
			// historyRecord.setWatchState(2);
			// }
			// }
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

	@Override
	// @Cacheable(value = "HistoryRecord", key =
	// "#cartoonId.concat(#id).concat(#userId)")
	public List<HistoryRecord> getWatchStateByUserAndCartoonSetId(
			String cartoonId, String id, String userId) {
		// 根据SET id 和用户查询历史记录
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM HistoryRecord WHERE 1=1 ");
		sb.append(" AND cartoonId='" + cartoonId + "' AND cartoonSetId='" + id
				+ "' AND userId='" + userId + "'");
		// sb.append(" AND cartoonId='" + cartoonId + "' AND  userId='" + userId
		// + "' ORDER BY implDate DESC");
		List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);
		return list;
	}

	@Override
	// @Cacheable(value = "HistoryRecord", key =
	// "#cartoonId.concat(#id).concat(#userId)")
	public List<HistoryRecord> getWatchStateByUserAndCartoonId(
			String cartoonId, String userId) {
		// 根据SET id 和用户查询历史记录
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM HistoryRecord WHERE 1=1 ");
		sb.append(" AND cartoonId='" + cartoonId + "' AND userId='" + userId
				+ "'ORDER BY implDate DESC limit 0,1");
		List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);
		return list;
	}

	@Override
	// @CacheEvict(value="HistoryRecord",key="#cartoonId.concat(#userId)",allEntries=true)
	public boolean updateState(String cartoonId, String userId) {
		// 查看之前关于这个漫画的历史记录
		boolean flag = false;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM HistoryRecord WHERE 1=1 ");
			sb.append(" AND cartoonId='" + cartoonId + "' AND userId='"
					+ userId + "' ORDER BY implDate DESC");
			List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);

			if (!ParaClick.clickList(list)) {
				flag = true;
			} else {
				for (HistoryRecord historyRecord : list) {
					delete(historyRecord.getId());
				}
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int getAllHistoryRecordCount(UserEntity userEntity,
			CartoonData cartoonData) {
		// 查询历史记录次数
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT c.id) FROM HistoryRecord as h,Cartoon as c WHERE c.id=h.cartoonId AND c.state=1 ");
		sb.append(" AND userId='" + userEntity.getUserId() + "'");
		int num = super.gettotalpage(sb.toString());
		return num;
	}

	@Override
	public List<Object> getAllHistoryRecord(UserEntity userEntity,
			CartoonData cartoonData) {
		// 查询所有历史记录
		StringBuffer sb = new StringBuffer();
		/*
		 * sb.append("SELECT * FROM HistoryRecord  WHERE 1=1 ");
		 * sb.append(" AND userId='" + userEntity.getUserId() +
		 * "' OEDER BY implDate DESC LIMIT " +
		 * (Integer.parseInt(cartoonData.getNowPage()) - 1) * 10 + ",10");
		 */
		sb.append("SELECT DISTINCT c.id FROM HistoryRecord as h,Cartoon as c WHERE c.id=h.cartoonId AND c.state=1 ");
		sb.append(" AND h.userId='" + userEntity.getUserId()
				+ "' ORDER BY h.implDate DESC LIMIT "
				+ (Integer.parseInt(cartoonData.getNowPage()) - 1) * 10 + ",10");
		// List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);
		@SuppressWarnings("unchecked")
		List<Object> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}

	@Override
	// @CacheEvict(value="HistoryRecord",key="#cartoonSet.cartoonId.concat(#userEntity.userId)",allEntries=true)
	public boolean updateState2(CartoonSet cartoonSet, UserEntity userEntity) {
		// 查看之前关于这个漫画的历史记录
		boolean flag = false;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT * FROM HistoryRecord WHERE 1=1 ");
			sb.append(" AND cartoonId='" + cartoonSet.getCartoonId()
					+ "' AND userId='" + userEntity.getUserId()
					+ "' ORDER BY implDate DESC");
			List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);
			if (!ParaClick.clickList(list)) {
				flag = true;
			} else {
				for (HistoryRecord historyRecord : list) {
					delete(historyRecord.getId());
				}
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addhistoryRecord2(CartoonSet cartoonSet,
			UserEntity userEntity) {
		// 增加歷史紀錄
		boolean flag = false;
		try {
			if (ParaClick.clickString(cartoonSet.getCartoonId())) {
				return flag;
			}
			if (ParaClick.clickString(cartoonSet.getId())) {
				return flag;
			}
			if (ParaClick.clickString(userEntity.getUserId())) {
				return flag;
			}
			List<HistoryRecord> list = SQL(
					"SELECT * FROM HistoryRecord WHERE cartoonId='"
							+ cartoonSet.getCartoonId()
							+ "' AND cartoonSetId='" + cartoonSet.getId()
							+ "' AND userId='" + userEntity.getUserId() + "'",
					HistoryRecord.class);
			if (!ParaClick.clickList(list)) {
				HistoryRecord historyRecord = new HistoryRecord();
				historyRecord.setCartoonId(cartoonSet.getCartoonId());
				historyRecord.setCartoonSetId(cartoonSet.getId());
				historyRecord.setImplDate(DateUtil
						.getdate_yyyy_MM_dd_HH_MM_SS());
				historyRecord.setUserId(userEntity.getUserId());
				historyRecord.setWatchState(2);
				save(historyRecord);
			} else {
				for (int i = 0; i < list.size(); i++) {
					HistoryRecord historyRecord = super
							.get(list.get(i).getId());
					historyRecord.setImplDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					historyRecord.setWatchState(2);
				}
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

	@Override
	public List<HistoryRecord> getHistoryByCartoonId(String cartoonId,
			UserEntity userEntity) {
		// 根據cartoonid 查詢歷史
		List<HistoryRecord> list = super.SQL(
				"SELECT * FROM HistoryRecord WHERE cartoonId='" + cartoonId
						+ "' AND userId='" + userEntity.getUserId()
						+ "' ORDER BY implDate DESC", HistoryRecord.class);
		return list;
	}

	@Override
	public List<HistoryRecord> getCartoonHistoryRecord(UserEntity userEntity,
			CartoonData cartoonData) {
		// 查看用户的续看
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM HistoryRecord WHERE 1=1 ");
		sb.append(" AND userId='" + userEntity.getUserId()
				+ "'  AND cartoonId='" + cartoonData.getId()
				+ "' ORDER BY implDate DESC LIMIT 0,1 ");
		List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);
		return list;
	}

	@Override
	public List<HistoryRecord> getHisToday(String userId) {
		// 查看用户今天是否看了两部漫画
		List<HistoryRecord> list = super.SQL(
				"SELECT * FROM HistoryRecord WHERE  userId='" + userId
						+ "' AND  implDate LIKE '%"
						+ DateUtil.getdate_yyyy_MM_dd()
						+ "%' GROUP BY cartoonId", HistoryRecord.class);
		return list;
	}

	@Override
	public int getHistoryByCartoonIdCount(String id, UserEntity userEntity) {
		// 查询历史记录次数
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM HistoryRecord ");
		sb.append(" WHERE  userId='" + userEntity.getUserId()
				+ "' AND cartoonId='" + id + "'");
		int num = super.gettotalpage(sb.toString());
		return num;
	}

	@Override
	public int getAllHistoryRecordCountByIos(UserEntity userEntity,
			CartoonData cartoonData) {
		// ios查询历史记录次数
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(DISTINCT c.id) FROM HistoryRecord as h,Cartoon as c WHERE c.id=h.cartoonId AND c.state=1 AND c.iosState=1 ");
		String flag="APP_IOS";
		if (userEntity.getPlatformIndex().equals("APPLE")) {
			flag="APPLE";
			sb.append(" AND c.updateType=1");
		}
		sb.append(" AND userId='" + userEntity.getUserId() + "'");
		int num = super.gettotalpage(sb.toString());
		return num;
	}

	@Override
	public List<Object> getAllHistoryRecordByIos(UserEntity userEntity,
			CartoonData cartoonData) {
		// ios查询所有历史记录
		StringBuffer sb = new StringBuffer();

		sb.append("SELECT DISTINCT c.id FROM HistoryRecord as h,Cartoon as c WHERE c.id=h.cartoonId AND c.state=1 AND c.iosState=1 ");
		String flag="APP_IOS";
		if (userEntity.getPlatformIndex().equals("APPLE")) {
			flag="APPLE";
			sb.append(" AND c.updateType=1");
		}
		sb.append(" AND h.userId='" + userEntity.getUserId()
				+ "' ORDER BY h.implDate DESC LIMIT "
				+ (Integer.parseInt(cartoonData.getNowPage()) - 1) * 10 + ",10");
		// List<HistoryRecord> list = SQL(sb.toString(), HistoryRecord.class);
		@SuppressWarnings("unchecked")
		List<Object> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}

}
