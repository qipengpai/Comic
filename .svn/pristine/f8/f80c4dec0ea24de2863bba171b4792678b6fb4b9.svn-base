package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonPhoto;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.service.AdminCartoonPhotoService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.RandomUtil;
import com.qin.game.entity.redis.JedisUtil;
/**
 * 每话图片的实现类
 * @author cui
 *
 */
@Service
public class AdminCartoonPhotoServiceImpl extends BaseServiceImpl<CartoonPhoto> implements AdminCartoonPhotoService {

	@Override
	public boolean addCartoonPhoto(CartoonPhotoData cartoonPhotoData,
			String[] photoUrl,String[] height,String[] width) {
		try {
			if(!ParaClick.clickString(cartoonPhotoData.getCartoonId())&&!ParaClick.clickString(cartoonPhotoData.getCartoonSetId())){
				if(photoUrl!=null&&photoUrl.length>0){
					if(photoUrl.length==1&&ParaClick.clickString(photoUrl[0])){
						return false;
					}
					int sortMax = this.getSortMax(cartoonPhotoData);
					for(int i=0;i<photoUrl.length;i++){
						//筛出字符数组中空数组
						if(ParaClick.clickString(photoUrl[i])){
							continue;
						}
						CartoonPhoto cartoonPhoto=new CartoonPhoto();
						//漫画Id
						cartoonPhoto.setCartoonId(cartoonPhotoData.getCartoonId());
						//每话Id
						cartoonPhoto.setCartoonSetId(cartoonPhotoData.getCartoonSetId());
						//图片路径
						cartoonPhoto.setPhotoUrl(photoUrl[i]);
						//图片高
						cartoonPhoto.setPhotoHeight(height[i]);
						//图片宽
						cartoonPhoto.setPhotoWidth(width[i]);
						//序号sort（自增+1）
						sortMax++;
						cartoonPhoto.setSort(sortMax);
						//操作时间
						cartoonPhoto.setImplDate(DateUtil.getdate());
						super.save(cartoonPhoto);
					}
					//////////////redis
					JedisUtil.batchDel("MHPhoto"+cartoonPhotoData.getCartoonId()+cartoonPhotoData.getCartoonSetId());
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public int getSortMax(CartoonPhotoData cartoonPhotoData) {
		StringBuffer sb=new StringBuffer();
		sb.append("select max(sort) from CartoonPhoto where cartoonSetId='"+cartoonPhotoData.getCartoonSetId()+"'");
		try {
			List<Integer> lists=getSessionFactory().createSQLQuery(sb.toString()).list();
			if(lists!=null&&lists.size()>0){
				Integer obj=lists.get(0);
				if(obj==null){
					obj=0;
				}
				return obj;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//删除tupian
	@Override
	public boolean deleteCartoonPhoto(CartoonPhotoData cartoonPhotoData) {
		try {
			CartoonPhoto cartoonPhoto = super.get(cartoonPhotoData.getId());
			if(ParaClick.clickObj(cartoonPhoto)){
				super.delete(cartoonPhotoData.getId());
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean updateCartoonPhoto(CartoonPhotoData cartoonPhotoData,String[] photoUrl,String[] height,String[] width) {
		try {
			SQL("delete from CartoonPhoto where cartoonSetId='"+cartoonPhotoData.getCartoonSetId()+"'");
			if(photoUrl.length==1&&"".equals(photoUrl[0])){
				return true;
			}
			boolean bool = this.addCartoonPhoto(cartoonPhotoData,photoUrl,height,width);
			if(!bool){
				return false;
			}
			JedisUtil.batchDel("MHPhoto"+cartoonPhotoData.getCartoonId()+cartoonPhotoData.getCartoonSetId());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<CartoonPhoto> selectCartoonPhoto(String cartoonSetId,String cartoonId) {
		List<CartoonPhoto> list=SQL("select * from CartoonPhoto where cartoonSetId='"+cartoonSetId+"' and cartoonId='"+cartoonId+"' Order by sort",CartoonPhoto.class);
		if(ParaClick.clickList(list)){
			return list;
		}
		return null;
	}


	@Override
	public int getCount(CartoonPhotoData cartoonPhotoData) {
		int gettotalpage=0;
		try {
			gettotalpage = super.gettotalpage("select count(*) from CartoonPhoto where cartoonSetId='"+cartoonPhotoData.getCartoonSetId()+"'");
			return gettotalpage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return gettotalpage;
	}


	@Override
	public List<CartoonPhoto> selectCartoonPhotoByCartoonId(String cartoonId) {
		List<CartoonPhoto> sql = SQL("select * from CartoonPhoto where cartoonId='"+cartoonId+"'",CartoonPhoto.class);
		return sql;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> selectCartoonPhoto(
			CartoonPhotoData cartoonPhotoData) {
		List<String> list=null;
		try {
			list = getSessionFactory().createSQLQuery("select id from CartoonSet where cartoonId='"+cartoonPhotoData.getCartoonId()+"' and sort="+cartoonPhotoData.getSort()).list();
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//查询话是否存在图片内容（用于话的全部上线）
	@Override
	public int selectSetPhotoNum(String cartoonId) {
		return super.gettotalpage("SELECT count(*) from (select s.id from CartoonPhoto p,CartoonSet s where p.cartoonSetId=s.id and p.cartoonId='"+cartoonId+"' GROUP BY p.CartoonSetId) a");
	}

	private RandomUtil ran = new RandomUtil();
	@Override
	public boolean addCartoonPhotoPP() {
		// 测试
		boolean flag=true;
		try {
			CartoonPhoto cartoonPhoto=new CartoonPhoto();
			cartoonPhoto.setSort(1);
			cartoonPhoto.setCartoonId(ran.generateString(32));
			cartoonPhoto.setCartoonSetId(ran.generateString(32));
			cartoonPhoto.setPhotoUrl(ran.generateString(32));
			cartoonPhoto.setPhotoHeight("1");
			cartoonPhoto.setPhotoWidth("2");
			cartoonPhoto.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			save(cartoonPhoto);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}


	

	

}
