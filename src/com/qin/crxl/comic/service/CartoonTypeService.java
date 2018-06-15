package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonTypeData;

@Service
@Transactional
public interface CartoonTypeService extends BaseService<CartoonType>{

	int gettotalNum(CartoonTypeData cartoonTypeData);

	List<CartoonType> getAllCartoonType();

	//CartoonType getById(String string);

}
