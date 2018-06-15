package com.ipaynow.pay;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipaynow.utils.MD5Facade;

/**
 * 支付表单数据准备
 * @author christ
 *
 */
public class ToPayServlet extends HttpServlet{
	
	private static final long serialVersionUID = -3240794927782965682L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//商户自己的订单数据
		String funcode = req.getParameter("funcode");//功能码
		String deviceType = req.getParameter("deviceType");//设备类型
		String appId = req.getParameter("appId");
		String mhtOrderNo = System.currentTimeMillis()+"";
		String version = req.getParameter("version");//版本
		String mhtOrderName = req.getParameter("mhtOrderName");//订单名称
		String mhtCurrencyType = req.getParameter("mhtCurrencyType");
		String mhtOrderAmt = req.getParameter("mhtOrderAmt");//订单金额
		String mhtOrderDetail = req.getParameter("mhtOrderDetail");//订单详情
		String mhtOrderType = req.getParameter("mhtOrderType");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String mhtOrderStartTime = dateFormat.format(new Date());//订单时间
		String notifyUrl = req.getParameter("notifyUrl");//后台回调地址
		String frontNotifyUrl = req.getParameter("frontNotifyUrl");//前台回调地址
		String mhtCharset = req.getParameter("mhtCharset");//支付编码
		String payChannelType = req.getParameter("payChannelType");//支付渠道13
		String outputType = req.getParameter("outputType");//输出格式0/1
		String appKey = req.getParameter("appKey");//md5 key
		String mhtReserved = req.getParameter("mhtReserved");//商户保留域
		String mhtSignType = req.getParameter("mhtSignType");//签名方法
		
		//做MD5签名
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("appId", appId);
		dataMap.put("deviceType", deviceType);
		dataMap.put("funcode",funcode);
		dataMap.put("mhtOrderNo", mhtOrderNo);
		dataMap.put("mhtOrderName", mhtOrderName);
		dataMap.put("version", version);
		dataMap.put("mhtCurrencyType", mhtCurrencyType);
		dataMap.put("mhtOrderAmt", mhtOrderAmt);
		dataMap.put("mhtOrderDetail", mhtOrderDetail);
		dataMap.put("mhtOrderType", mhtOrderType);
		dataMap.put("mhtOrderStartTime", mhtOrderStartTime);
		dataMap.put("notifyUrl", notifyUrl);
		dataMap.put("frontNotifyUrl", frontNotifyUrl);
		dataMap.put("mhtCharset", mhtCharset);
		dataMap.put("outputType", outputType);
		dataMap.put("payChannelType", payChannelType);
		dataMap.put("mhtSignType", mhtSignType);
		
		//商户保留域， 可以不用填。 如果商户有需要对每笔交易记录一些自己的东西，可以放在这个里面
		dataMap.put("mhtReserved", mhtReserved);
		String mhtSignature = MD5Facade.getFormDataParamMD5(dataMap, appKey, "UTF-8");
		
		req.setAttribute("appId", appId);
		req.setAttribute("mhtOrderNo", mhtOrderNo);
		req.setAttribute("mhtOrderName", mhtOrderName);
		req.setAttribute("mhtCurrencyType", mhtCurrencyType);
		req.setAttribute("version", version);
		req.setAttribute("mhtOrderAmt", mhtOrderAmt);
		req.setAttribute("mhtOrderDetail", mhtOrderDetail);
		req.setAttribute("mhtOrderType", mhtOrderType);
		req.setAttribute("mhtOrderStartTime", mhtOrderStartTime);
		req.setAttribute("notifyUrl", notifyUrl);
		req.setAttribute("frontNotifyUrl", frontNotifyUrl);
		req.setAttribute("mhtCharset", mhtCharset);
		req.setAttribute("mhtSignType", mhtSignType);
		req.setAttribute("mhtSignature", mhtSignature);
		req.setAttribute("funcode", funcode);
		req.setAttribute("deviceType", deviceType);
		req.setAttribute("outputType", outputType);
		req.setAttribute("payChannelType", payChannelType);
		req.setAttribute("mhtReserved", mhtReserved);
		req.getRequestDispatcher("topay.jsp").forward(req, resp);				
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
