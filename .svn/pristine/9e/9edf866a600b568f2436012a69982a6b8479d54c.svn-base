package com.qin.crxl.comic.service;

import java.util.List;





























import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllModel;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonPhotoData;

@Service
@Transactional
public interface CartoonService extends BaseService<Cartoon> {


//	List<Cartoon> getALLCartoon(CartoonData cartoonData);

	List<Cartoon> getALLHotCartoon(CartoonData cartoonData);

	List<Cartoon> getSixHotCartoon();

	List<Cartoon> getALLCartoonbyType(CartoonData cartoonData);

	List<Cartoon> getNewCartoon(String string);

	List<Cartoon> getALLCartoonbyTypeThree(CartoonData cartoonData);

	int getNewCartoonCount(CartoonData cartoonData);

	int getALLCartoonCount(String cartoonType, String firsrtType);

	int getALLHotCartoonCount(CartoonData cartoonData);

	int getALLCartoonbyTypeCount(CartoonData cartoonData);

	int getALLCartoonbyTypeThreeCount(CartoonData cartoonData);

	List<Cartoon> getHot0_8();

	List<Cartoon> getSearchLike(String conext);

	int getALLCartoonCountByRankingList(CartoonData cartoonData);

	List<Cartoon> getALLCartoonByRankingList(CartoonData cartoonData);

	boolean backupsDatabases();

//	boolean addCommentCount(String cartoonId);

	boolean addPlayCount(String string);

	List<Cartoon> getALLCartoonByRankingListThree(CartoonData cartoonData);

	List<Cartoon> getALLCartoon(String cartoonType, String firsrtType,
			String nowPage);

	List<Cartoon> getALLCartoonByRMRankingList(CartoonData cartoonData);

	List<Cartoon> getNewCartoonByIos(String nowPage, String qd);

	int getNewCartoonCountIos(CartoonData cartoonData, String qd);

	List<Cartoon> getSixHotCartoonByIos(String nowPage, String qd);

	int getALLCartoonCountByIos(String cartoonType, String firsrtType, String qd);

	List<Cartoon> getALLCartoonByIos(String cartoonType, String firsrtType,
			String nowPage,String qd);

	int getALLCartoonbyTypeThreeCountByIos(CartoonData cartoonData, String qd);

	List<Cartoon> getALLCartoonbyTypeThreeByIos(CartoonData cartoonData, String qd);

	int getALLCartoonCountByRankingListByIos(CartoonData cartoonData, String qd);

	List<Cartoon> getALLCartoonByRMRankingListByIos(CartoonData cartoonData, String qd);

	List<Cartoon> getALLCartoonByRankingListByIos(CartoonData cartoonData, String qd);

	List<Cartoon> getSearchLikeByIos(String content, String qd);

	List<Cartoon> getHot0_8ByIos(String qd);

	List<Cartoon> getNewCartoonByIosNew(String nowPage, String qd);

	int getFreeCartoonMoreCount();

	List<Cartoon> getFreeCartoonMore(String nowPage);

	List<Cartoon> getCartoonMore(String cartoonModelId, String nowPage);

	int getCartoonMoreCount(String cartoonModelId);


//	boolean addFollowNum(CartoonData cartoonData);

//	boolean deleteFollowNum(CartoonData cartoonData);

	
}
