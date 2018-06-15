package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.MallCartoonOrder;
import com.qin.crxl.comic.entity.vo.MallCartoonOrderData;
import com.qin.crxl.comic.entity.vo.UserEntityData;
/**
 *用户用咔咔豆充值漫画订单的接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminMallCartoonOrderService extends BaseService<MallCartoonOrder>{
	//查询用户用咔咔豆购买订单（已购买）
	List<Object[]> selectMallCartoonOrder(MallCartoonOrderData mallCartoonOrderData);
	
	
	//计算用户用咔咔豆购买订单数量
	int getCount(MallCartoonOrderData mallCartoonOrderData);
	
	
	//查询每个漫画收益
	List<Object[]> cartoonMoneyWater(MallCartoonOrderData mallCartoonOrderData);
	
	//计算漫画收益的数量
	int getNum(MallCartoonOrderData mallCartoonOrderData);
	
	//计算总收益
	double moneyAll(MallCartoonOrderData mallCartoonOrderData);
	
	//各个公众号渠道进账统计(咔咔豆)
	List<Object[]> selectMallCartoonOrderByGongZhong(String startTime,String endTime);
	
	//查询每个漫画收益(统计)
	List<Object[]> cartoonMoneyWater(String startTime,
			String endTime);
	
	//查询每个漫画收益列表(统计)
	List<Object[]> cartoonMoneyWaterLie(UserEntityData userEntityData);
	
	//查询每个漫画收益列表(统计漫画数量)
	int cartoonNum(UserEntityData userEntityData);
	
	//查询每个漫画话的收益（统计）
	List<Object[]> cartoonSetMoneyWater(MallCartoonOrderData mallCartoonOrderData);
	
	//查询每个漫画话的收益（统计列表）
	List<Object[]> cartoonSetMoneyWaterLie(MallCartoonOrderData mallCartoonOrderData);
	
	//查询每个漫画话的收益（统计）
	int getNumLie(MallCartoonOrderData mallCartoonOrderData);
	
}
