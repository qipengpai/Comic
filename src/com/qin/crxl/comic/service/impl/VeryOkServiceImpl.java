package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.VeryOk;
import com.qin.crxl.comic.entity.vo.CartoonSetData;
import com.qin.crxl.comic.service.VeryOkService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class VeryOkServiceImpl extends BaseServiceImpl<VeryOk> implements VeryOkService{

	@Override
//	@CacheEvict(value="CartoonSetOk",key="#id.concat(#userId)")
	public boolean addVeryOK(String id,String userId) throws Exception {
		// 点赞
		boolean flag=false;
		try {
			VeryOk  veryOk=new VeryOk();
			veryOk.setCartoonSetId(id);
			veryOk.setOkDate(DateUtil.getdate_yyyy_MM_dd());
			veryOk.setUserId(userId);
			veryOk.setOkState(1);
			super.save(veryOk);
			JedisUtil.del("CVeryOk-"+id+userId);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE CartoonSet SET okCount=(okCount+1) WHERE id='"+id+"'");
		return flag;
	}

	@Override
	public List<VeryOk> getUserVseryOk(String id,
			UserEntity userEntity) {
		// 查询此话该用户是否点赞
		List<VeryOk> list=SQL("SELECT * FROM VeryOk WHERE userId='"+userEntity.getUserId()+"' AND cartoonSetId='"+id+"' AND okState=1 ",VeryOk.class);
		return list;
	}

	@Override
	public int getUserVseryOkCount(String id, UserEntity userEntity) {
		// 查询此话点赞次数
		int num=super.gettotalpage("SELECT * FROM VeryOk  WHERE cartoonSetId='"+id+"' AND okState=1 ");
		return num;
	}

	@Override
//	@CacheEvict(value="CartoonSetOk",key="#id.concat(#userId)")
	public boolean deleteVeryOK(String id,String userId) throws Exception {
		// 取消评论
		boolean flag=true;
		try {
			List<VeryOk> list = getUserSetVseryOk(id,userId);
			if(!ParaClick.clickList(list)){
				return flag;
			}
			for (int i = 0; i < list.size(); i++) {
				super.delete(list.get(i).getId());
			}
			JedisUtil.del("CVeryOk-"+id+userId);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE CartoonSet SET okCount=(okCount-1) WHERE id='"+id+"'");
		return flag;
	}

	@Override
//	@Cacheable(value="CartoonSetOk",key="#id.concat(#userId)")
	public List<VeryOk> getUserSetVseryOk(String id, String userId) {
		// 查看用户是否赞过此话
		List<VeryOk> list=super.SQL("CVeryOk-"+id+userId,3600,"SELECT * FROM VeryOk WHERE userId='"+userId+"' AND cartoonSetId='"+id+"' AND okState=1", VeryOk.class);
		return list;
	}
	
	
}