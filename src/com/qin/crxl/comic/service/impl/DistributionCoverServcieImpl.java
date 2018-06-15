package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.DistributionCover;
import com.qin.crxl.comic.entity.vo.DistributionCoverData;
import com.qin.crxl.comic.service.DistributionCoverServcie;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class DistributionCoverServcieImpl extends
		BaseServiceImpl<DistributionCover> implements DistributionCoverServcie {

	@Override
	public boolean addDistributorModel(String cartoonCover) {
		// 增加漫画推广模型
		boolean flag = false;
		try {
			DistributionCover distributionCover = new DistributionCover();
			distributionCover.setCartoonId("");
			distributionCover.setImplDate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			distributionCover.setCartoonCover(cartoonCover);
			distributionCover.setSort(0);
			distributionCover.setState(1);
			save(distributionCover);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateDistributorModel(
			DistributionCoverData distributionCoverData) {
		// 修改漫画推广模型
		boolean flag = false;
		try {
			DistributionCover distributionCover = get(distributionCoverData
					.getId());
			if (distributionCover == null) {
				return flag;
			}
			if (!ParaClick.clickString(distributionCoverData.getState())) {
				distributionCover.setState(Integer
						.parseInt(distributionCoverData.getState()));
			}
			if (!ParaClick.clickString(distributionCoverData.getCartoonCover())) {
				distributionCover.setCartoonCover(distributionCoverData
						.getCartoonCover());
			}
			distributionCover.setImplDate(DateUtil
					.getdate_yyyy_MM_dd_HH_MM_SS());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean deleteDistributorNews(String id) {
		// 删除漫画推广模型
		boolean flag = false;
		try {
			DistributionCover distributionCover = get(id);
			if (distributionCover == null) {
				return flag;
			}
			delete(distributionCover);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public int selectDistributorModelCount() {
		// 查询漫画推广模型数量
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM DistributionCover WHERE state=1 ");
		return gettotalpage(sb.toString());
	}

	@Override
	public List<DistributionCover> selectDistributorModel(String nowPage,
			String pageNum) {
		// 查询漫画推广模型
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM DistributionCover WHERE state=1 ORDER BY implDate DESC LIMIT "+(Integer.parseInt(nowPage)-1)*Integer.parseInt(pageNum)+","+Integer.parseInt(pageNum)+" ");
		return SQL(sb.toString(), DistributionCover.class);
	}

//	@Override
//	public DistributionCover getByLogin(String cartoonId, String implDate) {
//		// TODO Auto-generated method stub
//		StringBuffer sb=new StringBuffer();
//		sb.append("SELECT * FROM DistributionCover WHERE cartoonId="+cartoonId+" AND implDate="+implDate+"");
//		List<DistributionCover> list=SQL(sb.toString(), DistributionCover.class);
//		if (list.size()>0) {
//			return list.get(0);
//		}
//		return null;
//	}

}
