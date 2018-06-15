package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserTask;
import com.qin.crxl.comic.entity.vo.CartoonTaskData;
import com.qin.crxl.comic.entity.vo.UserTaskData;

@Service
@Transactional
public interface UserTaskService extends BaseService<UserTask>{

	List<UserTask> getAllUserTask(UserEntity userEntity);

	boolean addUserTask(String string);

	UserTask getTaskByUser(UserTaskData userTaskData);

	boolean updateSignState(UserTaskData userTaskData) throws Exception;

	boolean updateSignState2(UserTaskData userTaskData) throws Exception;

	boolean updateUserInteAndState(UserTaskData userTaskData, int i) throws Exception;

	boolean finallyShare(String string);

	UserTask getTaskByUser(String id, String userId);

	List<UserTask> getAllTask(String string);

	boolean finishTask(String userId, String openid);

	boolean finallyShareComic(String string);

	boolean addUserFollowTask(String userId);

	boolean finallyFollow(String userId);


}
