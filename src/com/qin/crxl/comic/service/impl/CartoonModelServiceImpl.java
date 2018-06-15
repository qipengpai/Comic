package com.qin.crxl.comic.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonModel;
import com.qin.crxl.comic.entity.vo.CartoonModelData;
import com.qin.crxl.comic.service.CartoonModelService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class CartoonModelServiceImpl extends BaseServiceImpl<CartoonModel>
		implements CartoonModelService {

	@Override
	public List<CartoonModel> getAllCartoonModel() {
		// 查询漫画所有模块
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM CartoonModel ORDER BY sort");
		return SQL("CartoonModelAdmin" , 3600, sb.toString(), CartoonModel.class);
	}

	public List<CartoonModel> getAllCartoonModel2() {
		// 查询漫画所有模块
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM CartoonModel ORDER BY sort");
		return SQL(sb.toString(), CartoonModel.class);
	}
	@Override
	public List<CartoonModel> getAllCartoonModelDesc() {
		// 查询漫画所有模块
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM CartoonModel ORDER BY sort DESC");
		return SQL(sb.toString(), CartoonModel.class);
	}

	@Override
	public boolean addCartoonModel(String model, int i) {
		// 增加模块
		boolean flag = false;
		try {
			CartoonModel cartoonModel = new CartoonModel();
			cartoonModel.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			cartoonModel.setSort(i + 1);
			cartoonModel.setState(1);
			cartoonModel.setModel(model);
			save(cartoonModel);
			JedisUtil.del("CartoonModelAdmin");
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateCartoonModelById(CartoonModelData cartoonModelData) {
		// 修改模块
		List<CartoonModel> list = getAllCartoonModel2();
		CartoonModel cartoonModel = get(cartoonModelData.getId());
		boolean flag = false;
		try {
			if (!ParaClick.clickString(cartoonModelData.getSign())) {
				// up
				if (Integer.parseInt(cartoonModelData.getSign()) == 1) {
					for (int i = 0; i < list.size(); i++) {
						if (cartoonModel.getId().equals(list.get(i).getId())) {
							if (i == 0) {
								return flag;
							}
							cartoonModel.setSort(list.get(i - 1).getSort());
							list.get(i - 1)
									.setSort(
											Integer.parseInt(cartoonModelData
													.getSort()));
							cartoonModel.setImplDate(DateUtil
									.getdate_yyyy_MM_dd_HH_MM_SS());
							list.get(i).setImplDate(
									DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (cartoonModel.getId().equals(list.get(i).getId())) {
							if (i == list.size() - 1) {
								return flag;
							}
							cartoonModel.setSort(list.get(i + 1).getSort());
							list.get(i + 1)
									.setSort(
											Integer.parseInt(cartoonModelData
													.getSort()));
							cartoonModel.setImplDate(DateUtil
									.getdate_yyyy_MM_dd_HH_MM_SS());
							list.get(i).setImplDate(
									DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
						}
					}
				}
			} else {
				cartoonModel.setModel(cartoonModelData.getModel());
				cartoonModel
						.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			}
			JedisUtil.del("CartoonModelAdmin");
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteCartoonModelById(String id) {
		// 删除模块
		boolean flag = false;
		try {
			CartoonModel cartoonModel = super.get(id);
			if (cartoonModel == null) {
				return flag;
			}
			super.delete(id);
			SQL("DELETE FROM CartoonAllModel WHERE cartoonModelId = '" + id
					+ "'");
			JedisUtil.del("CartoonModelAdmin");
			JedisUtil.del("ComicModelSix-"+id);
			JedisUtil.del("ComicAllModelMoreTh-"+id);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

}