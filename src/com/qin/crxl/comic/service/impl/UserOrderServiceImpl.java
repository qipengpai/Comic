package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Distributor;
import com.qin.crxl.comic.entity.Product;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.crxl.comic.entity.vo.UserOrderData;
import com.qin.crxl.comic.service.DistributorOrderService;
import com.qin.crxl.comic.service.DistributorService;
import com.qin.crxl.comic.service.DistributorTotalYmdService;
import com.qin.crxl.comic.service.DistributorWithdrawalsService;
import com.qin.crxl.comic.service.UserOrderService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.RandomUtil;
import com.qin.crxl.comic.exception.BusinessException;

@Service
public class UserOrderServiceImpl extends BaseServiceImpl<UserOrder> implements
		UserOrderService {

	@Autowired
	private DistributorService distributorService;
	@Autowired
	private DistributorOrderService distributorOrderTotalService;
	@Autowired
	private DistributorWithdrawalsService distributorWithdrawalsService;
	@Autowired
	private DistributorTotalYmdService distributorTotalYmdService;
	
	@Override
	public UserOrder updateOrder(String orderId) {
		List<UserOrder> list = SQL("SELECT * FROM UserOrder WHERE orderNum='"
				+ orderId + "'", UserOrder.class);
		if (list != null && list.size() > 0) {
			if (list.get(0).getOrderState()!=1) {
				list.get(0).setOrderState(1);
				list.get(0).setOrderImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			}		
			return list.get(0);
		}
		return null;
	}

	@Override
	public void cleanJF(String orderId) {
		UserOrder uo = get(orderId);
		uo.setOrderCurrency(0);
	}

	@Override
	public int getUserOrderNum(UserEntity userEntity) {
		// 產看訂單總數
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM UserOrder WHERE orderState= 1  AND orderUserId='"
				+ userEntity.getUserId() + "'");
		int num = gettotalpage(sb.toString());
		return num;
	}

	@Override
	public List<UserOrder> getALLUserOrderByUserId(UserOrderData userOrderData,
			UserEntity userEntity) {
		// 查看所有訂單
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM UserOrder WHERE orderState= 1 AND orderUserId='"
				+ userEntity.getUserId() + "'  ORDER BY orderDate DESC ");
		List<UserOrder> list = SQL(sb.toString(), UserOrder.class);
		return list;
	}

	@Override
	public int getUserOrderByTask(String userId, String openid) {
		// 查看是否首充
		int num = super
				.gettotalpage("SELECT COUNT(*) FROM UserOrder WHERE orderState= 1 AND orderUserId='"
						+ userId + "'");
		return num;
	}

	@Override
	public UserOrder getByOrderNum(String orderNum) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM UserOrder WHERE orderNum='" + orderNum + "'");
		List<UserOrder> list = SQL(sb.toString(), UserOrder.class);
		if (ParaClick.clickList(list)) {
			return list.get(0);
		}
		return null;
	}

	public static RandomUtil ran;

	@SuppressWarnings("static-access")
	@Override
	public boolean addUserOrder(Product product, String userId, String name,String qd) {
		// 生成有效订单
		boolean flag = true;
		try {
			UserOrder userOrder = new UserOrder();
			userOrder.setOrderUserId(userId);
			userOrder.setMchCreateIp("127.0.0.1");
			userOrder.setOrderDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userOrder.setOrderImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userOrder.setOrderUserName(name);
			userOrder.setOrderState(1);
			userOrder.setOrderMoney((int) product.getPrice());
			userOrder.setOrderCurrency(product.getCurrency());
			userOrder.setOrderDescription(product.getType());
			userOrder.setOrderIntegral(qd);
			userOrder.setOrderNum(ran.generateString(32));
			userOrder.setOrderRemarks(product.getIntroduc());
			userOrder.setOrderCurrency(product.getCurrency());
			super.save(userOrder);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean distributorOrderTotal() {
		// 定时统计每天各个渠道商的各项数据
		boolean flag = false;
		try {
			List<Distributor> list = distributorService.SQL(
					"SELECT * FROM Distributor WHERE state=1",
					Distributor.class);
			for (Distributor distributor : list) {
			List<Object[]> numSum = super
						.getSessionFactory()
						.createSQLQuery(
								"SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=101 AND TO_DAYS(NOW())-TO_DAYS(orderDate)=1").list();
			List<Object[]> numSum2 = super
					.getSessionFactory()
					.createSQLQuery(
								"SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
									+ distributor.getQd()
									+ "' AND orderDescription=102 AND TO_DAYS(NOW())-TO_DAYS(orderDate)=1").list();
		
			System.out.println("SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=101 AND TO_DAYS(NOW())-TO_DAYS(orderDate)=1 UNION SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=102 AND TO_DAYS(NOW())-TO_DAYS(orderDate)=1");
				boolean flag2=distributorOrderTotalService.addDistributorOrderTotal(numSum,distributor.getId(),numSum2);
				if (!flag2) {
					throw new BusinessException("异常");
				}
			}
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean distributorTotalYmd() {
		// 累计统计最近一个月和总充值
		boolean flag = false;
		try {
			List<Distributor> list = distributorService.SQL(
					"SELECT * FROM Distributor WHERE state=1",
					Distributor.class);
			for (Distributor distributor : list) {
				List<Object[]> numSum = getSessionFactory()
						.createSQLQuery(
								"SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=101 AND DATE_FORMAT(orderDate,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m')")
						.list();
				List<Object[]> numSum2 = getSessionFactory()
						.createSQLQuery("SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=102 AND DATE_FORMAT(orderDate,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m') ").list();
				boolean flag2 = distributorTotalYmdService
						.addDistributorOrderTotal(numSum, distributor.getId(),1,numSum2);
				if (!flag2) {
					throw new BusinessException("异常");
				}
				List<Object[]> sum = getSessionFactory()
						.createSQLQuery(
								"SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=101 ").list();
				List<Object[]> sum2 = getSessionFactory()
						.createSQLQuery("SELECT IFNULL(SUM(orderMoney),0),COUNT(*),COUNT(DISTINCT orderUserId) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "' AND orderDescription=102").list();
				
				boolean flag3 = distributorTotalYmdService
						.addDistributorOrderTotal(sum, distributor.getId(),2,sum2);
				if (!flag3) {
					throw new BusinessException("异常");
				}
			}
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean distributorWithdrawals() {
		// 生成待结算单
		boolean flag=true;
		try {
			List<Distributor> list = distributorService.SQL(
					"SELECT * FROM Distributor WHERE state=1",
					Distributor.class);
			for (Distributor distributor : list) {
				List<Object[]> numSum = getSessionFactory()
						.createSQLQuery(
								"SELECT IFNULL(SUM(orderMoney),0),COUNT(*) FROM UserOrder WHERE orderState=1 AND orderIntegral='"
										+ distributor.getQd()
										+ "'  AND TO_DAYS(NOW())-TO_DAYS(orderDate)=1 ").list();
				boolean flag2=distributorWithdrawalsService.addDistributorWithdrawals(numSum,distributor.getId(),distributor.getProportion(),distributor.getNickName());
				if (!flag2) {
					throw new BusinessException("异常");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public String addOrder(Product product, String userId, String userName,
			String qd) {
		// 生成ios支付订单
		UserOrder userOrder=new UserOrder();
		try {
			userOrder.setOrderUserId(userId);
			userOrder.setMchCreateIp("127.0.0.1");
			userOrder.setOrderDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userOrder.setOrderImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userOrder.setOrderUserName(userName);
			userOrder.setOrderState(0);
			userOrder.setOrderMoney((int) product.getPrice());
			userOrder.setOrderCurrency(product.getCurrency());
			userOrder.setOrderDescription(product.getType());
			userOrder.setOrderIntegral(qd);
			userOrder.setOrderNum(ran.generateString(32));
			userOrder.setOrderRemarks(product.getIntroduc());
			userOrder.setOrderCurrency(product.getCurrency());
			super.save(userOrder);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return userOrder.getOrderNum();
	}
}