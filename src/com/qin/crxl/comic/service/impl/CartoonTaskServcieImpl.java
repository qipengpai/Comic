package com.qin.crxl.comic.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonTask;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonTaskData;
import com.qin.crxl.comic.entity.vo.UserTaskData;
import com.qin.crxl.comic.service.CartoonTaskServcie;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.game.entity.redis.JedisUtil;
@Service
public class CartoonTaskServcieImpl extends BaseServiceImpl<CartoonTask>
		implements CartoonTaskServcie {

	@Override
//	@Cacheable(value = "UserTask",key="#root.methodName")
	public List<CartoonTask> getAllCartoonTask() {
		// 查詢所有任務
		//List<CartoonTask> list=super.SQL("Task",3600,"SELECT * FROM CartoonTask ORDER BY sort ASC", CartoonTask.class);
		List<CartoonTask> list=super.SQL("SELECT * FROM CartoonTask ORDER BY sort ASC", CartoonTask.class);
		return list;
	}

	@Override
//	@CacheEvict(value = "UserTask",allEntries=true)
	public boolean addTask(CartoonTaskData cartoonTaskData) {
		// 增加任务 
		boolean flag=false;
		try {
			CartoonTask cartoonTask=new CartoonTask();
			cartoonTask.setTaskAward(Integer.parseInt(cartoonTaskData.getTaskAward()));
			cartoonTask.setTaskName(cartoonTaskData.getTaskName());
			cartoonTask.setTaskInfo(cartoonTaskData.getTaskInfo());
			cartoonTask.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			cartoonTask.setState(Integer.parseInt(cartoonTaskData.getState()));
			cartoonTask.setTaskType(Integer.parseInt(cartoonTaskData.getTaskType()));
			save(cartoonTask);
			JedisUtil.del("Task");
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();		
			return flag;
		}
		return flag;
	}

}