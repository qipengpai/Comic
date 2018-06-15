package com.qin.crxl.comic.service.impl;

import java.util.List;

import javassist.bytecode.LineNumberAttribute.Pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.HistoryRecord;
import com.qin.crxl.comic.entity.MallCartoonOrder;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.service.CartoonSetService;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.service.MallCartoonOrderService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.RandomUtil;

@Service
public class MallCartoonOrderServiceImpl extends
		BaseServiceImpl<MallCartoonOrder> implements MallCartoonOrderService {
	public static RandomUtil ran;
	@Autowired
	private HistoryRecordService historyRecordService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartoonService cartoonService;
	@Autowired
	private CartoonSetService cartoonSetService;

	@SuppressWarnings("static-access")
	@Override
	public boolean BuyThisCartoonSet(UserEntity userEntity,
			CartoonPhotoData cartoonPhotoData) {
		// 增加訂單
		boolean flag = true;
		try {
			MallCartoonOrder mallCartoonOrder = new MallCartoonOrder();
			mallCartoonOrder.setCartoonId(cartoonPhotoData.getCartoonId());
			mallCartoonOrder
					.setCartoonSetId(cartoonPhotoData.getCartoonSetId());
			mallCartoonOrder.setUserId(cartoonPhotoData.getUserId());
			mallCartoonOrder.setOrderMoney(Integer.parseInt(cartoonPhotoData
					.getPrice()));
			mallCartoonOrder.setOrderDate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			mallCartoonOrder.setOrderNum(ran.generateString(32));
			mallCartoonOrder.setOrderState(1);
			super.save(mallCartoonOrder);
			boolean flag3 = userService.deductTheIntegral(cartoonPhotoData,
					userEntity);
			if (!flag3) {
				throw new BusinessException("扣除積分異常");
			}
			/*
			 * boolean flag5 =
			 * cartoonService.addPlayCount(cartoonPhotoData.getCartoonId()); if
			 * (!flag5) { throw new BusinessException("增加歷史紀錄次數失敗"); } boolean
			 * flag4 =
			 * cartoonSetService.addPlayCount(cartoonPhotoData.getCartoonSetId
			 * ()); if (!flag4) { throw new BusinessException("增加每话紀錄次數失敗"); }
			 * boolean
			 * flag2=historyRecordService.addhistoryRecord(cartoonPhotoData,
			 * userEntity); if(!flag2){ throw new BusinessException("增加歷史記錄異常");
			 * }
			 */
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	@Override
	public boolean BuyThisCartoonSetByIos(String userId, String cartoonId,
			String id, int price) {
		// 土豪模式后买
		boolean flag = true;
		try {
			MallCartoonOrder mallCartoonOrder = new MallCartoonOrder();
			mallCartoonOrder.setCartoonId(cartoonId);
			mallCartoonOrder
					.setCartoonSetId(id);
			mallCartoonOrder.setUserId(userId);
			mallCartoonOrder.setOrderMoney(price);
			mallCartoonOrder.setOrderDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			mallCartoonOrder.setOrderNum(ran.generateString(32));
			mallCartoonOrder.setOrderState(1);
			super.save(mallCartoonOrder);
			boolean flag3 = userService.deductTheIntegralByIos(userId,price);
			if (!flag3) {
				throw new BusinessException("扣除積分異常");
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
	public MallCartoonOrder getHistory(String cartoonId, String id,
			String userId) {
		// 查询此用户是否买过次漫画的此集
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM MallCartoonOrder WHERE 1=1 AND orderState=1  ");
		sb.append(" AND cartoonId='" + cartoonId + "' AND cartoonSetId='" + id
				+ "' AND userId='" + userId + "'");
		List<MallCartoonOrder> list = SQL(sb.toString(), MallCartoonOrder.class);
		if (!ParaClick.clickList(list)) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<MallCartoonOrder> getByUser(String id, String userId) {
		//允鹏知道查询漫画
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM MallCartoonOrder WHERE 1=1 AND orderState=1  ");
		sb.append(" AND cartoonId='" + id + "' AND userId='" + userId + "'");
		List<MallCartoonOrder> list = SQL(sb.toString(), MallCartoonOrder.class);
		return list;
	}


	

}