package com.qin.crxl.comic.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonPhoto;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.vo.CartoonSetData;
import com.qin.crxl.comic.service.AdminCartoonPhotoService;
import com.qin.crxl.comic.service.AdminCartoonService;
import com.qin.crxl.comic.service.AdminCartoonSetService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

/**
 * 漫画话的控制类
 * 
 * @author cui
 * 
 */
@Controller
public class AdminCartoonSetController {

	@Autowired
	private AdminCartoonSetService adminCartoonSetService;
	@Autowired
	private AdminCartoonService adminCartoonService;
	@Autowired
	private AdminCartoonPhotoService adminCartoonPhotoService;

	// 增加话
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOONSET, method = RequestMethod.POST)
	public Model addCartoonSet(CartoonSetData cartoonSetData) {
		boolean bool = adminCartoonSetService.addCartoonSet(cartoonSetData);
		if (bool) {
			return new Model(200, "增加成功");
		}
		return new Model(500, "增加失败");
	}

	// 增加话时自增（+1）
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOONSETADDTITILE, method = RequestMethod.POST)
	public Model cartoonSetAddTitle(CartoonSetData cartoonSetData) {
		Integer sortMax = adminCartoonSetService.getSortMax(cartoonSetData
				.getCartoonId());
		if (!ParaClick.clickObj(sortMax)) {
			return new Model(200, "第1话");
		}
		return new Model(200, "第" + (sortMax + 1) + "话");
	}

	// 删除话
	@ResponseBody
	@RequestMapping(value = ActionUrl.DATELE_CARTOONSET, method = RequestMethod.POST)
	public Model dateleCartoonSet(CartoonSetData cartoonSetData) {
		if (ParaClick.clickString(cartoonSetData.getSort())) {
			return new Model(500, "sort为空");
		}
		if (ParaClick.clickString(cartoonSetData.getCartoonId())) {
			return new Model(500, "CartoonId为空");

		}
		boolean b = adminCartoonSetService
				.selectCartoonSetByCartoonId(cartoonSetData);
		if (!b) {
			return new Model(500, "不能直接删除此话!!!若想删除请先删除此话下面的话集!!!!");
		}
		boolean bool = adminCartoonSetService.deleteCartoonSet(cartoonSetData);
		if (bool) {
			return new Model(200, "删除成功");
		}
		return new Model(500, "删除失败");
	}

	// 修改话
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONSET, method = RequestMethod.POST)
	public Model updateCartoonSet(CartoonSetData cartoonSetData) {

		if (!ParaClick.clickString(cartoonSetData.getWatchState())) {// 话的观看状态修改（0--不能观看
																		// 1--可观看）
			if ("1".equals(cartoonSetData.getWatchState().trim())) {
				CartoonSet selectCartoonSetById = adminCartoonSetService.selectCartoonSetById(cartoonSetData.getId());
				List<CartoonPhoto> list = adminCartoonPhotoService
						.selectCartoonPhoto(cartoonSetData.getId(),selectCartoonSetById.getCartoonId());
				if (!ParaClick.clickList(list)) {
					return new Model(500, "此话无具体话图片内容，请添加后再操作此功能");
				}
				Cartoon cartoon = adminCartoonService
						.selectByIdCartoon(cartoonSetData.getCartoonId());
				if (cartoon.getState() == 0) {
					boolean bool = adminCartoonSetService
							.updateCartoonSet(cartoonSetData);
					if (bool) {
						return new Model(200, "状态更新成功,但此漫画未上架");
					}
					return new Model(500, "请检查上一集，是否上架");
				} else if (!ParaClick.clickObj(cartoon)) {
					return new Model(500, "无此漫画");
				}
			} else if ("0".equals(cartoonSetData.getWatchState().trim())) {
				if (ParaClick.clickString(cartoonSetData.getCartoonId())) {
					return new Model(500, "漫画id为空");
				}
				Cartoon cartoon = adminCartoonService
						.selectByIdCartoon(cartoonSetData.getCartoonId());
				if (ParaClick.clickObj(cartoon) && cartoon.getState() == 0) {
					//更改漫画的更新标题
					boolean bool = adminCartoonSetService
							.updateCartoonSet(cartoonSetData);
					if (bool) {
						return new Model(200, "修改成功");
					}
					return new Model(500, "请检查下一集，是否下架");
				} else if (ParaClick.clickObj(cartoon)
						&& cartoon.getState() == 1) {
					int lookNum = adminCartoonSetService
							.getLookNum(cartoonSetData.getCartoonId());

					if (lookNum <= 1) {// 漫画的唯一一集下架
						boolean bool = adminCartoonService
								.updateStateByCartoonSetLast(cartoonSetData
										.getCartoonId());// 将漫画直接下架
						if (bool) {
							boolean b = adminCartoonSetService
									.updateCartoonSet(cartoonSetData);
							if (b) {
								return new Model(200, "已直接将此漫画下架");
							}
						}
						return new Model(500, "此漫画线上,不存在漫画,请将此漫画下架");
					}
				}
			}
		}

		boolean bool = adminCartoonSetService.updateCartoonSet(cartoonSetData);
		if (bool) {
			return new Model(200, "修改成功");
		}
		return new Model(500, "修改失败");
	}

	// 查询所有话
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONSET, method = RequestMethod.POST)
	public Model selectCartoonSet(CartoonSetData cartoonSetData) {
		Model model = new Model();
		int num = 0;
		if (ParaClick.clickString(cartoonSetData.getNowPage())) {
			cartoonSetData.setNowPage("1");
		}
		if (ParaClick.clickString(cartoonSetData.getPageNum())) {
			cartoonSetData.setPageNum("10");
		}
		num = adminCartoonSetService.getCount(cartoonSetData);
		List<CartoonSet> cartoonSetList = adminCartoonSetService
				.selectCartoonSet(cartoonSetData);
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonSetData.getNowPage().trim()));
		model.setTotalpage((num + Integer.parseInt(cartoonSetData.getPageNum()) - 1)
				/ Integer.parseInt(cartoonSetData.getPageNum()));
		model.setObj(cartoonSetList);
		model.setTotalNum(num);
		return model;
	}

	// 根据id查询话
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONSETBYID, method = RequestMethod.POST)
	public Model selectCartoonSetById(CartoonSetData cartoonSetData) {
		CartoonSet cartoonSet = adminCartoonSetService
				.selectCartoonSetById(cartoonSetData.getId());
		if (ParaClick.clickObj(cartoonSet)) {
			return new Model(200, cartoonSet);
		}
		return new Model(500, "查询失败");
	}

	// 全部上线话
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOONSETALLSTATE, method = RequestMethod.POST)
	public Model cartoonSetAllState(CartoonSetData cartoonSetData) {
		if(ParaClick.clickString(cartoonSetData.getCartoonId())){
			return new Model(500, "漫画id为空");
		}
		int setPhotoNum = adminCartoonPhotoService.selectSetPhotoNum(cartoonSetData.getCartoonId());
		int setCount = adminCartoonSetService.getCount(cartoonSetData);
		if(setPhotoNum!=setCount){
			return new Model(500, "话全部上线失败,有话的图片内容为空");
		}
		cartoonSetData.setSort(String.valueOf(setCount));
		boolean bool = adminCartoonSetService.updateAllCartoonSetState(cartoonSetData);
		if (bool) {
			return new Model(200, "话全部上线成功");
		}
		return new Model(500, "话全部上线失败");
	}

}
