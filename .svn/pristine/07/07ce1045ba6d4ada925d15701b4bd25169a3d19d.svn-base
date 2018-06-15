package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.CartoonTask;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonTaskData;
import com.qin.crxl.comic.entity.vo.UserTaskData;

@Service
@Transactional
public interface CartoonTaskServcie extends BaseService<CartoonTask>{

	List<CartoonTask> getAllCartoonTask();

	boolean addTask(CartoonTaskData cartoonTaskData);

}
