package com.qin.crxl.comic.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.swiftpass.config.SwiftpassConfig;
import cn.swiftpass.util.SignUtils;
import cn.swiftpass.util.XmlUtils;

import com.ipaynow.pay.GateWayService;
import com.ipaynow.utils.MD5Facade;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.crxl.comic.entity.vo.Payment;
import com.qin.crxl.comic.entity.vo.UserOrderData;
import com.qin.crxl.comic.service.UserOrderService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.SystemConfig;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.RandomUtil;
import com.qin.crxl.comic.tool.StringToInt;

@Controller
public class PayServlet {

	private static Logger log = Logger.getLogger(Payservlettwo.class.getName());
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private UserService userService;

	private RandomUtil ran = new RandomUtil();

	/**
	 * H5支付地址
	 * 
	 * @param req
	 * @param resp
	 * @param session
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pay/payaction/playPoint", method = RequestMethod.POST)
	protected void doPost2(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		// 生成订单
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userId = req.getParameter("userId");
		UserEntity userEntity = userService.get(userId);
		if (userEntity == null) {

		}
		/* 订单号 */
		String rans = req.getParameter("out_trade_no");
		String money = req.getParameter("total_fee");
		String describe = req.getParameter("describe");
		String mch_create_ip = req.getParameter("mch_create_ip");
		String body = req.getParameter("body");
		String currency = req.getParameter("currency");
		// 初始化订单
		try {
			UserOrder uo = new UserOrder();
			uo.setOrderNum(rans);
			uo.setOrderMoney(Integer.parseInt(money) / 100);
			uo.setOrderDescription(describe);
			uo.setOrderRemarks(body);
			uo.setOrderUserId(userEntity.getUserId());
			uo.setOrderUserName(userEntity.getUsername());
			uo.setMchCreateIp(mch_create_ip);
			uo.setOrderState(0);
			uo.setOrderCurrency((Integer.parseInt(currency)));
			uo.setOrderIntegral(userEntity.getPlatformIndex());
			uo.setOrderDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userOrderService.save(uo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 做支付验证
		GateWayService service = new GateWayService();
		service.pay(req, resp, userEntity.getOpenid());
	}

	// 剧点支付回调@
	@ResponseBody
	@RequestMapping(value = "/pay/callback/playPoint", method = RequestMethod.POST)
	protected void callbackPlayPoint(HttpServletRequest req,
			HttpServletResponse resp, String orderNum) throws ServletException,
			IOException {
		try {
			// System.out.println("收到通知...");
			// req.setCharacterEncoding("utf-8");
			// resp.setCharacterEncoding("utf-8");
			// String respString = "200";
			// // 修改订单状态
			// UserOrder ret2 =userOrderService.getByOrderNum(orderNum);
			// if (ret2 == null) {
			// respString = "500";
			// } else {
			// if (ret2.getOrderState()!=1) {
			// UserOrder ret = userOrderService.updateOrder(orderNum);
			// UserEntity userEntity = userService.getUserInfoById(ret
			// .getOrderUserId());
			// if ("101".equals(ret.getOrderDescription())) {
			// UserEntity entity = userService.updateMoney(
			// userEntity.getUserId(), ret.getOrderCurrency());
			// if (entity != null) {
			// entity.setNickname(StringToInt.toString(entity
			// .getNickname()));
			// System.out.println("---------success支付回调:订单" + ret.getOrderNum()
			// + "  状态:" + ret.getOrderState() + "---------");
			// respString = "200";
			// } else {
			// System.out.println("---------error用户信息未获取---------");
			// respString = "500";
			// }
			// } else if ("102".equals(ret.getOrderDescription())) {
			// boolean flag = userService.updateVipState(
			// userEntity.getUserId(), ret.getOrderMoney());
			// if (!flag) {
			// System.out.println("---------error用户信息未获取---------");
			// respString = "500";
			// } else {
			// System.out.println("---------success支付回调:订单" + ret.getOrderNum()
			// + "  状态:" + ret.getOrderState() + "---------");
			// respString = "200";
			// }
			// } else {
			// respString = "500";
			// }
			// }
			// }
			// resp.getWriter().write(respString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/pay/callback/playPoint/ByWFT", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			System.out.println("收到威富通通知-----------------------------");
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			resp.setHeader("Content-type", "text/html;charset=UTF-8");
			String resString = XmlUtils.parseRequst(req);
			System.out.println("请求的内容：" + resString);
			String respString = "error";
			if (resString != null && !"".equals(resString)) {
				Map<String, String> map = XmlUtils.toMap(resString.getBytes(),
						"utf-8");
				String res = XmlUtils.toXml(map);
				System.out.println("请求结果====================================");
				System.out.println("请求结果：" + res);
				System.out.println("请求结果====================================");
				if (map.containsKey("sign")) {
					if (!SignUtils.checkParam(map, SwiftpassConfig.key)) {
						res = "验证签名不通过";
						respString = "error";
					} else {
						String status = map.get("status");
						if (status != null && "0".equals(status)) {
							String result_code = map.get("result_code");
							if (result_code != null && "0".equals(result_code)) {
								String orderNum = map.get("out_trade_no");
								System.out.println(orderNum);
								UserOrder ret = userOrderService.updateOrder(orderNum);
								if (ret == null) {
									respString = "error";
								} else {
									UserEntity userEntity = userService.getUserInfoById(ret
													.getOrderUserId());
									if ("101".equals(ret.getOrderDescription())) {
										UserEntity entity = userService.updateMoney(userEntity.getUserId(),
														ret.getOrderCurrency());
										if (entity != null) {
											entity.setNickname(StringToInt
													.toString(entity
															.getNickname()));
											System.out.println("---------WFT---------success支付回调:订单"+ ret.getOrderNum()
															+ "  状态:"
															+ ret.getOrderState()
															+ "---------");
											respString = "success";
										} else {
											System.out.println("-------WFT---------error---------");
											respString = "error";
										}
									} else if ("102".equals(ret.getOrderDescription())) {
										boolean flag = userService.updateVipState(userEntity.getUserId(),
														ret.getOrderMoney());
										if (flag) {System.out.println("---------WFT---------success支付回调:订单"
															+ ret.getOrderNum()+ "  状态:"+ ret.getOrderState()+ "---------");
											respString = "success";
										} else {
											System.out.println("-------WFT---------error---------");
											respString = "error";
										}
									} else {
										System.out.println("-------WFT---------无效订单error---------");
										respString = "error";
									}
								}
							}
						}
					}
				}
			}
			resp.getWriter().write(respString);
		} catch (Exception e) {
			log.error("操作失败，原因：", e);
		}
	}

	/**
	 * 會員支付地址
	 * 
	 * @param req
	 * @param resp
	 * @param session
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pay/payaction/vip", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp,
			UserOrderData userOrderData) throws Exception {
		userOrderData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(userOrderData
				.getUserId());
		if (userEntity == null) {

		}
		// 生成订单
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		/* 订单号 */
		@SuppressWarnings("static-access")
		String rans = userOrderData.getOrderNum();
		String money = userOrderData.getOrderMoney();// 分
		// 初始化订单
		try {
			UserOrder uo = new UserOrder();
			uo.setOrderNum(rans);
			uo.setOrderMoney(Integer.parseInt(money) / 100);
			uo.setOrderDescription("充值会员");
			uo.setOrderRemarks("充值会员");
			uo.setOrderUserId(userEntity.getUserId());
			uo.setOrderState(0);
			uo.setOrderCurrency((Integer.parseInt(money)) / 100 * 100);
			uo.setOrderIntegral(userEntity.getUsername());
			uo.setOrderDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userOrderService.save(uo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 做支付验证
		GateWayService service = new GateWayService();
		service.pay(req, resp, userEntity.getOpenid());
	}

	// 會員支付回调
	@ResponseBody
	@RequestMapping(value = "/pay/callback", method = RequestMethod.POST)
	protected Model callback(HttpServletRequest req, String orderNum)
			throws ServletException, IOException {
		log.info("---------开始回调---------");
		System.err.println("##回调订单号:##" + orderNum);
		// 修改订单状态
		UserOrder ret = userOrderService.updateOrder(orderNum);
		if (ret == null) {
			new Model(500, "订单信息错误");
		}
		UserEntity userEntity = userService.getUserInfoById(ret
				.getOrderUserId());
		boolean flag = userService.updateVipState(userEntity.getUserId(),
				ret.getOrderMoney());
		if (!flag) {
			return new Model(500, "支付失败");
		}
		UserEntity user2 = userService.get(userEntity.getUserId());
		if (user2 != null) {
			user2.setNickname(StringToInt.toString(user2.getNickname()));
			log.info("---------支付回调:订单" + ret.getOrderNum() + "  状态:"
					+ ret.getOrderState() + "---------");
			log.info("---------用户信息:" + user2.toString() + "---------");
			return new Model(200, "支付完成");

		} else {
			log.info("---------用户信息未获取---------");
			return new Model(500, "支付失败");
		}

	}
}
