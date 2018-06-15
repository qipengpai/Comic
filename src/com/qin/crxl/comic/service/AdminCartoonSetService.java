package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.vo.CartoonSetData;
/**
 * 漫画选集接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminCartoonSetService extends BaseService<CartoonSet>{
	
	//增加话
	boolean addCartoonSet(CartoonSetData cartoonSetData);
	
	//对话排序
	Integer getSortMax(String cartoonId);
	
	
	//查询漫画的话上架状态时最大的sort
	Integer getStateSortMax(String cartoonId);
	
	
	//删除话
	boolean deleteCartoonSet(CartoonSetData cartoonSetData);
	
	
	//修改话
	boolean updateCartoonSet(CartoonSetData cartoonSetData);
	
	//修改话的更新日期
	boolean updateCartoonSetDate(String cartoonSetId);
	
	//查询话
	List<CartoonSet> selectCartoonSet(CartoonSetData cartoonSetData);
	
	//計算話的数量
	int getCount(CartoonSetData cartoonSetData);
	
	//id查询话
	CartoonSet selectCartoonSetById(String id);
	
	//根据漫画的id查询所属已上架话数量
	int getLookNum(String cartoonId);
	
	//查询漫画是否存在上架话内容
	List<CartoonSet> selectCartoonSetByCartoonId(String cartoonId);//漫画ID
	
	//话的全部上线(下线)
	boolean updateAllCartoonSetState(CartoonSetData cartoonSetData);
	
	//删除漫画的话时判断是否存在漫画的下一集话
	boolean selectCartoonSetByCartoonId(CartoonSetData cartoonSetData);
}