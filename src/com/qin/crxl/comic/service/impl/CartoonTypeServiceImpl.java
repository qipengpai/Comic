package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonTypeData;
import com.qin.crxl.comic.service.CartoonTypeService;

@Service
public class CartoonTypeServiceImpl extends BaseServiceImpl<CartoonType> implements CartoonTypeService{

	@Override
	public int gettotalNum(CartoonTypeData cartoonTypeData) {
		// 查詢所有類型總數
		int num=super.gettotalpage("SELECT COUNT(*) FROM CartoonType");
		return num;
	}

	@Override
//	@Cacheable(value = "CartoonType",key="0")
	public List<CartoonType> getAllCartoonType() {
		// 查詢所有類型
		List<CartoonType> list=super.SQL("CartoonType1",3600,"SELECT * FROM CartoonType ORDER BY showNum ASC ", CartoonType.class);
		//LIMIT "+(Integer.parseInt(cartoonTypeData.getNowPage())-1)*10+",10
		return list;
	}

}
