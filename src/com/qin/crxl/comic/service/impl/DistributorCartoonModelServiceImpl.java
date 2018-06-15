package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.DistributorCartoonModel;
import com.qin.crxl.comic.entity.vo.DistributorCartoonModelData;
import com.qin.crxl.comic.service.DistributorCartoonModelService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class DistributorCartoonModelServiceImpl extends BaseServiceImpl<DistributorCartoonModel> implements DistributorCartoonModelService{

	@Override
	public boolean addDistributorModel(String cartoonId, String modelUrl) {
		// 增加漫画推广文案封面
		boolean flag=false;
		try {
			DistributorCartoonModel distributorCartoonModel=new DistributorCartoonModel();
			distributorCartoonModel.setCartoonId(cartoonId);
			distributorCartoonModel.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			distributorCartoonModel.setModelType(1);
			distributorCartoonModel.setModelUrl(modelUrl);
			distributorCartoonModel.setSort(0);
			distributorCartoonModel.setState(1);
			save(distributorCartoonModel);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateDistributorModel(
			DistributorCartoonModelData distributorCartoonModelData) {
		// 修改漫画推广文案封面
		boolean flag=false;
		try {
			DistributorCartoonModel distributorCartoonModel=get(distributorCartoonModelData.getId());
			if (distributorCartoonModel==null) {
				return flag;
			}
			if (!ParaClick.clickString(distributorCartoonModelData.getState())) {
				distributorCartoonModel.setState(Integer.parseInt(distributorCartoonModelData.getState()));
			}
			if (!ParaClick.clickString(distributorCartoonModelData.getModelUrl())) {
				distributorCartoonModel.setModelUrl(distributorCartoonModelData.getModelUrl());
			}
			distributorCartoonModel.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean deleteDistributorNews(String id) {
		// 删除漫画推广文案封面
		boolean flag=false;
		try {
			DistributorCartoonModel distributorCartoonModel=get(id);
			if (distributorCartoonModel==null) {
				return flag;
			}
			delete(distributorCartoonModel);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public int selectDistributorModelCount(String cartoonId) {
		// 查询漫画推广文案封面数量
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM DistributorCartoonModel WHERE cartoonId='"+cartoonId+"' AND state=1");
		return gettotalpage(sb.toString());
	}

	@Override
	public List<DistributorCartoonModel> selectDistributorModel(String nowPage, String pageNum, String cartoonId) {
		// 查询漫画推广文案封面
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM DistributorCartoonModel WHERE cartoonId='"+cartoonId+"' AND state=1 ORDER BY implDate DESC LIMIT "+(Integer.parseInt(nowPage)-1)*Integer.parseInt(pageNum)+","+Integer.parseInt(pageNum)+" ");
		return SQL(sb.toString(), DistributorCartoonModel.class);
	}

}
