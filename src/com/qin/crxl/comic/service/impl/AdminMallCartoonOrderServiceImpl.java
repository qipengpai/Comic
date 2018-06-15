package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.MallCartoonOrder;
import com.qin.crxl.comic.entity.vo.MallCartoonOrderData;
import com.qin.crxl.comic.entity.vo.UserEntityData;
import com.qin.crxl.comic.service.AdminMallCartoonOrderService;
import com.qin.crxl.comic.tool.ParaClick;

/**
 * 用户用咔咔豆兑换漫画实现类
 * 
 * @author cui
 * 
 */
@Service
public class AdminMallCartoonOrderServiceImpl extends
		BaseServiceImpl<MallCartoonOrder> implements
		AdminMallCartoonOrderService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectMallCartoonOrder(
			MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select o.id,o.orderNum,u.username,c.cartoonName,s.titile,o.orderMoney,o.orderDate FROM MallCartoonOrder o,Cartoon c,CartoonSet s,UserEntity u WHERE u.userId=o.userId  AND o.cartoonId=c.id AND o.cartoonSetId=s.id AND o.orderState=1");
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonId())) {
			sb.append(" AND c.cartoonName like '%"
					+ mallCartoonOrderData.getCartoonId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonSetId())) {
			sb.append(" AND s.titile like '%"
					+ mallCartoonOrderData.getCartoonSetId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" AND o.orderDate BETWEEN '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append(" ORDER BY o.orderDate DESC  LIMIT "
				+ (Integer.parseInt(mallCartoonOrderData.getNowPage()) - 1)
				* Integer.parseInt(mallCartoonOrderData.getPageNum()) + ","
				+ Integer.parseInt(mallCartoonOrderData.getPageNum()));
		System.out.println(sb.toString());
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public int getCount(MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(o.id) FROM MallCartoonOrder o,Cartoon c,CartoonSet s,UserEntity u WHERE u.userId=o.userId  AND o.cartoonId=c.id AND o.cartoonSetId=s.id AND o.orderState=1");
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonId())) {
			sb.append(" and c.cartoonName like '%"
					+ mallCartoonOrderData.getCartoonId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonSetId())) {
			sb.append(" and s.titile like '%"
					+ mallCartoonOrderData.getCartoonSetId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and o.orderDate BETWEEN '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		int gettotalpage = 0;
		System.out.println(sb.toString());
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cartoonMoneyWater(
			MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.id,c.cartoonName,SUM(orderMoney) from MallCartoonOrder mo,Cartoon c where mo.cartoonId=c.id");
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonId())) {
			sb.append(" and c.cartoonName like '%"
					+ mallCartoonOrderData.getCartoonId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and mo.orderDate BETWEEN '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append(" group by mo.cartoonId");
		sb.append(" LIMIT "
				+ (Integer.parseInt(mallCartoonOrderData.getNowPage()) - 1)
				* Integer.parseInt(mallCartoonOrderData.getPageNum()) + ","
				+ Integer.parseInt(mallCartoonOrderData.getPageNum()));
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public int getNum(MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(a.num) from (select count(*) num from MallCartoonOrder mo,Cartoon c where mo.cartoonId=c.id");
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonId())) {
			sb.append(" and c.cartoonName like '%"
					+ mallCartoonOrderData.getCartoonId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and mo.orderDate BETWEEN '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append(" group by mo.cartoonId) a");
		System.out.println(sb.toString());
		int gettotalpage = 0;
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}

	@Override
	public double moneyAll(MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select SUM(orderMoney) from (select o.id,o.orderNum,o.userId,c.cartoonName,s.titile,o.orderMoney,o.orderDate from MallCartoonOrder o,Cartoon c,CartoonSet s where o.cartoonId=c.id and o.cartoonSetId=s.id and orderState=1");
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonId())) {
			sb.append(" and c.cartoonName like '%"
					+ mallCartoonOrderData.getCartoonId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getCartoonSetId())) {
			sb.append(" and s.titile like '%"
					+ mallCartoonOrderData.getCartoonSetId() + "%'");
		}
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and o.orderDate BETWEEN '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append(") a,UserEntity u");
		sb.append(" where a.userId=u.userId");
		return super.getSUM(sb.toString());
	}

	// 各个公众号渠道进账统计(咔咔豆)
	@Override
	public List<Object[]> selectMallCartoonOrderByGongZhong(String startTime,
			String endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append("select u.PlatformIndex,sum(o.orderMoney) as a from UserEntity u,MallCartoonOrder o  where u.userId=o.userId and o.orderState=1  ");
		if (!ParaClick.clickString(startTime)
				&& !ParaClick.clickString(endTime)) {
			sb.append("  and  o.orderDate>='" + startTime + " 00:00:00"
					+ "'  and  o.orderDate<='" + endTime + " 23:59:59'");
		}
		sb.append(" GROUP BY u.PlatformIndex");
		@SuppressWarnings("unchecked")
		List<Object[]> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cartoonMoneyWater(String startTime, String endTime) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.id,c.cartoonName,SUM(mo.orderMoney) a from MallCartoonOrder mo,Cartoon c where mo.cartoonId=c.id and mo.orderState=1");
		if (!ParaClick.clickString(startTime)
				&& !ParaClick.clickString(endTime)) {
			sb.append(" and mo.orderDate between '" + startTime
					+ " 00:00:00' and '" + endTime + " 23:59:59'");
		}
		sb.append(" group by mo.cartoonId order by a DESC");
		System.out.println(sb.toString());
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cartoonMoneyWaterLie(UserEntityData userEntityData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.id,c.cartoonName,SUM(mo.orderMoney) a from MallCartoonOrder mo,Cartoon c where mo.cartoonId=c.id and mo.orderState=1");
		if (!ParaClick.clickString(userEntityData.getStarTime())
				&& !ParaClick.clickString(userEntityData.getEndTime())) {
			sb.append(" and mo.orderDate between '"
					+ userEntityData.getStarTime() + " 00:00:00' and '"
					+ userEntityData.getEndTime() + " 23:59:59'");
		}
		sb.append(" group by mo.cartoonId order by a DESC LIMIT "
				+ (Integer.parseInt(userEntityData.getNowPage()) - 1)
				* Integer.parseInt(userEntityData.getPageNum()) + ","
				+ Integer.parseInt(userEntityData.getPageNum()));
		List<Object[]> list = null;
		System.out.println(sb.toString());
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public int cartoonNum(UserEntityData userEntityData) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(f.id) from (select c.id,c.cartoonName,SUM(mo.orderMoney) a from MallCartoonOrder mo,Cartoon c where mo.cartoonId=c.id and mo.orderState=1");
		if (!ParaClick.clickString(userEntityData.getStarTime())
				&& !ParaClick.clickString(userEntityData.getEndTime())) {
			sb.append(" and mo.orderDate between '"
					+ userEntityData.getStarTime() + " 00:00:00' and '"
					+ userEntityData.getEndTime() + " 23:59:59'");
		}
		sb.append(" group by mo.cartoonId order by a DESC) f");
		System.out.println(sb.toString());
		return super.gettotalpage(sb.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cartoonSetMoneyWater(
			MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.CartoonName,s.titile,SUM(mo.orderMoney) from MallCartoonOrder mo,Cartoon c,CartoonSet s where mo.cartoonId=c.id and mo.orderState=1 and mo.CartoonSetId=s.id and mo.cartoonId='"
				+ mallCartoonOrderData.getCartoonId() + "'");
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and mo.orderDate between '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append("  GROUP BY mo.CartoonSetId order by s.sort ASC");
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> cartoonSetMoneyWaterLie(
			MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.CartoonName,s.titile,SUM(mo.orderMoney) from MallCartoonOrder mo,Cartoon c,CartoonSet s where mo.cartoonId=c.id and mo.CartoonSetId=s.id and mo.orderState=1 and mo.cartoonId='"
				+ mallCartoonOrderData.getCartoonId() + "'");
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and mo.orderDate between '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append("  GROUP BY mo.CartoonSetId order by s.sort ASC LIMIT "
				+ (Integer.parseInt(mallCartoonOrderData.getNowPage()) - 1)
				* Integer.parseInt(mallCartoonOrderData.getPageNum()) + ","
				+ Integer.parseInt(mallCartoonOrderData.getPageNum()));
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public int getNumLie(MallCartoonOrderData mallCartoonOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from (select c.CartoonName,s.titile,SUM(mo.orderMoney) from MallCartoonOrder mo,Cartoon c,CartoonSet s where mo.cartoonId=c.id and mo.CartoonSetId=s.id and mo.orderState=1 and mo.cartoonId='"
				+ mallCartoonOrderData.getCartoonId() + "'");
		if (!ParaClick.clickString(mallCartoonOrderData.getStarTime())
				&& !ParaClick.clickString(mallCartoonOrderData.getEndTime())) {
			sb.append(" and mo.orderDate between '"
					+ mallCartoonOrderData.getStarTime() + " 00:00:00' and '"
					+ mallCartoonOrderData.getEndTime() + " 23:59:59'");
		}
		sb.append(" GROUP BY mo.CartoonSetId order by s.sort ASC) a");
		return super.gettotalpage(sb.toString());
	}

}
