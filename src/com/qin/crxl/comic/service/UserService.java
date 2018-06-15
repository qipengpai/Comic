package com.qin.crxl.comic.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Banner;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;
import com.qin.crxl.comic.entity.vo.UserEntityData;

@Service
@Transactional
public interface UserService extends BaseService<UserEntity>{

	UserEntity updateUser(UserEntity entity, String wxqd) throws Exception;

//	boolean addUserEntity(UserEntityData userEntityData);

	List<UserEntity>   getByPhone(UserEntityData userEntityData);

	List<UserEntity> getUserEntityByLogin(UserEntityData userEntityData);

	boolean userProfectInfo(UserEntityData userEntityData);

	boolean userProfectInfoh5(UserEntityData userEntityData);
	
	boolean userUpdateHeadPortrict(
			UserEntityData userEntityData);

	UserEntity getByOpenId(String openid);

	boolean updateVipState(String id, Integer integer);

	UserEntity updateMoney(String userid, Integer orderCurrency);
	
	UserEntity getUserInfoById(String string);
	
	UserEntity updateUserPhone(UserEntity user, String phone)throws Exception ;

	boolean deductTheIntegral(CartoonPhotoData cartoonPhotoData,
			UserEntity userEntity);

	boolean updateVipState2(UserEntity userEntity);

	boolean updateUserIntegral(String userId, int integral);

	boolean chackVip();

	List<Object[]> getById(String userId);

	List<UserEntity> getByPhone(String truePhone);

	boolean setLoginDate(String userId);

	List<UserEntity> getByTRUEPhone(UserEntityData userEntityData);

	UserEntity addUserEntity(UserEntityData userEntityData);

	boolean addUserEntity2(UserEntityData userEntityData);

	UserEntity userRegisterByQQSINAWX(UserEntityData userEntityData);

	UserEntity getAPPByOpenId(String openid, String string);

	UserEntity getUserInfoByAPP(String userId, String uuid);

	boolean updateVipStateByIOS(String userId, int currency);

	boolean deductTheIntegralByIos(String userId,int price);

	UserEntity getByTRUEPhoneUpdatePwd(UserEntityData userEntityData);


}