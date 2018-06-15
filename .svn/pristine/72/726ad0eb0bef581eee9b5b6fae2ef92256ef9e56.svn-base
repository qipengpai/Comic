package com.qin.crxl.comic.system;

import org.springframework.beans.factory.annotation.Autowired;

import com.qin.crxl.comic.service.UserOrderService;
import com.qin.crxl.comic.service.UserService;

public class TimeService {

	@Autowired
	private  UserService userService;
	@Autowired
	private  UserOrderService userOrderService;
	
	
	public void myTest(){
		if (userService.chackVip()) {
			System.out.println("----------------更新数据成功---------------");
		}else{
			System.out.println("----------------更新数据失败---------------");
		}
	}
	
	public void distributorTotal(){
		if (userOrderService.distributorOrderTotal()) {
			System.out.println("----------------更新DistributorOrderTotal数据成功---------------");
		}else{
			System.out.println("----------------更新DistributorOrderTotal数据失败---------------");
		}
	}
	public void distributorTotalYmd(){
		if (userOrderService.distributorTotalYmd()) {
			System.out.println("----------------更新distributorTotalYmd数据成功---------------");
		}else{
			System.out.println("----------------更新distributorTotalYmd数据失败---------------");
		}
	}
	public void distributorWithdrawals(){
		if (userOrderService.distributorWithdrawals()) {
			System.out.println("----------------更新distributorWithdrawals数据成功---------------");
		}else{
			System.out.println("----------------更新distributorWithdrawals数据失败---------------");
		}
	}
}
