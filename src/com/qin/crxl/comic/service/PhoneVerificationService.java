package com.qin.crxl.comic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.PhoneVerification;

@Service
@Transactional
public interface PhoneVerificationService extends BaseService<PhoneVerification> {

	String temCode(String phone, String num);

	String getCodeById(String codeId);

}
