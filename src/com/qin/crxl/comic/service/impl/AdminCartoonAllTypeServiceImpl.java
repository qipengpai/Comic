package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.service.AdminCartoonAllTypeService;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;
/**
 * 每个漫画的全部类型的接口实现类
 * @author cui
 *
 */
@Service
public class AdminCartoonAllTypeServiceImpl extends BaseServiceImpl<CartoonAllType> implements
		AdminCartoonAllTypeService {
	
	/**
	 * pp
	 * 
	 * @Remarks 修改指定漫画类型
	 * @throws Exception
	 * */
	@Override
//	@CacheEvict(value="CartoonType",key="#cartoonId",allEntries=false)
	public boolean addCartoonAllType(String cartoonId, String[] cartoonTypeStr) {
		try {
			if(!ParaClick.clickString(cartoonId)&&(cartoonTypeStr!=null&&cartoonTypeStr.length>0)){
				//先删除
				SQL("delete from CartoonAllType where cartoonId='"+cartoonId+"'");
				for(int i=0;i<cartoonTypeStr.length;i++){
					if(!ParaClick.clickString(cartoonTypeStr[i])){
						//后增加
						CartoonAllType cartoonType=new CartoonAllType();
						cartoonType.setCartoonId(cartoonId);
						cartoonType.setCartoonTypeId(cartoonTypeStr[i]);
						super.save(cartoonType);
					}
				}
				JedisUtil.del("CartoonType2-"+cartoonId);
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean deleteCartoonAllType(String cartoonId, String cartoonTypeId) {
		try {
			List<CartoonAllType> sql = SQL("select * from CartoonAllType where CartoonId='"+cartoonId+"' and CartoonTypeId='"+cartoonTypeId+"'",CartoonAllType.class);
			if(ParaClick.clickList(sql)){
				super.delete(sql.get(0).getId());
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<CartoonAllType> selectCartoonAllType(String cartoonId) {
		List<CartoonAllType> cartoonAllTypeList = SQL("select * from CartoonAllType where CartoonId='"+cartoonId+"'",CartoonAllType.class);
		if(ParaClick.clickList(cartoonAllTypeList)){
			return cartoonAllTypeList;
		}
		return null;
	}
	

}
