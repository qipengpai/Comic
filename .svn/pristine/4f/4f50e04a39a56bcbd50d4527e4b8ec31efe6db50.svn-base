package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllModel;
import com.qin.crxl.comic.entity.CartoonModel;
import com.qin.crxl.comic.entity.vo.CartoonAllModelData;
import com.qin.crxl.comic.entity.vo.CartoonModelData;
import com.qin.crxl.comic.service.CartoonAllModelService;
import com.qin.crxl.comic.service.CartoonModelService;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class CartoonAllModelController {

	@Autowired
	private CartoonAllModelService cartoonAllModelService;
	@Autowired
	private CartoonModelService cartoonModelService;

	/**
	 * pp
	 * 
	 * @Remarks 查看该漫画 所有模块
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_THIS_CARTOON_MODEL, method = RequestMethod.POST)
	public Model selectGetAllCartoonModel(String cartoonId) throws Exception {
		Model model = new Model();
		List<CartoonAllModel> list2 = cartoonAllModelService
				.getThisCartoonModel(cartoonId);
		if (!ParaClick.clickList(list2)) {
			return new Model(200, "无模块");
		}
		for (CartoonAllModel cartoonAllModel : list2) {
			CartoonModel cartoonModel= cartoonModelService.get(cartoonAllModel.getCartoonModelId());
			cartoonAllModel.setObj(cartoonModel.getModel());
		}
		model.setError(200);
		model.setMsg("操作成功");
		model.setObj(list2);
		return model;
	}

	/**
	 * pp
	 * 
	 * @Remarks 查看该漫画 可增加模块
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_ADD_THIS_CARTOON_MODEL, method = RequestMethod.POST)
	public Model getAllCartoonModel(String cartoonId) throws Exception {
		Model model = new Model();
		List<CartoonModel> object = new ArrayList<CartoonModel>();
		List<CartoonModel> list = cartoonModelService.getAllCartoonModel();
		if (!ParaClick.clickList(list)) {
			return new Model(200, "无數據");
		}
		List<CartoonAllModel> list2 = cartoonAllModelService
				.getThisCartoonModel(cartoonId);
		if (ParaClick.clickList(list2)) {
			for (CartoonModel cartoonModel : list) {
				boolean flag = false;
				for (CartoonAllModel cartoonAllModel : list2) {
					if (cartoonModel.getId().equals(cartoonAllModel.getCartoonModelId())) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					object.add(cartoonModel);
				}
			}
			model.setError(200);
			model.setMsg("操作成功");
			model.setObj(object);
			return model;
		} else {
			model.setError(200);
			model.setMsg("操作成功");
			model.setObj(list);
			return model;
		}

	}

	/**
	 * pp
	 * 
	 * @Remarks 增加漫画的漫画模块
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_THIS_CARTOON_MODEL, method = RequestMethod.POST)
	public Model addCartoonModel(CartoonAllModelData cartoonAllModelData)
			throws Exception {
		if (ParaClick.clickString(cartoonAllModelData.getCartoonId())) {
			return new Model(500, "cartoonid为空");
		}
		if (ParaClick.clickString(cartoonAllModelData.getCartoonModelId())) {
			return new Model(500, "cartoonModelId为空");
		}
		String[] pp = cartoonAllModelData.getCartoonModelId().split("\\,");
		for (String string : pp) {
			boolean flag = cartoonAllModelService.addThisCartoonModel(
					cartoonAllModelData.getCartoonId(),
					string);
			if (!flag) {
				return new Model(500, "添加失败");
			}
		}
		return new Model(200, "添加成功");
	}

	/**
	 * pp
	 * 
	 * @Remarks 删除漫画的漫画模块
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DELETE_THIS_CARTOON_MODEL, method = RequestMethod.POST)
	public Model deleteCartoonModel(CartoonAllModelData cartoonAllModelData)
			throws Exception {
		if (ParaClick.clickString(cartoonAllModelData.getId())) {
			return new Model(500, "id为空");
		}
		boolean flag = cartoonAllModelService
				.deleteThisCartoonModel(cartoonAllModelData.getId());
		if (!flag) {
			return new Model(500, "删除失败");
		}
		return new Model(200, "删除成功");
	}

	
	/**
	 * pp
	 * 
	 * @Remarks 升降序置顶
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONSORT_BYMODEL, method = RequestMethod.POST)
	public Model updateCartoonByModel(CartoonAllModelData cartoonAllModelData)
			throws Exception {
		boolean flag = cartoonAllModelService
				.updateSortByModel(cartoonAllModelData);
		if (!flag) {
			return new Model(500, "修改排序失败");
		}
		return new Model(200, "修改排序成功");
	}
}
