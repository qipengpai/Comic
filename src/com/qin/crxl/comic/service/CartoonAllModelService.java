package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonAllModel;
import com.qin.crxl.comic.entity.vo.CartoonAllModelData;

@Service
@Transactional
public interface CartoonAllModelService extends BaseService<CartoonAllModel>{

	List<CartoonAllModel> getThisCartoonModel(String cartoonId);

	boolean deleteThisCartoonModel(String id);

	boolean addThisCartoonModel(String cartoonId, String cartoonModelId);

	List<CartoonAllModel> getThisCartoonModelBycartoonModelId(String cartoonModelId, String nowpage);

	boolean updateSortByModel(CartoonAllModelData cartoonAllModelData);

	int getThisCartoonModelBycartoonModelIdNum(CartoonAllModelData cartoonAllModelData);

	List<CartoonAllModel> getCartoonBeforeSix(String id);

	List<CartoonAllModel> getCartoonMore(String cartoonModelId,String nowPage);

	int getCartoonMoreCount(String cartoonModelId);

}
