package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.FirstType;
import com.qin.crxl.comic.service.AdminFirstTypeService;
import com.qin.crxl.comic.tool.ParaClick;
@Service
public class AdminFirstTypeServiceImpl extends BaseServiceImpl<FirstType> implements AdminFirstTypeService{

	//查询所有一级漫画类型
	@Override
	public List<FirstType> selectAllFirsttype() {
		List<FirstType> firstTypeList = super.getAll();
		if(ParaClick.clickList(firstTypeList)){
			return firstTypeList;
		}
		return null;
	}

}
