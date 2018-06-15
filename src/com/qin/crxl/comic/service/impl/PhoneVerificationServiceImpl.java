package com.qin.crxl.comic.service.impl;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.PhoneVerification;
import com.qin.crxl.comic.service.PhoneVerificationService;

@Service
public class PhoneVerificationServiceImpl extends BaseServiceImpl<PhoneVerification> implements PhoneVerificationService{

	@Override
	public String temCode(String phone, String num) {
		//创建验证信息表
		PhoneVerification phoneVerification=new PhoneVerification();
		phoneVerification.setCode(num);
		phoneVerification.setPhone(phone);
		super.save(phoneVerification);
		return phoneVerification.getId();
	}

	@Override
	public String getCodeById(String codeId) {
		PhoneVerification code=get(codeId);
		if(code==null){
			return "";
		}else{
			return code.getCode();
		}
	}

}
