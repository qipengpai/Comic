package com.qin.crxl.comic.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonTask;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.CartoonTaskServcie;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;
import com.qin.weixin.dao.impl.WeiXinDaoImpl;
import com.qin.weixin.model.OAuthAccessToken;
import com.qin.weixin.model.useValue;

@Controller
public class WeiXinAction {

	@Autowired
	private UserService userService;

	private static Logger log = Logger.getLogger(WeiXinAction.class.getName());

	/**
	 * 微信登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Scope("prototype")
	@ResponseBody
	@RequestMapping(value = ActionUrl.WEIXINLOGIN, method = RequestMethod.POST)
	public Model doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Model model =new Model();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		String wxtp = "WZ";
		try {
			// 获取渠道
			wxtp = request.getParameter("qd");
		} catch (Exception e) {
		}
		log.info("code:" + code);
		WeiXinDaoImpl dao = new WeiXinDaoImpl();
		UserEntity entity = null;
		String lastLoginDate="";
		int hobby=0;
		try {
			OAuthAccessToken oac = dao.getOAuthAccessToken(useValue.AppId,
					useValue.AppSecret, code);// 通过code换取网页授权access_token
			log.info("--------------------" + oac.getAccessToken() + ";"
					+ oac.getRefreshToken() + ";" + oac.getScope() + ";"
					+ oac.getOpenid());
			OAuthAccessToken oacd = dao.refershOAuthAccessToken(useValue.AppId,
					oac.getRefreshToken());// 刷新access_token
			log.info("--------------------" + oacd.getAccessToken() + ";"
					+ oacd.getRefreshToken() + ";" + oacd.getScope()
					+ ";Openid:" + oacd.getOpenid());

			entity = dao.acceptOAuthUserNews(oacd.getAccessToken(),
					oacd.getOpenid());// 获取用户信息
			log.info("------微信拉去用户数据成功--------------" + entity.getNickname()
					+ ";" + entity.getCountry());
			String newTime = DateUtil.dateToTimeStamp(new Date());
			entity= userService.updateUser(entity, wxtp);
			if("".equals(entity.getHobby())||entity.getHobby()==null){
				hobby=0;
			}else{
				hobby=Integer.parseInt(entity.getHobby());
			}
			entity.setNickname(StringToInt.toString(entity.getNickname()));
//			log.info("------微信拉去用户数据成功--------------" + entity.getNickname()
//					+ ";" + entity.getCountry());
			//entity=userService.getByOpenId(entity.getOpenid());
			if (""==entity.getLastLoginDate()||"".equals(entity.getLastLoginDate())||DateUtil.getdate_yyyy_MM_dd_00_00_00(entity.getLastLoginDate()).getTime()<DateUtil.getdate_yyyy_MM_dd_00_00_00(newTime+" 00:00:00").getTime()) {
				lastLoginDate="1";
			}else{
				lastLoginDate="0";
			}
			boolean flag=userService.setLoginDate(entity.getUserId());
			if(!flag){
				model.setError(200);
				model.setObj(entity.getUserId());
				model.setSpare(lastLoginDate);
				model.setObj2(hobby);
				return model;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("-------------------微信拉去用户数据失败--------------------------");
		}
		//log.info("-------------------用户信息-" + entity.toString());
		//return new Model(200, entity);
		// 跳转到界面
		// 找不到*.html
		//response.sendRedirect("http://ptest.edisonluorui.com/index.html?userid="+entity.getUserId()+"&lastLoginDate=");
		// request.getRequestDispatcher("http://ptest.edisonluorui.com/cartoon/index.html?userid="+entity.getUserId()).forward(request, response);
		model.setError(200);
		model.setObj(entity.getUserId());
		model.setSpare(lastLoginDate);
		model.setObj2(hobby);
		return model;
		
	}
}