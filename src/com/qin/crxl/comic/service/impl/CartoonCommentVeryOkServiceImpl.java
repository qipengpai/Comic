package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonCommentVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonCommentData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.CartoonCommentService;
import com.qin.crxl.comic.service.CartoonCommentVeryOkService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class CartoonCommentVeryOkServiceImpl extends
		BaseServiceImpl<CartoonCommentVeryOk> implements
		CartoonCommentVeryOkService {
	@Autowired
	private CartoonCommentService cartoonCommenService;

	@Override
//	@Cacheable(value = "CartoonCommentOK", key = "#id.concat(#userId)")
	public List<CartoonCommentVeryOk> getUserCartoonCommentVeryOk(String id,
			String userId) {
		// 产看用户是否给该评论点赞
//		List<CartoonCommentVeryOk> list = SQL("CCVO-"+userId+"-"+id,3600,
//				"SELECT * FROM CartoonCommentVeryOk WHERE cartoonCommentId='"
//						+ id + "' AND userId='" + userId + "' AND okState=1 ",
//				CartoonCommentVeryOk.class);
		List<CartoonCommentVeryOk> list = SQL(
				"SELECT * FROM CartoonCommentVeryOk WHERE cartoonCommentId='"
						+ id + "' AND userId='" + userId + "' AND okState=1 ",
				CartoonCommentVeryOk.class);
		return list;
	}

	@Override
	public int getUserVseryOkCount(String id) {
		// 查询此腒点赞次数
		int num = super
				.gettotalpage("SELECT COUNT(*) FROM CartoonCommentVeryOk  WHERE cartoonCommentId='"
						+ id + "' AND okState=1 ");
		return num;
	}

	@Override
//	@Caching(evict = {
//			@CacheEvict(value = "CartoonComment", allEntries = true),
//			@CacheEvict(value = "CartoonCommentOK", key = "#id.concat(#userId)") })
	public boolean addVeryOK(String id, String userId,String cartoonId) {
		// 点赞
		boolean flag = false;
		try {
			CartoonCommentVeryOk veryOk = new CartoonCommentVeryOk();
			veryOk.setCartoonCommentId(id);
			veryOk.setOkDate(DateUtil.getdate_yyyy_MM_dd());
			veryOk.setUserId(userId);
			veryOk.setOkState(1);
			super.save(veryOk);
			boolean flag2 = cartoonCommenService.addOkCount(id);
			if (!flag2) {
				throw new BusinessException("点赞异常");
			}
//			JedisUtil.del("CCVO-"+userId+"-"+id);
//			JedisUtil.batchDel("CC-"+cartoonId);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
//	@Caching(evict = {
//			@CacheEvict(value = "CartoonComment", allEntries = true),
//			@CacheEvict(value = "CartoonCommentOK", key = "#id.concat(#userId)") })
	public boolean deleteVeryOK(String id, String userId,String cartoonId) {
		// 取消点赞
		boolean flag = false;
		try {
			List<CartoonCommentVeryOk> list3 = getUserCartoonCommentVeryOk(id,
					userId);
			if (!ParaClick.clickList(list3)) {
				return flag;
			}
			for (int i = 0; i < list3.size(); i++) {
				super.delete(list3.get(i).getId());
			}
			// 减少点赞次数
			boolean flag2 = cartoonCommenService.subductionOkCount(id);
			if (!flag2) {
				throw new BusinessException("减少点赞数点赞异常");
			}
//			JedisUtil.del("CCVO"+userId+id);
//			JedisUtil.batchDel("CC"+cartoonId);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

}