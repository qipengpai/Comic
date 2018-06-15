package com.qin.crxl.comic.service.impl;
/**
 * 漫画类型接口实现类
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonTypeVo;
import com.qin.crxl.comic.service.AdminCartoonAllTypeService;
import com.qin.crxl.comic.service.AdminCartoonTypeService;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;
@Service
public class AdminCartoonTypeServiceImpl extends BaseServiceImpl<CartoonType> implements AdminCartoonTypeService {
	@Autowired
	private AdminCartoonAllTypeService adminCartoonAllTypeService;
	@Override
//	@CacheEvict(value="CartoonType",key="0",allEntries=false)
	public boolean addCartoonType(CartoonTypeVo cartoonTypeData) {
		try{
			CartoonType cartoonType =new CartoonType();
			cartoonType.setCartoonType(cartoonTypeData.getCartoonType());
			cartoonType.setShowNum(this.selectCartoonType()+1);
			super.save(cartoonType);
			/////////redis
			JedisUtil.del("CartoonType1");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateCartoonType(CartoonTypeVo cartoonTypeData) {
		CartoonType cartoonType=null;
		try {
			cartoonType=super.get(cartoonTypeData.getId());
			if(ParaClick.clickObj(cartoonType)){
				cartoonType.setCartoonType(cartoonTypeData.getCartoonType());
//				cartoonType.setClick(cartoonTypeData.getClick());
//				cartoonType.setSort(cartoonTypeData.getSort());
				JedisUtil.batchDel("CartoonType");/////////redis
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public CartoonType selectCartoonType(String id) {
		List<CartoonType> cartoonType = SQL("select * from CartoonType where id='"+id+"'",CartoonType.class);
		if(cartoonType!=null&&cartoonType.size()>0){
			return cartoonType.get(0);
		}
		return null;
	}

	@Override
	public List<CartoonType> allCartoonType() {
		List<CartoonType> cartoonType = SQL("select * from CartoonType order by showNum ASC",CartoonType.class);
		return cartoonType;
	}

	@Override
	public List<CartoonType> selectAllCartoonType(Cartoon cartoon) {
		
		List<CartoonAllType> list = adminCartoonAllTypeService.selectCartoonAllType(cartoon.getId());
		if(ParaClick.clickList(list)){
			List<CartoonType> cartoonTypeList=new ArrayList<CartoonType>();
			for(int i=0;i<list.size();i++){
				CartoonType type = this.selectCartoonType(list.get(i).getCartoonTypeId());
				cartoonTypeList.add(type);
			}
			return cartoonTypeList;
		}
		return null;
	}

	@Override
//	@CacheEvict(value="CartoonType",allEntries=true)
	public boolean deleteCartoonType(CartoonTypeVo cartoonTypeData) {
		try {
			super.delete(cartoonTypeData.getId());
			SQL("delete from CartoonAllType where CartoonTypeId='"+cartoonTypeData.getId()+"'");
			/////////redis
			JedisUtil.batchDel("CartoonType");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public int selectCartoonType() {
		return super.gettotalpage("select count(id) from CartoonType");
	}

	@Override
	public boolean updateCartoonTypeShowNum(CartoonTypeVo cartoonTypeVo) {
		
		return false;
	}

	@Override
//	@CacheEvict(value="CartoonType",allEntries=true)
	public int[] updateSort(CartoonTypeVo cartoonTypeVo) {
		if("0".equals(cartoonTypeVo.getSortNum())){//向下排序
			StringBuffer sb= new StringBuffer();
			sb.append("select c.* from CartoonType c where 1=1");
			sb.append(" and c.showNum>(SELECT showNum from CartoonType WHERE id='"+cartoonTypeVo.getId()+"') order by c.showNum ASC LIMIT 0,1");
			List<CartoonType>  list=super.SQL(sb.toString(),CartoonType.class);
			if(!ParaClick.clickList(list)){
				int[] s=new int[1];
				return s;
			}
			int[] sortNum=new int[2];
			sortNum[0]=list.get(0).getShowNum();
			sortNum[1]=Integer.parseInt(cartoonTypeVo.getShowNum());
			return sortNum;
			
		}else if("1".equals(cartoonTypeVo.getSortNum())){//向上排序
			StringBuffer sb= new StringBuffer();
			sb.append("select c.* from CartoonType c where 1=1");
			sb.append(" and c.showNum<(SELECT showNum from CartoonType WHERE id='"+cartoonTypeVo.getId()+"') order by c.showNum DESC LIMIT 0,1");
			List<CartoonType>  list=super.SQL(sb.toString(),CartoonType.class);
			if(!ParaClick.clickList(list)){
				int[] s=new int[1];
				return s;
			}
			int[] sortNum=new int[2];
			sortNum[0]=list.get(0).getShowNum();
			sortNum[1]=Integer.parseInt(cartoonTypeVo.getShowNum());
			return sortNum;
		}
		return null;
	}

	@Override
//	@CacheEvict(value="CartoonType",allEntries=true)
	public boolean cartoonChangeSort(int[] sortArr) {
		List<CartoonType> cartoon1=null;
		List<CartoonType> cartoon2=null;
		try {
			cartoon1 = super.SQL("select * from CartoonType where showNum="+sortArr[0], CartoonType.class);
			System.out.println(cartoon1.get(0).getCartoonType());
			cartoon2 = super.SQL("select * from CartoonType where showNum="+sortArr[1], CartoonType.class);
			int num = cartoon1.get(0).getShowNum();
			cartoon1.get(0).setShowNum(cartoon2.get(0).getShowNum());
			cartoon2.get(0).setShowNum(num);
			/////////redis
			JedisUtil.del("CartoonType1");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	

}
