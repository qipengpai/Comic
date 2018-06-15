package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonVo;
/**
 * 漫画接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminCartoonService extends BaseService<Cartoon>{
	//增加漫画
	String addAdminCartoon(CartoonVo cartoonData);
	
	//对漫画排排序
	int getSortMax();
	
	//删除漫画
	boolean deleteAdminCartoon(String id);
	
	//修改漫画
	boolean updateAdminCartoon(CartoonVo cartoonData);
	
	//查询漫画（id）
	Cartoon selectByIdCartoon(String id);
	
	
	//查询所有漫画(分页)
	List<Cartoon> selectAllCartoon(CartoonVo cartoonData);
	
	
	//查询漫画数量
	int getCount(CartoonVo cartoonData);
	
	//查询出相邻漫画的sort
	int[] updateSort(CartoonVo cartoonVo);
	
	//漫画置顶
	boolean cartoonTop(CartoonVo cartoonVo);
	
	//排序的漫画与相邻的漫画交换sort
	boolean cartoonChangeSort(int[] sortArr);
	
	
	//查询漫画（用于Banner图增加）
	List<String[]> selectCartoonAddBanner();
	 
	//将此漫画下架（此功能用于漫画最后一个话下架（CartoonSet）时  ）
	boolean updateStateByCartoonSetLast(String cartoonId);

	boolean updateCartoonTitle(String string, String cartoonId);

	List<Cartoon> selectAllCartoonByDistributor(CartoonData cartoonData);

	int getCountByDistributor(CartoonData cartoonData);
	
		
}
