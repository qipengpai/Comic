package com.qin.crxl.comic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Feedback;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.FeedBackData;
@Service
@Transactional
public interface FeedbackService extends BaseService<Feedback>{

	boolean addFeedback(FeedBackData feedBackData, UserEntity userEntity);

}
