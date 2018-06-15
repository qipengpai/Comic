package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonModel;
import com.qin.crxl.comic.entity.vo.CartoonModelData;

@Service
@Transactional
public interface CartoonModelService extends BaseService<CartoonModel>{

	List<CartoonModel> getAllCartoonModel();

	boolean addCartoonModel(String model, int i);

	boolean updateCartoonModelById(CartoonModelData cartoonModelData);

	boolean deleteCartoonModelById(String id);
	
	List<CartoonModel> getAllCartoonModelDesc();
}
