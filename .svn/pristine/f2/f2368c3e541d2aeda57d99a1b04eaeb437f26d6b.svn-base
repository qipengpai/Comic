package com.qin.crxl.comic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Administrators;
import com.qin.crxl.comic.entity.vo.AdministratorsData;
/**
 * 管理员接口
 * @author cui
 *
 */
@Service
@Transactional
public interface AdministratorsService extends BaseService<Administrators> {
	//管理员注册
	boolean addAdministrators(AdministratorsData administratorsData);
	
	//管理员登录
	Administrators loginAdministrators(AdministratorsData administratorsData);
	
	//管理员修改密码
	boolean updateAdminPwd(AdministratorsData administratorsData,String newPwd);
}
