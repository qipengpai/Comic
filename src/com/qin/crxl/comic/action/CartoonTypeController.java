package com.qin.crxl.comic.action;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonTypeData;
import com.qin.crxl.comic.service.CartoonTypeService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class CartoonTypeController {
	
	@Autowired
	private  CartoonTypeService cartoonTypeService;
	@Autowired
	private UserService userService;
	/* 1.用@CachePut处理，这中方法需要对指定缓存key保持一致，尽管这样，还是不行，因为它返回的缓存是int(增加或删除或修改的记录数或是该记录的对象，这对我们查询所有或部分记录的缓存还是不可行的)

       2.用@CacheEvict(value="myCache",key="0",beforeInvocation=true)处理，清除我们指定key的缓存，这种方式缺点是麻烦，需要我们注意每一个缓存的key

       3.用@CacheEvict(value="myCache",allEntries=true,beforeInvocation=true)处理，清除所有缓存，这种方式最省事，但会把其他缓存也一同清除。*/
	

	/**
	 * pp
	 * 
	 * @Remarks 发现>查看所有小说类型
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_ALL_CARTOOMTYPE, method = RequestMethod.POST)
	public Model getAllCartoonCotYPE(
			CartoonTypeData cartoonTypeData) throws Exception {
		Model model = new Model();
		cartoonTypeData.clickUser();
		UserEntity userEntity = userService.get(cartoonTypeData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
//		if (ParaClick.clickString(cartoonTypeData.getNowPage())) {
//			cartoonTypeData.setNowPage("1");
//		}
//	    int num=cartoonTypeService.gettotalNum(cartoonTypeData);
		List<CartoonType> list2=cartoonTypeService.getAllCartoonType();
		if (!ParaClick.clickList(list2)) {
			return new Model(500, "无數據");
		}
		model.setError(200);
	//	model.setNowpage(Integer.parseInt(cartoonTypeData.getNowPage()));
	//	model.setTotalpage((num + 10 - 1) / 10);
		
		model.setObj(list2);
		return model;
	}
}
