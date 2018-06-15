package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.News;
import com.qin.crxl.comic.entity.Product;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.crxl.comic.entity.vo.NewsData;
import com.qin.crxl.comic.entity.vo.UserOrderData;
@Service
@Transactional
public interface UserOrderService extends BaseService<UserOrder> {

	UserOrder updateOrder(String orderId);

	void cleanJF(String orderId);

	int getUserOrderNum(UserEntity userEntity);

	List<UserOrder> getALLUserOrderByUserId(UserOrderData userOrderData, UserEntity userEntity);

	int getUserOrderByTask(String userId, String openid);

	UserOrder getByOrderNum(String orderNum);

	boolean addUserOrder(Product product, String string, String string2, String string3);

	String addOrder(Product product, String string, String string2, String string3);
	
	boolean distributorOrderTotal();

	boolean distributorWithdrawals();

	boolean distributorTotalYmd();

}
