package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.VeryOk;
import com.qin.crxl.comic.entity.vo.CartoonSetData;

@Service
@Transactional
public interface VeryOkService extends BaseService<VeryOk>{

	boolean addVeryOK(String string, String string2) throws Exception;

	List<VeryOk> getUserVseryOk(String id,
			UserEntity userEntity);

	int getUserVseryOkCount(String id, UserEntity userEntity);

	boolean deleteVeryOK(String string, String string2) throws Exception;

	List<VeryOk> getUserSetVseryOk(String id, String userId);

}
