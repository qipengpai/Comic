package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.service.CartoonAllTypeService;

@Service
public class CartoonAllTypeServiceImpl extends
BaseServiceImpl<CartoonAllType> implements CartoonAllTypeService{

	@Override
//	@Cacheable(value="CartoonType",key="#id")
	public List<CartoonAllType> getByCartoonId(String id) {
		// 查询所有类型
		List<CartoonAllType> list=super.SQL("CartoonType2-"+id,3600,"SELECT * FROM CartoonAllType WHERE CartoonId='"+id+"'",CartoonAllType.class);
		return list;
	}

	
}
