package com.qin.crxl.comic.service.impl;

import java.util.Date;
import java.util.List;

import javassist.bytecode.LineNumberAttribute.Pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.CartoonTask;
import com.qin.crxl.comic.entity.HistoryRecord;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserTask;
import com.qin.crxl.comic.entity.vo.CartoonTaskData;
import com.qin.crxl.comic.entity.vo.UserTaskData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.CartoonTaskServcie;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.service.UserOrderService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.service.UserTaskService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.game.entity.redis.JedisUtil;

@Service
public class UserTaskServiceImpl extends BaseServiceImpl<UserTask> implements
		UserTaskService {

	@Autowired
	private CartoonTaskServcie cartoonTaskServcie;
	// @Autowired
	// private UserService userService;
	@Autowired
	private HistoryRecordService historyRecordService;
	@Autowired
	private UserOrderService userOrderService;

	@Override
	public List<UserTask> getAllUserTask(UserEntity userEntity) {
		// 查看用戶的任務表

		List<UserTask> list = getUserTask(userEntity.getUserId());

		String newTime = DateUtil.dateToTimeStamp(new Date());
		try {
			for (UserTask userTask : list) {
				if (userTask.getType() == 2) {
					if (DateUtil.getdate_yyyy_MM_dd_00_00_00(
							userTask.getImplDate()).getTime() < DateUtil
							.getdate_yyyy_MM_dd_00_00_00(newTime + " 00:00:00")
							.getTime()) {
						userTask.setButtonState(0);
						// userTask.setImplDate(DateUtil
						// .getdate_yyyy_MM_dd_HH_MM_SS());
					}
				} else if (userTask.getType() == 3) {
					if (DateUtil.getdate_yyyy_MM_dd_00_00_00(
							userTask.getImplDate()).getTime() < DateUtil
							.getdate_yyyy_MM_dd_00_00_00(newTime + " 00:00:00")
							.getTime()) {
						userTask.setButtonState(1);
						// userTask.setImplDate(DateUtil
						// .getdate_yyyy_MM_dd_HH_MM_SS());
					}

				} else if (userTask.getType() == 4) {
					if (DateUtil.getdate_yyyy_MM_dd_00_00_00(
							userTask.getImplDate()).getTime() < DateUtil
							.getdate_yyyy_MM_dd_00_00_00(newTime + " 00:00:00")
							.getTime()) {
						userTask.setButtonState(0);
						// userTask.setImplDate(DateUtil
						// .getdate_yyyy_MM_dd_HH_MM_SS());
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	private List<UserTask> getUserTask(String userId) {
		// TODO Auto-generated method stub
		List<UserTask> list = SQL("SELECT * FROM UserTask WHERE userId='"
				+ userId + "' ORDER BY sort ASC", UserTask.class);
		return list;
	}

	@Override
	// @CacheEvict(value="UserTask",key="#userId")
	public boolean addUserTask(String userId) {
		// 給用戶增加用戶表
		boolean flag = false;
		try {
			List<CartoonTask> list = cartoonTaskServcie.getAllCartoonTask();
			if (!ParaClick.clickList(list)) {
				return flag;
			}
			for (int i = 0; i < list.size(); i++) {
				UserTask userTask = new UserTask();
				userTask.setTaskId(list.get(i).getId());
				userTask.setUserId(userId);
				userTask.setTaskState(0);
				if (list.get(i).getTaskType() == 3) {
					userTask.setButtonState(1);
				} else {
					userTask.setButtonState(0);
				}
				userTask.setImplDate("2018-01-01 00:00:01");
				userTask.setType(list.get(i).getTaskType());
				userTask.setSort(i + 1);
				super.save(userTask);
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean addUserFollowTask(String userId) {
		// 增加关注任务
		boolean flag = false;
		try {
			UserTask userTask = new UserTask();
			userTask.setTaskId(8);
			userTask.setUserId(userId);
			userTask.setTaskState(0);
			userTask.setButtonState(0);
			userTask.setImplDate("2018-01-01 00:00:01");
			userTask.setType(1);
			userTask.setSort(8);
			save(userTask);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserTask getTaskByUser(UserTaskData userTaskData) {
		// 查詢簽到任務
		List<UserTask> list = SQL(
				"SELECT * FROM UserTask WHERE userId='"
						+ userTaskData.getUserId() + "' AND taskId='"
						+ userTaskData.getTaskId() + "'", UserTask.class);
		if (ParaClick.clickList(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean updateSignState(UserTaskData userTaskData) throws Exception {
		// 簽到
		boolean flag = false;
		try {
			UserTask userTask = super.get(userTaskData.getId());
			if (userTask.getTaskState() < 7) {
				// if(userTask.getTaskState()==6){
				// userTask.setTaskState(userTask.getTaskState()+1);
				//
				// }else{
				// boolean flag2 = userService.updateUserIntegral(
				// userTaskData.getUserId(), 10);
				// if (!flag2) {
				// throw new BusinessException("bus异常");
				// }
				userTask.setTaskState(userTask.getTaskState() + 1);
			} else {
				userTask.setTaskState(1);
			}
			userTask.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userTask.setButtonState(1);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		super.SQL("UPDATE UserEntity SET integral=(integral+10) WHERE userId='"
				+ userTaskData.getUserId() + "'");
		return flag;
	}

	@Override
	public boolean updateSignState2(UserTaskData userTaskData) throws Exception {
		// 簽到
		boolean flag = false;
		UserTask userTask = super.get(userTaskData.getId());
		try {
			userTask.setButtonState(1);
			userTask.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			// boolean flag2 = userService.updateUserIntegral(
			// userTaskData.getUserId(), 10);
			// if (!flag2) {
			// throw new BusinessException("bus异常");
			// }
			userTask.setTaskState(1);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		// 签到增加十积分
		super.SQL("UPDATE UserEntity SET integral=(integral+10) WHERE userId='"
				+ userTask.getUserId() + "'");
		return flag;
	}

	@Override
	public boolean updateUserInteAndState(UserTaskData userTaskData,
			int Integral) throws Exception {
		// 領取綁定手機獎勵
		boolean flag = false;
		UserTask userTask = super.get(userTaskData.getId());
		try {
			// userTask.setTaskState(0);
			userTask.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userTask.setButtonState(2);
			// if (userTask.getType()==1) {
			// JedisUtil.batchDel("UserTask"+userTask.getUserId());
			// }
			// boolean flag2 = userService.updateUserIntegral(
			// userTaskData.getUserId(), Integral);
			// if (!flag2) {
			// throw new BusinessException("bus异常");
			// }
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		// 增加積分
		super.SQL("UPDATE UserEntity SET integral=(integral+" + Integral
				+ ") WHERE userId='" + userTask.getUserId() + "'");
		return flag;
	}

	@Override
	public boolean finallyShare(String userId) {
		// 分享后修改任务的分享状态
		boolean flag = false;
		try {
			List<UserTask> list = SQL("SELECT * FROM UserTask WHERE userId='"
					+ userId + "' AND taskId=3", UserTask.class);
			if (!ParaClick.clickList(list)) {
				return flag;
			}
			if (list.get(0).getButtonState() == 0) {
				list.get(0).setTaskState(0);
				list.get(0).setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
				list.get(0).setButtonState(1);
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

	@Override
	public UserTask getTaskByUser(String id, String userId) {
		List<UserTask> list = SQL("SELECT * FROM UserTask WHERE userId='"
				+ userId + "' AND taskId='" + id + "'", UserTask.class);
		if (ParaClick.clickList(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	// @Cacheable(value="UserTask",key="#userId")
	public List<UserTask> getAllTask(String userId) {
		// 查看用户是否存在任务表
		List<UserTask> list = SQL("SELECT * FROM UserTask WHERE userId='"
				+ userId + "'", UserTask.class);
		// List<UserTask> list =
		// SQL("UserTask-"+userId,36000,"SELECT * FROM UserTask WHERE userId='"
		// + userId
		// + "'", UserTask.class);
		return list;
	}

	@Override
	public boolean finishTask(String userId, String openid) {
		// 查看任务是否完成
		boolean flag = false;
		try {
			List<UserTask> list = getUserTask(userId);
			;
			if (!ParaClick.clickList(list)) {
				return flag;
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getTaskId() == 5) {
					if (list.get(i).getButtonState() == 0) {
						List<HistoryRecord> historyRecord = historyRecordService
								.getHisToday(userId);
						if (ParaClick.clickList(list)) {
							if (historyRecord.size() >= 2) {
								list.get(i).setButtonState(1);
								list.get(i).setImplDate(
										DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
							}
						}
					}
				}
				if (list.get(i).getTaskId() == 6) {
					if (list.get(i).getButtonState() == 0) {
						int userOrder = userOrderService.getUserOrderByTask(
								userId, openid);
						if (userOrder > 0) {
							list.get(i).setButtonState(1);
							list.get(i).setImplDate(
									DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
						}
					}
				}
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean finallyShareComic(String userId) {
		// 分享后修改任务的分享状态
		boolean flag = false;
		try {
			List<UserTask> list = SQL("SELECT * FROM UserTask WHERE userId='"
					+ userId + "' AND taskId=4", UserTask.class);
			if (!ParaClick.clickList(list)) {
				return flag;
			}
			if (list.get(0).getButtonState() == 0) {
				list.get(0).setTaskState(0);
				list.get(0).setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
				list.get(0).setButtonState(1);
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

	@Override
	public boolean finallyFollow(String id) {
		// 关注后修改任务的关注状态
		boolean flag = false;
		try {
			UserTask usertask = get(id);
			if (usertask ==null) {
				return flag;
			}
			if (usertask.getButtonState() == 0) {
				usertask.setTaskState(0);
				usertask.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
				usertask.setButtonState(1);
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}

		return flag;
	}

}