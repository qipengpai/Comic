package com.qin.crxl.comic.entity.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.service.UserService;
@Component
public class User{
	@Autowired
	private UserService userService2;

	/**
	 * pp
	 * 
	 * @Remarks app>用户注册>提交手机号密码
	 * @throws Exception
	 * */
	public UserEntity getUserInfoById(String id) {
		UserEntity user= userService2.getUserInfoById(id);
		return user;
	}

}
