package com.qin.crxl.comic.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonTaskData;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.service.UserTaskService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.wx.qm.Sign;
import com.qin.crxl.wx.qm.TickFile;

@Controller
public class WXFXAction {

	public static String APPID = "wx053e2bdaaf81ab7a";
	public static String APPSECRET = "0b6f996c50e98740d89f29d395fe1aad";
	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ APPID + "&secret=" + APPSECRET;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private UserService userService;

	// 凭证获取（GET）

	@ResponseBody
	@RequestMapping(value = ActionUrl.SHARE_WEIXIN, method = {
			RequestMethod.POST, RequestMethod.GET })
	protected Model business(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = request.getParameter("url");
		Map<String, String> params;
		try {
			params = Sign.sign(TickFile.getTicke(), url);
			System.out.println("sign----------  " + url);
			JSONObject jsonObject = JSONObject.fromObject(params);
			jsonObject.put("appid", APPID);
			String jsonStr = jsonObject.toString();
			System.out.println("jsonStr" + jsonStr);
			Model model = new Model();
			model.setError(200);
			model.setObj(jsonObject);
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * pp
	 * 
	 * @Remarks app>个人中心>完成分享漫画平台任务
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.SHARE_BACK, method = RequestMethod.POST)
	public Model bA(CartoonTaskData CartoonTaskData) throws Exception {
		CartoonTaskData.clickUser();
		UserEntity userEntity = userService.get(CartoonTaskData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		// 获取登录用户信息 分享平台
		boolean flag = userTaskService.finallyShare(CartoonTaskData.getUserId());
		if (!flag) {
			return new Model(500, "領取失敗");
		}
		return new Model(200, "成功");
	}

	/**
	 * pp
	 * 
	 * @Remarks app>个人中心>完成分享每部漫画任务
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.SHARE_COMIC_BACK, method = RequestMethod.POST)
	public Model bb(CartoonTaskData CartoonTaskData) throws Exception {
		CartoonTaskData.clickUser();
		UserEntity userEntity = userService.get(CartoonTaskData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		// 分享漫画
		boolean flag = userTaskService.finallyShareComic(CartoonTaskData.getUserId());
		if (!flag) {
			return new Model(500, "領取失敗");
		}
		return new Model(200, "成功");
	}
}
