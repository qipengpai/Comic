package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonTypeVo;
import com.qin.crxl.comic.service.AdminCartoonTypeService;
import com.qin.crxl.comic.service.CartoonTypeService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

/**
 * 漫画类型控制器
 * 
 * @author cui
 * 
 */
@Controller
public class AdminCartoonTypeController {

	@Autowired
	private AdminCartoonTypeService cartoonTypeCService;

	@Autowired
	private CartoonTypeService  cartoonTypeService;
	// 添加漫画类型
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOONTYPE, method = RequestMethod.POST)
	public Model addCartoonType(CartoonTypeVo cartoonTypeData) {
		boolean addCartoonType = cartoonTypeCService
				.addCartoonType(cartoonTypeData);
		if (addCartoonType) {
			return new Model(200, "添加成功");
		}
		return new Model(500, "添加失败");
	}

	// 修改漫画类型
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONTYPE, method = RequestMethod.POST)
	public Model updateCartoonType(CartoonTypeVo cartoonTypeData) {
		if(!ParaClick.clickString(cartoonTypeData.getShowNum())){
			int[] updateSort = cartoonTypeCService.updateSort(cartoonTypeData);
			if(updateSort.length==1){
				return new Model(500,"不可进行此操作");
			}else{
				boolean bool = cartoonTypeCService.cartoonChangeSort(updateSort);
				if(bool){
					return new Model(200,"移动成功");
				}
				return new Model(500,"移动失败");
			}
		}
		boolean updateCartoonType = cartoonTypeCService
				.updateCartoonType(cartoonTypeData);
		if (updateCartoonType) {
			return new Model(200, "修改成功");
		}
		return new Model(500, "修改失败");
	}

	// 查询漫画类型
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONTYPEID, method = RequestMethod.POST)
	public Model selectCartoonTypeId(CartoonTypeVo cartoonTypeData) {
		CartoonType selectCartoonType = cartoonTypeCService
				.selectCartoonType(cartoonTypeData.getId());
		if (ParaClick.clickObj(selectCartoonType)) {
			return new Model(200, selectCartoonType);
		}
		return new Model(500, "查询失败");
	}

	// 查询漫画所有类型
	@ResponseBody
	@RequestMapping(value = ActionUrl.ALLSELECT_CARTOONTYPE, method = RequestMethod.POST)
	public Model selectCartoonTypeAll(CartoonTypeVo cartoonTypeData) {
		List<CartoonType> allCartoonTypeList = cartoonTypeService
				.getAllCartoonType();
		if (ParaClick.clickList(allCartoonTypeList)) {
			return new Model(200, allCartoonTypeList);
		}
		return new Model(500, "查询失败");
	}

	// 删除漫画类型
	@ResponseBody
	@RequestMapping(value = ActionUrl.DELETE_CARTOONTYPE, method = RequestMethod.POST)
	public Model deleteCartoonTypeAll(CartoonTypeVo cartoonTypeData) {
		if(ParaClick.clickString(cartoonTypeData.getId())){
			return new Model(500, "删除失败");
		}
		boolean bool = cartoonTypeCService.deleteCartoonType(cartoonTypeData);
		if(bool){
			return new Model(200, "删除成功");
		}
		return new Model(500, "删除失败");
	}

}
