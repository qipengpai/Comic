package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.UserEntityData;
import com.qin.crxl.comic.service.AdminUserEntityService;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;
@Service
public class AdminUserEntityServiceImpl extends BaseServiceImpl<UserEntity> implements
		AdminUserEntityService {

	@Override
	public List<UserEntity> selectUser(UserEntityData userEntityData) {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from UserEntity where 1=1");
		if (!ParaClick.clickString(userEntityData.getCondition())) {
			sb.append(" AND ((userId LIKE '%"+userEntityData.getCondition().trim()+"%') OR (username LIKE '%"+StringToInt.toInt(userEntityData.getCondition().trim())+"%') OR (truePhone LIKE '%"+userEntityData.getCondition().trim()+"%') OR (city LIKE '"+StringToInt.toInt(userEntityData.getCondition().trim())+"'))");
		}
		sb.append(" order by vipId DESC,integral DESC limit "+ (Integer.parseInt(userEntityData.getNowPage())-1) * Integer.parseInt(userEntityData.getPageNum()) + ","
				+ Integer.parseInt(userEntityData.getPageNum()));
		List<UserEntity> list=null;;
		try {
			list = SQL(sb.toString(),UserEntity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public int getCount(UserEntityData userEntityData) {
		int gettotalpage=0;
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from UserEntity where 1=1");
		if (!ParaClick.clickString(userEntityData.getCondition())) {
			sb.append(" AND ((userId LIKE '%"+userEntityData.getCondition().trim()+"%') OR (userName LIKE '%"+StringToInt.toInt(userEntityData.getCondition().trim())+"%') OR (truePhone LIKE '%"+userEntityData.getCondition().trim()+"%') OR (city LIKE '"+StringToInt.toInt(userEntityData.getCondition().trim())+"'))");
		}
		try {
			gettotalpage = super.gettotalpage(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}

	@Override
	public UserEntity selectUserByID(String id) {
		return super.get(id);
	}
	
	//查询粉丝性别（统计）
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectSexGroupBy() {
		StringBuffer sb=new StringBuffer();
		sb.append("select sex,count(*) from UserEntity GROUP BY sex");
		List<Object[]> list=null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	//查询粉丝用户分布
	@Override
	public List<Object[]> getCircleByArea() {
		StringBuffer sb = new StringBuffer();
		sb.append("select city,count(*) as a from UserEntity GROUP BY city ORDER BY a desc");
		@SuppressWarnings("unchecked")
		List<Object[]> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}
	
	//查询粉丝用户分布(分页)
	@Override
	public List<Object[]> getCircleByArea(UserEntityData userEntityData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select city,count(*) as a from UserEntity GROUP BY city ORDER BY a desc");
		sb.append(" limit "+ (Integer.parseInt(userEntityData.getNowPage())-1) * Integer.parseInt(userEntityData.getPageNum()) + ","
				+ Integer.parseInt(userEntityData.getPageNum()));
		System.out.println(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}

	//公众号粉丝分布
	@Override
	public List<Object[]> getFansByGongZhong() {
		StringBuffer sb = new StringBuffer();
		sb.append("select platformIndex,count(*) as a from UserEntity GROUP BY platformIndex ORDER BY a desc");
		@SuppressWarnings("unchecked")
		List<Object[]> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}

	@Override
	public int areaNum(UserEntityData userEntityData) {
		return super.gettotalpage("select count(*) FROM (select city as c from UserEntity GROUP BY city) a");
	}

	

	
}
