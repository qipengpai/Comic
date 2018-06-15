package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Banner;
import com.qin.crxl.comic.entity.vo.BannerData;
import com.qin.crxl.comic.service.AdminBannerService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;
@Service

public class AdminBannerServiceImpl extends BaseServiceImpl<Banner> implements
		AdminBannerService {

	@Override
	public List<Banner> getAllBanner(BannerData bannerData) {
		List<Banner> list = SQL("SELECT * FROM Banner",Banner.class);
		return list;
	}

	@Override
	public boolean addBanner(BannerData bannerData) {
		Banner banner=null;
		try {
			banner=new Banner();
			banner.setComicUrl(bannerData.getComicUrl());
			banner.setHttpImg(bannerData.getHttpImg());
			banner.setTitle(bannerData.getTitle());
			banner.setImplDate(DateUtil.getdate());
			banner.setCartoonId(bannerData.getCartoonId());
			super.save(banner);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
//	@CacheEvict(value="Banner",key="0", allEntries=true)
	public boolean deleteBanner(BannerData bannerData) {
		Banner banner=null;
		try {
			banner=super.get(bannerData.getId());
			if(!ParaClick.clickObj(banner)){
				return false;
			}
			super.delete("Banner",bannerData.getId());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
//	@CacheEvict(value="Banner",key="0", allEntries=true)
	public boolean updateBanner(BannerData bannerData) {
		Banner banner=null;
		try {
			banner=super.get(bannerData.getId());
			if(ParaClick.clickObj(banner)){
				if(!ParaClick.clickString(bannerData.getComicUrl())){
					banner.setComicUrl(bannerData.getComicUrl());
				}
				if(!ParaClick.clickString(bannerData.getHttpImg())){
					banner.setHttpImg(bannerData.getHttpImg());
				}
				if(!ParaClick.clickString(bannerData.getTitle())){
					banner.setTitle(bannerData.getTitle());
				}
				if(!ParaClick.clickString(bannerData.getState())){
					banner.setState(Integer.parseInt(bannerData.getState()));
				}
				if(!ParaClick.clickString(bannerData.getIosState())){
					banner.setIosState(Integer.parseInt(bannerData.getIosState()));
				}
				if(!ParaClick.clickString(bannerData.getAnroidState())){
					banner.setAnroidState(Integer.parseInt(bannerData.getAnroidState()));
				}
				if(JedisUtil.exists("Banner")){
					JedisUtil.batchDel("Banner");
				}
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public Banner selectBannerById(BannerData bannerData) {
		return super.get(bannerData.getId());
	}
}
