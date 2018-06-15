package com.qin.crxl.comic.action;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.CartoonPhoto;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.service.AdminCartoonPhotoService;
import com.qin.crxl.comic.service.AdminCartoonSetService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

/**
 * 话的图片控制类
 * 
 * @author cui
 * 
 */
@Controller
public class AdminCartoonPhotoController {
	@Autowired
	private AdminCartoonPhotoService adminCartoonPhotoService;
	@Autowired
	private AdminCartoonSetService adminCartoonSetService;

	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOONPHOTO, method = RequestMethod.POST)
	public Model addCartoonPhotoService(CartoonPhotoData cartoonPhotoData) {
		String photoAll=cartoonPhotoData.getPhotoUrl();
		String heightAll=cartoonPhotoData.getPhotoHeight();
		String widthAll=cartoonPhotoData.getPhotoWidth();
		String[] strs = photoAll.split(",");
		String[] height=heightAll.split(",");
		String[] width=widthAll.split(",");
		if(strs.length!=height.length||strs.length!=width.length||width.length!=height.length){
			return new Model(500, "添加失败");
		}
		boolean bool = adminCartoonPhotoService.addCartoonPhoto(
				cartoonPhotoData, strs,height,width);
		if (bool) {
			return new Model(200, "添加成功");
		}
		return new Model(500, "添加失败");
	}
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONPHOTO, method = RequestMethod.POST)
	public Model selectCartoonPhotoAll(CartoonPhotoData cartoonPhotoData) {
		Model model=new Model();
		int num=0;
		if(ParaClick.clickString(cartoonPhotoData.getNowPage())){
			cartoonPhotoData.setNowPage("1");
		}
		if(ParaClick.clickString(cartoonPhotoData.getPageNum())){
			cartoonPhotoData.setPageNum("10");
		}
		CartoonSet selectCartoonSetById = adminCartoonSetService.selectCartoonSetById(cartoonPhotoData.getCartoonSetId());
		List<CartoonPhoto> cartoonPhotoList = adminCartoonPhotoService.selectCartoonPhoto(cartoonPhotoData.getCartoonSetId(),selectCartoonSetById.getCartoonId());
		if(!ParaClick.clickList(cartoonPhotoList)){
			return new Model(500,"无数据");
		}
		num=adminCartoonPhotoService.getCount(cartoonPhotoData);
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonPhotoData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(cartoonPhotoData.getPageNum()) - 1) / Integer.parseInt(cartoonPhotoData.getPageNum()));
		model.setObj(cartoonPhotoList);
		model.setSpare(selectCartoonSetById.getSort());
		model.setTotalNum(num);
		return model;

	}
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONPHOTOBYID, method = RequestMethod.POST)
	public Model updateCartoonPhoto(CartoonPhotoData cartoonPhotoData){
		String photoAll=cartoonPhotoData.getPhotoUrl();
		String heightAll=cartoonPhotoData.getPhotoHeight();
		String widthAll=cartoonPhotoData.getPhotoWidth();
		String[] strs = photoAll.split(",");
		String[] height=heightAll.split(",");
		String[] width=widthAll.split(",");
		if(strs.length==1&&"".equals(strs[0])){
			CartoonSet cartoonSet = adminCartoonSetService.selectCartoonSetById(cartoonPhotoData.getCartoonSetId());
			if(cartoonSet.getWatchState()==1){
				return new Model(500, "此操作会将话的图片全部删除，请您直接将此话删除或者先将此话下线");
			}
		}
		if(strs.length!=height.length||strs.length!=width.length||width.length!=height.length){
			return new Model(500, "修改失败");
		}
		boolean del = adminCartoonPhotoService.updateCartoonPhoto(cartoonPhotoData,strs,height,width);
		if(del){
			return new Model(200,"修改成功");
//			if(strs.length!=height.length||strs.length!=width.length||width.length!=height.length){
//				return new Model(500, "修改失败");
//			}
//			boolean upda = adminCartoonPhotoService.addCartoonPhoto(
//					cartoonPhotoData, strs,height,width);
//			if (upda) {
//				return new Model(200, "修改成功");
//			}
//			return new Model(500, "修改失败");
		}
		return new Model(500,"修改失败");
	}
	
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONPHOTO_UPANDDOWN, method = RequestMethod.POST)
	public Model selectCartoonPhotoUpAndDown(CartoonPhotoData cartoonPhotoData){
		 List<String> cartoonSet = adminCartoonPhotoService.selectCartoonPhoto(cartoonPhotoData);
		 if(!ParaClick.clickList(cartoonSet)){
			 return new Model(500,"还点呢！！！无漫画图片啦！！！"); 
		 }
		 cartoonPhotoData.setCartoonSetId(cartoonSet.get(0));
		 Model cartoonPhotoAll = this.selectCartoonPhotoAll(cartoonPhotoData);
		return cartoonPhotoAll;
	}
}
