package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonAllTypeVo;
import com.qin.crxl.comic.service.AdminCartoonAllTypeService;
import com.qin.crxl.comic.service.AdminCartoonTypeService;
import com.qin.crxl.comic.system.ActionUrl;
/**
 * 漫画所有类型的控制类
 * @author cui
 *
 */
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
/**
 * 漫画所有类型的控制类
 * @author cui
 *
 */
@Controller
public class AdminCartoonAllTypeController {
	@Autowired
	private  AdminCartoonAllTypeService  adminCartoonAllTypeService;
	@Autowired
	private  AdminCartoonTypeService  adminCartoonTypeService;
	
	
	
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOONALLTYPE, method = RequestMethod.POST)
	public Model addCartoonAllType(CartoonAllTypeVo cartoonAllTypeVo){
		String cartoonTypeId=cartoonAllTypeVo.getCartoonTypeId();
		String[] strs = cartoonTypeId.split(",");
		boolean bool = adminCartoonAllTypeService.addCartoonAllType(cartoonAllTypeVo.getCartoonId(), strs);
		if(bool){
			return new Model(200,"添加成功");
		}
		return new Model(500,"添加失败");
	}
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONALLTYPE, method = RequestMethod.POST)
	public Model selectCartoonAllType(CartoonAllTypeVo cartoonAllTypeVo){
		List<CartoonType> allCartoonType = adminCartoonTypeService.allCartoonType();
		if(ParaClick.clickList(allCartoonType)){
			return new Model(200,allCartoonType);
		}
		return new Model(500,"查询失败");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
