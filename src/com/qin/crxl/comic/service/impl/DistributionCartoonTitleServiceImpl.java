package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.DistributionCartoonTitle;
import com.qin.crxl.comic.entity.vo.DistributorCartoonTitleData;
import com.qin.crxl.comic.service.DistributionCartoonTitleService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class DistributionCartoonTitleServiceImpl extends BaseServiceImpl<DistributionCartoonTitle> implements DistributionCartoonTitleService{

	@Override
	public boolean addDistributorTitle(String cartoonId, String cartoonTitle) {
		// 增加漫画推广标题
		boolean flag=false;
		try {
			DistributionCartoonTitle distributionCartoonTitle=new DistributionCartoonTitle();
			distributionCartoonTitle.setCartoonId(cartoonId);
			distributionCartoonTitle.setCartoonTitle(cartoonTitle);
			distributionCartoonTitle.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			distributionCartoonTitle.setSort(0);
			distributionCartoonTitle.setState(1);
			save(distributionCartoonTitle);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateDistributorTitle(
			DistributorCartoonTitleData distributorCartoonTitleData) {
		// 修改漫画推广标题
		boolean flag=false;
		try {
			DistributionCartoonTitle distributorCartoonTitle=super.get(distributorCartoonTitleData.getId());
			if (!ParaClick.clickString(distributorCartoonTitleData.getCartoonTitle())) {
				distributorCartoonTitle.setCartoonTitle(distributorCartoonTitleData.getCartoonTitle());
			}
			if (!ParaClick.clickString(distributorCartoonTitleData.getState())) {
				distributorCartoonTitle.setState(Integer.parseInt(distributorCartoonTitleData.getState()));
			}
			distributorCartoonTitle.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean deleteDistributorNews(String id) {
		// 删除漫画推广标题
		boolean flag=false;
		try {
			DistributionCartoonTitle distributorCartoonTitle=super.get(id);
			if (distributorCartoonTitle==null) {
				return flag;
			}
			delete(distributorCartoonTitle);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public int selectDistributorTitleCount(String condition, String nowPage,
			String pageNum,String cartoonId) {
		// 查询漫画推广标题数量
		StringBuffer sb =new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM DistributionCartoonTitle WHERE cartoonId='"+cartoonId+"'");
		return super.gettotalpage(sb.toString());
	}

	@Override
	public List<DistributionCartoonTitle> selectDistributorTitle(
			String condition, String nowPage, String pageNum,String cartoonId) {
		// 查询全部漫画推广标题
		StringBuffer sb =new StringBuffer();
		sb.append(" SELECT * FROM DistributionCartoonTitle WHERE cartoonId='"+cartoonId+"' ");
		
		if (!ParaClick.clickString(condition)) {
			sb.append(" AND cartoonTitle LIKE '%"+condition+"%' ");
		}
		sb.append(" ORDER BY implDate DESC LIMIT "+(Integer.parseInt(nowPage)-1)*Integer.parseInt(pageNum)+","+Integer.parseInt(pageNum)+" ");
		return super.SQL(sb.toString(),DistributionCartoonTitle.class);
	}
	
	
}
