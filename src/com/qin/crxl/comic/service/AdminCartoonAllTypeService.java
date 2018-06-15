package com.qin.crxl.comic.service;

import java.util.List;





import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonAllType;
/**
 * 漫画所有类型的接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminCartoonAllTypeService extends BaseService<CartoonAllType>{
	//增加漫画类型
	boolean addCartoonAllType(String cartoonId,String[] cartoonTypeStr);
		
		
	//删除漫画类型
	boolean deleteCartoonAllType(String cartoonId,String cartoonTypeId);
	
	
	//查询漫画的类型
	List<CartoonAllType> selectCartoonAllType(String cartoonId);
	
}
