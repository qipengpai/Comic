package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonTypeVo;
import com.qin.crxl.comic.entity.vo.CartoonVo;
/**
 * 漫画类型接口
 * @author cui
 *
 */

@Service
@Transactional
public interface AdminCartoonTypeService extends BaseService<CartoonType>{
	//增加漫画类型
	boolean addCartoonType(CartoonTypeVo cartoonTypeData);
	
	//修改漫画类型
	boolean updateCartoonType(CartoonTypeVo cartoonTypeData);
	
	//修改漫画类型排序
	boolean updateCartoonTypeShowNum(CartoonTypeVo cartoonTypeData);
	
	//查询出相邻漫画类型showNum
	int[] updateSort(CartoonTypeVo cartoonTypeData);
	
	//排序的漫画与相邻的漫画交换sort
	boolean cartoonChangeSort(int[] sortArr);
		
	//查询漫画类型(id查询)
	CartoonType selectCartoonType(String id);
	
	
	//查询全部漫画类型
	List<CartoonType> allCartoonType();
	
	
	//根据漫画的Id查询漫画类型
	List<CartoonType> selectAllCartoonType(Cartoon cartoon);
	
	//删除漫画类型
	boolean deleteCartoonType(CartoonTypeVo cartoonTypeData);
	
	//查询漫画数量
	int selectCartoonType();
	
	
}
