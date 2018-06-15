package com.qin.crxl.comic.service;

import java.util.List;







import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.UserEntityData;
@Service
@Transactional
public interface AdminUserEntityService extends BaseService<UserEntity>{

	//查询用户
	List<UserEntity> selectUser(UserEntityData userEntityData);
	
	//查询用户的数量
	int getCount(UserEntityData userEntityData);
	
	//查询用户 （id）
	UserEntity selectUserByID(String id);
	
	//查询用户性别比例
	List<Object[]> selectSexGroupBy();
	
	//查询粉丝用户分布(地域)
	List<Object[]> getCircleByArea();
	
	//查询粉丝用户分布(地域)
	List<Object[]> getCircleByArea(UserEntityData userEntityData);
	
	//查询粉丝用户分布(地域)
	int areaNum(UserEntityData userEntityData);
	
	//查询公众号粉丝分布
	List<Object[]> getFansByGongZhong();
	
}
