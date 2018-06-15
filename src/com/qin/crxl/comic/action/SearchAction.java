package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.HistorySearch;
import com.qin.crxl.comic.entity.SearchHistory;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.SearchInitializationData;
import com.qin.crxl.comic.service.CartoonAllTypeService;
import com.qin.crxl.comic.service.CartoonCommentService;
import com.qin.crxl.comic.service.CartoonService;
import com.qin.crxl.comic.service.CartoonTypeService;
import com.qin.crxl.comic.service.FollowCartoonService;
import com.qin.crxl.comic.service.HistoryRecordService;
import com.qin.crxl.comic.service.HistorySearchServcie;
import com.qin.crxl.comic.service.SearchHistoryService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class SearchAction {
	@Autowired
	private CartoonService cartoonService;
	@Autowired
	private UserService userService;
	@Autowired
	private SearchHistoryService searchHistoryService;
	@Autowired
	private HistoryRecordService historyRecordService;
	@Autowired
	private FollowCartoonService followCartoonService;
	@Autowired
	private CartoonCommentService cartoonCommentService;
	@Autowired
	private CartoonTypeService cartoonTypeService;
	@Autowired
	private CartoonAllTypeService cartoonAllTypeService;
	
	@Autowired
	private HistorySearchServcie historySearchServcie;
	/**
	 * h5
	 * @author pp
	 * @throws Exception 
	 * @date 2017年12月7日
	 * @Remarks 搜索
	 * @Tile={<h1>....</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_SEARCH, method = RequestMethod.POST)
	public Model getSearch(SearchInitializationData searchInitializationData) throws Exception{
		searchInitializationData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(searchInitializationData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if(ParaClick.clickString(searchInitializationData.getContent())){
			return new Model(200,"查询条件为空");
		}
		List<Cartoon> list=cartoonService.getSearchLike(searchInitializationData.getContent());
		if(!ParaClick.clickList(list)){
			return new Model(200,"暂无相关信息");
		}
		boolean flag=historySearchServcie.addHistory(searchInitializationData.getContent(),userEntity.getUserId());
		if (!flag) {
			return new Model(500,"增加历史失败");
		}
//		for (int i = 0; i < list.size(); i++) {
//			Hashtable<String, Object> hash2=new Hashtable<String, Object>();
//			List<CartoonAllType> cartoonAllType=cartoonAllTypeService.getByCartoonId(list.get(i).getId());
//			List<Object> list3 = new ArrayList<Object>();
//			for (int j = 0; j < cartoonAllType.size(); j++) {
//				 Hashtable<String, Object> hash=new Hashtable<String, Object>();
//				CartoonType cartoonType=cartoonTypeService.get(cartoonAllType.get(i).getCartoonTypeId());
//				if (cartoonType==null) {
//					return new Model(500,"类型为空");
//				}
//				hash.put("carrtoonType", cartoonType);
//				list3.add(hash);
//			}
//			hash2.put("map", list3);
//			hash2.put("cartoon", list.get(i));
//			list2.add(hash2);
//		}
		return new Model(200, list);
	}
	
	
	/**
	 * h5
	 * @author pp
	 * @date 2017年12月6日
	 * @Remarks 热搜
	 * @Tile={<h1>搜索初始化</h1>}
	 * @describe={<p>进热搜等关键词</p>}
	 *
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_SEARCH_INITIALIZATION, method = RequestMethod.POST)
	public Model getSearchHot(SearchInitializationData searchInitializationData) throws Exception{
		searchInitializationData.clickUser();

		//获取热门搜索
		List<String> hot=new ArrayList<String>();
		List<Cartoon> ctList=cartoonService.getHot0_8();
		if(ctList!=null&& ctList.size()>0){
			for(Cartoon ct:ctList){
				hot.add(ct.getCartoonName());
			}
		}
		return new Model(200, hot);
	}
	
	/**
	 * ios
	 * @author pp
	 * @throws Exception 
	 * @date 2017年12月7日
	 * @Remarks 搜索
	 * @Tile={<h1>....</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_SEARCH, method = RequestMethod.POST)
	public Model getSearchByIos(SearchInitializationData searchInitializationData) throws Exception{
		searchInitializationData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(searchInitializationData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		if(ParaClick.clickString(searchInitializationData.getContent())){
			return new Model(200,"查询条件为空");
		}
		List<Cartoon> list=cartoonService.getSearchLikeByIos(searchInitializationData.getContent(),userEntity.getPlatformIndex());
		if(!ParaClick.clickList(list)){
			return new Model(200,"暂无相关信息");
		}
		boolean flag=historySearchServcie.addHistory(searchInitializationData.getContent(),userEntity.getUserId());
		if (!flag) {
			return new Model(500,"增加历史失败");
		}
		return new Model(200, list);
	}
	
	
	/**
	 * ios
	 * @author pp
	 * @date 2017年12月6日
	 * @Remarks
	 * @Tile={<h1>搜索初始化</h1>}
	 * @describe={<p>进热搜等关键词</p>}
	 *
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.IOS_GET_SEARCH_INITIALIZATION, method = RequestMethod.POST)
	public Model getSearchHotByIos(SearchInitializationData searchInitializationData) throws Exception{
		searchInitializationData.clickUser();
		UserEntity userEntity = userService.getUserInfoByAPP(
				searchInitializationData.getUserId(), searchInitializationData.getUuid());
		if (userEntity == null) {
			return new Model(700, "无用户");
		}
		//获取热门搜索
		List<String> hot=new ArrayList<String>();
		List<Cartoon> ctList=cartoonService.getHot0_8ByIos(userEntity.getPlatformIndex());
		if(ctList!=null&& ctList.size()>0){
			for(Cartoon ct:ctList){
				hot.add(ct.getCartoonName());
			}
		}
		return new Model(200, hot);
	}
	/**
	 * 搜索历史
	 * @author pp
	 * @date 2017年12月6日
	 * @Remarks
	 * @Tile={<h1>搜索初始化</h1>}
	 * @describe={<p>进入功能加载历史搜索记录</p>}
	 *
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_SEARCH_HIS_INITIALIZATION, method = RequestMethod.POST)
	public Model getSearchHis(SearchInitializationData searchInitializationData) throws Exception{
		searchInitializationData.clickUser();
		//获取用户的历史记录
		List<HistorySearch> shList=historySearchServcie.getHistory(searchInitializationData.getUserId());
		List<Object> list=new ArrayList<Object>();
		for (HistorySearch historySearch : shList) {
			list.add(historySearch.getContent());
		}
		return new Model(200,list);
	}

}
