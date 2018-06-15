package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Banner;
import com.qin.crxl.comic.service.BannerService;

@Service
public class BannerServiceImpl  extends BaseServiceImpl<Banner> implements BannerService{

	@Override
//	@Cacheable(value="Banner",key="0")
	public List<Banner> getAllBanner() {
		// h5获取轮播
		List<Banner> list= SQL("Banner",1800,"SELECT * from Banner WHERE state=1 ORDER BY implDate DESC", Banner.class);
		return list;
	}

	@Override
	public List<Banner> getAllBannerByIos() {
		// ios获取轮播
		List<Banner> list= SQL("BannerIOS",1800,"SELECT * from Banner WHERE state=1 AND iosState=1 ORDER BY implDate DESC", Banner.class);
		return list;
	}
	
	
}
