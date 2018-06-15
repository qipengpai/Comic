package com.qin.crxl.comic.service;

import java.util.List;





import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonPhoto;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
/**
 * 每话图片内容
 * @author cui
 *
 */
@Service
@Transactional
public interface AdminCartoonPhotoService extends BaseService<CartoonPhoto>{
	//增加话的图片
	boolean addCartoonPhoto(CartoonPhotoData cartoonPhotoData,String[] photoUrl,String[] height,String[] width);
	
	//查询话中最大的sort
	int getSortMax(CartoonPhotoData cartoonPhotoData);
	
	//删除话的图片
	boolean deleteCartoonPhoto(CartoonPhotoData cartoonPhotoData);
	
	//修改话
	boolean updateCartoonPhoto(CartoonPhotoData cartoonPhotoData,String[] photoUrl,String[] height,String[] width);
	
	//查询话的图片
	List<CartoonPhoto> selectCartoonPhoto(String cartoonSetId,String cartoonId);//每话的id
	
	//查询话的所有图片
	int getCount(CartoonPhotoData cartoonPhotoData);
	
	//查询漫画是否存在图片内容
	List<CartoonPhoto> selectCartoonPhotoByCartoonId(String cartoonId);//漫画ID
	
	//查询话的集数图片
	List<String> selectCartoonPhoto(CartoonPhotoData cartoonPhotoData);
	
	//查询话是否存在图片（用于话全部上线功能）
	int selectSetPhotoNum(String cartoonId);

	boolean addCartoonPhotoPP();
	
}
