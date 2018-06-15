package com.qin.crxl.comic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.FriendsVeryOk;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FriendsCircleData;

@Service
@Transactional
public interface FriendsVeryOkService extends BaseService<FriendsVeryOk> {

	boolean deleteByFriendsCircleVeryOkId(FriendsCircleData friendsCircleData);

	boolean addVeryOK(String string2, String string)throws Exception ;

	List<FriendsVeryOk> getUserFriendsCircleVeryOk(String id,
			String string);

	boolean deleteVeryOK(String string2,
			String string) throws Exception;

}
