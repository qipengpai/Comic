package com.qin.crxl.comic.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.bytecode.LineNumberAttribute.Pc;

import org.apache.log4j.Logger;
import org.jaxen.function.IdFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.entity.vo.UserEntityData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.service.UserTaskService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.EmojiFilter;
import com.qin.crxl.comic.tool.MD5;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;
import com.qin.game.entity.redis.JedisUtil;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements
		UserService {
	@Autowired
	private UserTaskService userTaskService;

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(UserServiceImpl.class
			.getName());

	@Override
	public UserEntity updateUser(UserEntity entity, String wxqd)
			throws Exception {
		List<UserEntity> userEntity = SQL(
				"SELECT * from UserEntity WHERE openid ='" + entity.getOpenid()
						+ "'", UserEntity.class);
		if (userEntity != null && userEntity.size() > 0) {
			for (UserEntity old : userEntity) {
				old.setHeadimgurl(entity.getHeadimgurl());
				// old.setHeadPortrait(entity.getHeadimgurl());
				// if (EmojiFilter.containsEmoji(entity.getNickname()) != false)
				// {
				// old.setNickname(StringToInt.toInt("咔咔用户"));
				// old.setUsername(StringToInt.toInt("咔咔用户"));
				// } else {
				// if ("PT".equals(wxqd)) {
				// if (!"PT".equals(old.getPlatformIndex())) {
				// old.setPlatformIndex(wxqd);
				// }
				// }
				if (!ParaClick.clickString(wxqd)) {
					old.setPlatformIndex(wxqd);
				} else {
					old.setPlatformIndex("PT");
				}
				old.setUsername(StringToInt.toInt(entity.getNickname()));
				old.setNickname(StringToInt.toInt(entity.getNickname()));
				old.setCountry(StringToInt.toInt(entity.getCountry()));
				old.setProvince(StringToInt.toInt(entity.getProvince()));
				old.setCity(StringToInt.toInt(entity.getCity()));
				// if (old.getSubscribe() == 1) {
				// SQL("UPDATE UserTask SET taskState=1 WHERE userId='"
				// + old.getUserId() + "' AND taskId=5");
				// }
				// old.setLastLoginDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			}
			log.info("-----数据库用户:" + userEntity.toString() + "------");
			return userEntity.get(0);
		} else {
			// if (EmojiFilter.containsEmoji(entity.getNickname()) != false) {
			// entity.setNickname(StringToInt.toInt("咔咔用户"));
			// entity.setUsername(StringToInt.toInt("咔咔用户"));
			// } else {
			entity.setUsername(StringToInt.toInt(entity.getNickname()));
			entity.setNickname(StringToInt.toInt(entity.getNickname()));
			// }s
			entity.setProvince(StringToInt.toInt(entity.getProvince()));
			entity.setCity(StringToInt.toInt(entity.getCity()));
			entity.setCountry(StringToInt.toInt(entity.getCountry()));
			// entity.setHeadPortrait(entity.getHeadimgurl());
			entity.setHeadimgurl(entity.getHeadimgurl());
			// entity.setLastLoginDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			if (!ParaClick.clickString(wxqd)) {
				entity.setPlatformIndex(wxqd);
			} else {
				entity.setPlatformIndex("PT");
			}
			save(entity);
			// if (entity.getSubscribe() == 1) {
			// SQL("UPDATE UserTask SET taskState=1 WHERE userId='"
			// + entity.getUserId() + "' AND taskId=9");
			// }
		}
		return entity;
	}

	@Override
	public UserEntity addUserEntity(UserEntityData userEntityData) {
		// 创建用户并给以任务表及完成绑定手机任务
		boolean flag = false;
		UserEntity userEntity = new UserEntity();
		try {
			// 创建用户
			userEntity.setPhone(userEntityData.getPhone());
			userEntity.setUserPassword(MD5.getMd5(userEntityData
					.getUserPassword()));
			// userEntity.setHeadPortrait("http://p0oqd5s9w.bkt.clouddn.com/1516760313892@qinruida-WpVPddJe.png");
			userEntity
					.setHeadimgurl("http://p0oqd5s9w.bkt.clouddn.com/1516760313892@qinruida-WpVPddJe.png");
			userEntity.setRegisterDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			userEntity
					.setUsername(StringToInt.toInt(userEntityData.getPhone()));
			userEntity.setPlatformIndex(userEntityData.getPlatformIndex());
			if (!ParaClick.clickString(userEntityData.getDeviceId())) {
				userEntity.setDeviceId(userEntityData.getDeviceId());
			}
			if (!ParaClick.clickString(userEntityData.getSystemVersion())) {
				userEntity.setSystemVersion(userEntityData.getSystemVersion());
			}
			save(userEntity);
			// 为创建用户生成任务表
			boolean flag2 = userTaskService.addUserTask(userEntity.getUserId());
			if (!flag2) {
				throw new BusinessException("增加任务异常");
			}
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		if (!flag) {
			return null;
		}
		return userEntity;
	}

	@Override
	public boolean addUserEntity2(UserEntityData userEntityData) {
		// 绑定手机任务
		boolean flag = false;
		UserEntity userEntity = super.get(userEntityData.getUserId());
		try {
			userEntity.setPhone(userEntityData.getPhone());
			// userEntity.setUserPassword(MD5.getMd5(userEntityData
			// .getUserPassword()));
			userEntity.setRegisterDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			SQL(" UPDATE UserTask SET buttonState=1 WHERE userId='"
					+ userEntity.getUserId() + "' AND taskId=7");
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	@Override
	public List<UserEntity> getByPhone(UserEntityData userEntityData) {
		// 查看手机号是否被注册
		List<UserEntity> list = super.SQL(
				"SELECT * FROM UserEntity WHERE phone='"
						+ userEntityData.getPhone() + "'", UserEntity.class);
		if (!ParaClick.clickList(list)) {
			return null;
		}
		return list;
	}

	@Override
	public List<UserEntity> getUserEntityByLogin(UserEntityData userEntityData) {
		// 用户登录
		List<UserEntity> list = super.SQL(
				"SELECT * FROM UserEntity WHERE phone='"
						+ userEntityData.getPhone() + "' AND  userPassword='"
						+ MD5.getMd5(userEntityData.getUserPassword()) + "'",
				UserEntity.class);
		if (!ParaClick.clickList(list)) {
			return null;
		}
		list.get(0).setDeviceId(userEntityData.getUuid());
		return list;
	}

	@Override
	public boolean userProfectInfo(UserEntityData userEntityData) {
		// app用戶完善信息
		boolean flag = false;
		try {
			UserEntity userEntity = super.get(userEntityData.getUserId());
			if (userEntity == null) {
				return flag;
			}
			if (!ParaClick.clickString(userEntityData.getUsername())) {
				// if (EmojiFilter.containsEmoji(userEntityData.getUsername())
				// != false) {
				// userEntity.setUsername(StringToInt.toInt("咔咔用户"));
				// } else {
				userEntity.setUsername(StringToInt.toInt(userEntityData
						.getUsername()));
				// }
			}
			if (!ParaClick.clickString(userEntityData.getSex())) {
				userEntity.setSex(Integer.parseInt(userEntityData.getSex()));
			}
			if (!ParaClick.clickString(userEntityData.getHobby())) {
				userEntity.setHobby(userEntityData.getHobby());
			}
			if (!ParaClick.clickString(userEntityData.getCountry())) {
				userEntity.setCountry(StringToInt.toInt(userEntityData
						.getCountry()));
			}
			if (!ParaClick.clickString(userEntityData.getProvince())) {
				userEntity.setProvince(StringToInt.toInt(userEntityData
						.getProvince()));
			}
			if (!ParaClick.clickString(userEntityData.getCity())) {
				userEntity.setCity(StringToInt.toInt(userEntityData.getCity()));
			}
			userEntity.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			JedisUtil.del("USER-" + userEntity.getUserId());
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean userProfectInfoh5(UserEntityData userEntityData) {
		// h5用戶完善信息
		boolean flag = false;
		try {
			UserEntity userEntity = super.get(userEntityData.getUserId());
			if (userEntity == null) {
				return flag;
			}
			if (!ParaClick.clickString(userEntityData.getUsername())) {

				userEntity.setUsername(StringToInt.toInt(userEntityData
						.getUsername()));
			}
			if (!ParaClick.clickString(userEntityData.getSex())) {
				userEntity.setSex(Integer.parseInt(userEntityData.getSex()));
			}
			if (!ParaClick.clickString(userEntityData.getHobby())) {
				userEntity.setHobby(userEntityData.getHobby());
			}
			if (!ParaClick.clickString(userEntityData.getCountry())) {
				userEntity.setCountry(StringToInt.toInt(userEntityData
						.getCountry()));
			}
			if (!ParaClick.clickString(userEntityData.getProvince())) {
				userEntity.setProvince(StringToInt.toInt(userEntityData
						.getProvince()));
			}
			if (!ParaClick.clickString(userEntityData.getCity())) {
				userEntity.setCity(StringToInt.toInt(userEntityData.getCity()));
			}
			userEntity.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			JedisUtil.del("USER-" + userEntity.getUserId());
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean userUpdateHeadPortrict(UserEntityData userEntityData) {
		// 修改用户头像
		boolean flag = false;
		try {
			UserEntity userEntity = super.get(userEntityData.getUserId());
			if (userEntity == null) {
				return flag;
			}
			userEntity.setHeadimgurl(userEntityData.getHeadimgurl());
			JedisUtil.del("USER-" + userEntity.getUserId());
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public UserEntity getByOpenId(String openid) {
		// 再次查看用户信息
		List<UserEntity> list = SQL("SELECT * from UserEntity WHERE openid ='"
				+ openid + "'", UserEntity.class);
		System.out.println("再次查看用户信息:" + list);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public static Date addDate(Date date, long day) throws ParseException {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
		time += day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}

	@Override
	public boolean updateVipState(String userid, Integer integer) {
		// 设置vip
		boolean flag = false;
		try {
			UserEntity userEntity = super.get(userid);
			if (userEntity == null) {
				return flag;
			}
			if (userEntity.getVipId() == 1) {
				if (integer == 20) {
					String start = userEntity.getEndDate();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 31); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
				} else if (integer == 50) {
					String start = userEntity.getEndDate();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 91); // 指定日期加上92天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
				} else if (integer == 198) {
					String start = userEntity.getEndDate();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 366); // 指定日期加上366天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
				} else if (integer == 365) {
					String start = userEntity.getEndDate();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					int day = 0;
					if (userEntity.getIntegral() > 0) {
						if (userEntity.getIntegral() % 100 != 0) {
							day = (userEntity.getIntegral() / 100) + 1;
						}
						day = userEntity.getIntegral() / 100;
					}
					Date end = addDate(date, 366 + day); // 指定日期加上366天
					SQL("DELETE FROM MallCartoonOrder WHERE userId='"+userEntity.getUserId()+"'");
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(2);
					userEntity.setIntegral(0);
				}
			} else if (userEntity.getVipId() == 2) {
				if (integer == 365) {
					String start = userEntity.getEndDate();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 366); // 指定日期加上366天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(2);
				}
			} else {
				if (integer == 20) {
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					String start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 31); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
				} else if (integer == 50) {
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					String start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 91); // 指定日期加上92天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
				} else if (integer == 198) {
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					String start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					Date end = addDate(date, 366); // 指定日期加上366天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
				} else if (integer == 365) {
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					String start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss"); // 日期格式
					Date date = dateFormat.parse(start); // 指定日期
					int day = 0;
					if (userEntity.getIntegral() > 0) {
						if (userEntity.getIntegral() % 100 != 0) {
							day = (userEntity.getIntegral() / 100) + 1;
						}
						day = userEntity.getIntegral() / 100;
					}
					Date end = addDate(date, 366 + day); // 指定日期加上366天
					SQL("DELETE FROM MallCartoonOrder WHERE userId='"+userEntity.getUserId()+"'");
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(2);
					userEntity.setIntegral(0);
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
	public UserEntity updateMoney(String userid, Integer orderCurrency) {
		UserEntity user = super.get(userid);
		try {
			int num = 0;
			if (user.getIntegral() >= 0) {
				num = user.getIntegral();
			}
			user.setIntegral(num + orderCurrency);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	// @Cacheable(value = "Forever",key="#string")
	public UserEntity getUserInfoById(String string) {
		// 拿到用户
		List<UserEntity> userEntity = SQL("USER-" + string, 3600,
				"SELECT * FROM UserEntity WHERE userId='" + string + "'",
				UserEntity.class);
		if (!ParaClick.clickList(userEntity)) {
			return null;
		}
		return userEntity.get(0);
	}

	@Override
	public UserEntity getUserInfoByAPP(String userId, String uuid) {
		// APP 拿到用户 &&单点登录
		// List<UserEntity> userEntity = SQL("APPUSER-" + userId, 3600,
		// "SELECT * FROM UserEntity WHERE userId='" + userId +
		// "' AND devicled='"+uuid+"'",
		// UserEntity.class);
		List<UserEntity> userEntity = SQL("AppleUser" + userId + uuid, 1800,
				"SELECT * FROM UserEntity WHERE userId='" + userId
						+ "' AND deviceId='" + uuid + "'", UserEntity.class);
		if (!ParaClick.clickList(userEntity)) {
			return null;
		}
		return userEntity.get(0);
	}

	@Override
	public UserEntity updateUserPhone(UserEntity user, String phone)
			throws Exception {
		// 用户修改手机号
		boolean flag = false;
		List<UserEntity> list = new ArrayList<UserEntity>();
		try {
			list = SQL(
					"SELECT * from UserEntity WHERE userId ='"
							+ user.getUserId() + "'", UserEntity.class);
			if (list != null && list.size() > 0) {
				for (UserEntity u : list) {
					u.setTruePhone(phone);
					u.setTruePhoneDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
				}
				SQL(" UPDATE UserTask SET buttonState=1 WHERE userId='"
						+ user.getUserId() + "' AND taskId=7");
				JedisUtil.del("USER-" + user.getUserId());
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!flag) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean deductTheIntegral(CartoonPhotoData cartoonPhotoData,
			UserEntity userEntity) {
		// 購買後扣除積分
		boolean flag = false;
		try {
			UserEntity userEntity2 = super.get(userEntity.getUserId());
			if (userEntity2 == null) {
				return flag;
			}
			userEntity2.setIntegral(userEntity2.getIntegral()
					- Integer.parseInt(cartoonPhotoData.getPrice()));
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateVipState2(UserEntity userEntity) {
		// 簽到送會員
		boolean flag = false;
		try {
			UserEntity userEntity2 = super.get(userEntity.getUserId());
			if (userEntity2 == null) {
				return flag;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss"); // 日期格式
			if (userEntity2.getVipId() == 0) {
				userEntity2.setVipId(1);
				userEntity2
						.setStartDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
				String start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();

				Date date = dateFormat.parse(start); // 指定日期
				Date end = addDate(date, 1); // 指定日期加上1天
				userEntity2.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
				flag = true;
			} else {
				userEntity2.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(addDate(
						dateFormat.parse(userEntity2.getEndDate()), 1)));
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateUserIntegral(String userId, int integral) {
		// 怎加用戶積分
		boolean flag = false;
		try {
			UserEntity userEntity2 = super.get(userId);
			if (userEntity2 == null) {
				return flag;
			}
			userEntity2.setIntegral(userEntity2.getIntegral() + integral);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean chackVip() {
		// 檢查會員是否過期
		boolean flag = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 日期格式
		try {
			List<UserEntity> list = super.SQL(
					"SELECT * FROM UserEntity WHERE vipId>0 AND endDate<'"+DateUtil.getdate_yyyy_MM_dd_HH_MM_SS()+"'", UserEntity.class);
			if (!ParaClick.clickList(list)) {
				return true;
			}
			for (int i = 0; i < list.size(); i++) {
				if (dateFormat.parse(list.get(i).getEndDate()).getTime() < new Date()
						.getTime()) {
					list.get(i).setVipId(0);
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
	public List<Object[]> getById(String userId) {
		// 去除冗余数据
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT headimgurl,userId,nickname FROM UserEntity  WHERE userId='"
				+ userId + "'");
		@SuppressWarnings("unchecked")
		List<Object[]> list = super.getSessionFactory()
				.createSQLQuery(sb.toString()).list();
		return list;
	}

	@Override
	public List<UserEntity> getByPhone(String truePhone) {
		// 查看手机号是否被绑定
		List<UserEntity> list = super.SQL(
				"SELECT * FROM UserEntity WHERE truePhone='" + truePhone + "'",
				UserEntity.class);
		return list;
	}

	@Override
	public boolean setLoginDate(String userId) {
		// 修改最后登录时间
		boolean flag = false;
		try {
			UserEntity userEntity = super.get(userId);
			userEntity.setLastLoginDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<UserEntity> getByTRUEPhone(UserEntityData userEntityData) {
		// 查看手机号是否被注册
		List<UserEntity> list = super.SQL(
				"SELECT * FROM UserEntity WHERE truephone='"
						+ userEntityData.getPhone() + "'", UserEntity.class);
		return list;
	}

	@Override
	public UserEntity userRegisterByQQSINAWX(UserEntityData userEntityData) {
		// 第三方注册 新浪 qq wx
		boolean flag = false;
		UserEntity userEntity = new UserEntity();
		try {
			userEntity.setOpenid(userEntityData.getOpenid());
			userEntity.setHeadimgurl(userEntityData.getHeadimgurl());
			if (!ParaClick.clickString(userEntityData.getSex())) {
				userEntity.setSex(Integer.parseInt(userEntityData.getSex()));
			}
			// if (EmojiFilter.containsEmoji(userEntityData.getUsername()) !=
			// false) {
			// userEntity.setUsername(StringToInt.toInt("咔咔用户"));
			// } else {
			userEntity.setUsername(StringToInt.toInt(userEntityData
					.getUsername()));
			// }
			userEntity.setProvince(StringToInt.toInt(userEntityData
					.getProvince()));
			userEntity.setCity(StringToInt.toInt(userEntityData.getCity()));
			userEntity
					.setCountry(StringToInt.toInt(userEntityData.getCountry()));
			userEntity.setPlatformIndex(userEntityData.getPlatformIndex());
			userEntity.setRegisterDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			if (!ParaClick.clickString(userEntityData.getDeviceId())) {
				userEntity.setDeviceId(userEntityData.getDeviceId());
			}
			if (!ParaClick.clickString(userEntityData.getSystemVersion())) {
				userEntity.setSystemVersion(userEntityData.getSystemVersion());
			}
			userEntity.setLastLoginDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			save(userEntity);
			flag = true;
			boolean flag2 = userTaskService.addUserTask(userEntity.getUserId());
			if (!flag2)
				flag = false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!flag)
			return null;
		return userEntity;
	}

	@Override
	public UserEntity getAPPByOpenId(String openid, String uuid) {
		// 查看是否已存在用户
		List<UserEntity> list = super.SQL(
				"SELECT * FROM UserEntity WHERE openid='" + openid + "' ",
				UserEntity.class);
		if (!ParaClick.clickList(list)) {
			return null;
		}
		list.get(0).setDeviceId(uuid);
		return list.get(0);
	}

	@Override
	public boolean updateVipStateByIOS(String userId, int currency) {
		// IOS充值会员
		boolean flag = false;
		try {
			UserEntity userEntity = super.get(userId);
			if (userEntity == null) {
				return flag;
			}
			if (userEntity.getVipId() == 1) {
				String start = "";
				Date date = null;
				Date end = null;
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss"); // 日期格式
				switch (currency) {
				case 1:
					start = userEntity.getEndDate();

					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 31); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				case 3:
					start = userEntity.getEndDate();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 92); // 指定日期加上92天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				case 6:
					start = userEntity.getEndDate();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 184); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				case 12:
					start = userEntity.getEndDate();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 366); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				default:
					break;
				}
			} else {
				String start = "";
				Date date = null;
				Date end = null;
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss"); // 日期格式
				switch (currency) {
				case 1:
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 31); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				case 3:
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 91); // 指定日期加上92天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				case 6:
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 183); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				case 12:
					userEntity.setStartDate(DateUtil
							.getdate_yyyy_MM_dd_HH_MM_SS());
					start = DateUtil.getdate_yyyy_MM_dd_HH_MM_SS();
					date = dateFormat.parse(start); // 指定日期
					end = addDate(date, 366); // 指定日期加上31天
					userEntity.setEndDate(DateUtil.getdate_yyyy_MM_dd_Hms(end));
					userEntity.setVipId(1);
					break;
				default:
					break;
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
	public boolean deductTheIntegralByIos(String userId, int price) {
		// 購買後扣除積分
		boolean flag = false;
		try {
			UserEntity userEntity2 = super.get(userId);
			if (userEntity2 == null) {
				return flag;
			}
			userEntity2.setIntegral(userEntity2.getIntegral() - price);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public UserEntity getByTRUEPhoneUpdatePwd(UserEntityData userEntityData) {
		// 查看手机号是否被注册
		List<UserEntity> list = super.SQL(
				"SELECT * FROM UserEntity WHERE truephone='"
						+ userEntityData.getPhone() + "'", UserEntity.class);
		if (!ParaClick.clickList(list)) {
			return null;
		}
		list.get(0).setUserPassword(userEntityData.getUserPassword());
		return list.get(0);
	}

}
