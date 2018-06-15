package com.qin.crxl.comic.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.service.AdminCartoonPhotoService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.RandomUtil;

@Controller
public class Test {
	@Autowired
	private AdminCartoonPhotoService adminCartoonPhotoService;
	
	
	private RandomUtil ran = new RandomUtil();
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(WeiXinAction.class.getName());

	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/addPhoto", method = RequestMethod.GET)
	public Model addCartoonPhotoService() {
		for (int i = 0; i < 800000; i++) {
			boolean bool = adminCartoonPhotoService.addCartoonPhotoPP();
			if (bool) {
				System.out.println("-----------------------------第"+i+"条------------------------------");
			}
		}
		System.out.println("-----------------------------完毕------------------------------");
		return new Model(200, "添加成功");
	}
	
}
