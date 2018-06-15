package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.News;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.crxl.comic.entity.vo.NewsData;
import com.qin.crxl.comic.entity.vo.UserOrderData;
import com.qin.crxl.comic.service.UserOrderService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class UserOrderController {

	@Autowired
	private  UserOrderService  userOrderService;
	@Autowired
	private  UserService  userService;
	
	/**
	 * pp
	 * @Remarks app>個人中心>充值中新>我的訂單
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.USER_MY_ORDER, method = RequestMethod.POST)
	public Model userUplod(UserOrderData userOrderData) throws Exception {
		userOrderData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(userOrderData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		Model model=new Model();
		
		List<UserOrder> list=userOrderService.getALLUserOrderByUserId(userOrderData,userEntity);
		if (!ParaClick.clickList(list)) {
			return new Model(200,"暫無订单");
		}
		model.setError(200);
		model.setObj(list);
		return model;
	}
	
	
}