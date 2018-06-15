package com.qin.crxl.comic.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Administrators;
import com.qin.crxl.comic.entity.vo.AdministratorsData;
import com.qin.crxl.comic.service.AdministratorsService;
import com.qin.crxl.comic.tool.MD5;
import com.qin.crxl.comic.tool.ParaClick;
@Service
public class AdministratorsServiceImpl extends BaseServiceImpl<Administrators>
		implements AdministratorsService {

	@Override
	public boolean addAdministrators(AdministratorsData administratorsData) {
		try {
			Administrators adminstrators=new Administrators();
			adminstrators.setAdminName(administratorsData.getAdminName());
			adminstrators.setAdminPwd(MD5.getMd5(administratorsData.getAdminPwd()));
			adminstrators.setName(administratorsData.getName());
			adminstrators.setPhone(administratorsData.getPhone());
			super.save(adminstrators);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public Administrators loginAdministrators(AdministratorsData administratorsData) {
		List<Administrators> list=null;
		try {
			list = SQL("SELECT * from Administrators WHERE (adminName='"+administratorsData.getAdminName()+"' or phone='"+administratorsData.getPhone()+"') and adminPwd='"+MD5.getMd5(administratorsData.getAdminPwd())+"'", Administrators.class);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}				
		return null;
	}

	@Override
	public boolean updateAdminPwd(AdministratorsData administratorsData,String newPwd) {
		try {
			List<Administrators> list=SQL("SELECT * from Administrators WHERE (adminName='"+administratorsData.getAdminName()+"' or phone='"+administratorsData.getPhone()+"') and adminPwd='"+MD5.getMd5(administratorsData.getAdminPwd())+"'", Administrators.class);				
			if(ParaClick.clickList(list)){
				list.get(0).setAdminPwd(MD5.getMd5(newPwd));
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	

}
