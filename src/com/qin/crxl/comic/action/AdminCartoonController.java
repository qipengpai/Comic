package com.qin.crxl.comic.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Cartoon;
import com.qin.crxl.comic.entity.CartoonAllModel;
import com.qin.crxl.comic.entity.CartoonAllType;
import com.qin.crxl.comic.entity.CartoonModel;
import com.qin.crxl.comic.entity.CartoonSet;
import com.qin.crxl.comic.entity.CartoonType;
import com.qin.crxl.comic.entity.vo.CartoonData;
import com.qin.crxl.comic.entity.vo.CartoonVo;
import com.qin.crxl.comic.service.AdminCartoonAllTypeService;
import com.qin.crxl.comic.service.AdminCartoonPhotoService;
import com.qin.crxl.comic.service.AdminCartoonService;
import com.qin.crxl.comic.service.AdminCartoonSetService;
import com.qin.crxl.comic.service.AdminCartoonTypeService;
import com.qin.crxl.comic.service.CartoonAllModelService;
import com.qin.crxl.comic.service.CartoonAllTypeService;
import com.qin.crxl.comic.service.CartoonModelService;
import com.qin.crxl.comic.service.CartoonTypeService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.weixin.dao.impl.WeiXinDaoImpl;
import com.qin.weixin.model.useValue;
/**
 * 漫画控制类
 * @author cui
 *
 */
@Controller
public class AdminCartoonController {
	@Autowired
	private AdminCartoonService adminCartoonService;
	@Autowired
	private AdminCartoonTypeService adminCartoonTypeService;
	@Autowired
	private AdminCartoonAllTypeService adminCartoonAllTypeService;
	@Autowired
	private AdminCartoonPhotoService adminCartoonPhotoService;
	@Autowired
	private AdminCartoonSetService adminCartoonSetService;
	@Autowired
	private CartoonAllTypeService cartoonAllTypeService;
	@Autowired
	private CartoonTypeService cartoonTypeService;
	@Autowired
	private CartoonAllModelService cartoonAllModelService;
	@Autowired
	private CartoonModelService cartoonModelService;
	
	
	/**
	 * pp
	 * 
	 * @Remarks 生成漫画每集推送链接
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOON_LIANJIE, method = RequestMethod.POST)
	public Model addCartoonSetLianjie(String cartoonId,String cartoonSetId,String qd){
		if(ParaClick.clickString(cartoonId)){
			return new Model(500,"漫画id为空");
		}
		if(ParaClick.clickString(cartoonId)){
			return new Model(500,"此话id为空");
		}
		WeiXinDaoImpl dao=new WeiXinDaoImpl();
		String url="http://www.kktoon.com/htd/#/login?qd="+qd+"&cartoonSetId="+cartoonSetId+"&cartoonId="+cartoonId+"&moneyState=0";
		String pathUrl=null;
		try {
			pathUrl=dao.getCodeUrl(useValue.AppId,url,"snsapi_userinfo", "state");
		} catch (Exception e) {
			
		}
		return new Model(200,pathUrl);
	}
	/**
	 * pp
	 * 
	 * @Remarks 生成每部漫画推送链接
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOON_TUIWEN_LIANJIE, method = RequestMethod.POST)
	public Model addCartoonLianjie(String cartoonId){
		if(ParaClick.clickString(cartoonId)){
			return new Model(500,"漫画id为空");
		}
		WeiXinDaoImpl dao=new WeiXinDaoImpl();
		String url="http://www.kktoon.com/htd/#/login?cartoonId="+cartoonId;
		String pathUrl=null;
		try {
			pathUrl=dao.getCodeUrl(useValue.AppId,url,"snsapi_userinfo", "state");
		} catch (Exception e) {
			
		}
		return new Model(200,pathUrl);
	}
	/**
	 * pp
	 * 
	 * @Remarks 增加漫画
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_CARTOON, method = RequestMethod.POST)
	public Model addCartoon(CartoonVo cartoonVo){
		String cartoonId = adminCartoonService.addAdminCartoon(cartoonVo);
		if(ParaClick.clickString(cartoonId)){
			return new Model(500,"添加失败");
		}
		return new Model(200,cartoonId);
	}
	/**
	 * pp
	 * 
	 * @Remarks 删除漫画 (备用)
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.DELETE_CARTOON, method = RequestMethod.POST)
	public Model deleteCartoon(CartoonVo cartoonVo){
		if(ParaClick.clickString(cartoonVo.getId())){
			return new Model(500,"未传id");
		}
		boolean bool = adminCartoonService.deleteAdminCartoon(cartoonVo.getId());
		if(bool){
			return new Model(200,"删除成功");
		}
		return new Model(500,"删除失败");
	}
	/**
	 * pp
	 * 
	 * @Remarks 修改漫画属性
	 * @throws Exception
	 * */
	@ResponseBody
	@RequestMapping(value = ActionUrl.UPDATE_CARTOON, method = RequestMethod.POST)
	public Model updateCartoon(CartoonVo cartoonVo){
		if(ParaClick.clickString(cartoonVo.getId())){
			return new Model(500,"未传id");
		}
		//判断修改排序
		if(!ParaClick.clickString(cartoonVo.getSort())){
			if(ParaClick.clickString(cartoonVo.getState())){
				return new Model(500,"状态(state)为空");
			}
			if(ParaClick.clickString(cartoonVo.getHot())){
				return new Model(500,"热度(hot)为空");
			}
			if("1".equals(cartoonVo.getState())){
				if("2".equals(cartoonVo.getSortNum())){//漫画置顶
					boolean bool = adminCartoonService.cartoonTop(cartoonVo);
					if(bool){
						return new Model(200,"置顶成功");
					}
					return new Model(500,"置顶失败");
				}
				int[] sortArr = adminCartoonService.updateSort(cartoonVo);
				if(sortArr.length==1){
					return new Model(500,"此漫画不可进行此操作");
				}else{
					boolean bool = adminCartoonService.cartoonChangeSort(sortArr);
					if(bool){
						return new Model(200,"漫画移动成功");
					}
					return new Model(500,"漫画移动失败");
				}
			}else{
				return new Model(500,"此漫画未上架,请上架后再调整");
			}
		}
		//判断漫画是否可以上架
		if(!ParaClick.clickString(cartoonVo.getState())){
			if("1".equals(cartoonVo.getState())){
				List<CartoonAllType> cartoonAllType = cartoonAllTypeService.getByCartoonId(cartoonVo.getId());
				if(!ParaClick.clickList(cartoonAllType)){
					return new Model(500,"此漫画还未有类型，不能上架");
				}
				List<CartoonSet> cartoonPhoto = adminCartoonSetService.selectCartoonSetByCartoonId(cartoonVo.getId());
				if(!ParaClick.clickList(cartoonPhoto)){
					return new Model(500,"此漫画还未有集数上架，不能直接上架，请将集数上架后再操作此功能");
				}
				cartoonVo.setUpdateDate(cartoonPhoto.get(0).getUpdateDate());
			}
		}
		boolean bool = adminCartoonService.updateAdminCartoon(cartoonVo);
		if(bool){
			return new Model(200,"修改成功");
		}
		return new Model(500,"修改失败");
	}
	/**
	 * pp
	 * 
	 * @Remarks分页查询漫画（模糊查询）
	 * @throws Exception
	 * */
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOON, method = RequestMethod.POST)
	public Model selectCartoon(HttpServletRequest req, HttpServletResponse resp,CartoonVo cartoonVo) throws Exception{
		Model model=new Model();
		int num=0;
		if(ParaClick.clickString(cartoonVo.getNowpage())){
			cartoonVo.setNowpage("1");
		}
		if(ParaClick.clickString(cartoonVo.getPageNum())){
			cartoonVo.setPageNum("10");
		}
		List<Cartoon> cartoonList = adminCartoonService.selectAllCartoon(cartoonVo);
		if(!ParaClick.clickList(cartoonList)){
			return new Model(500,"暂无数据");
		}
		num=adminCartoonService.getCount(cartoonVo);
		List<Object> obj=new ArrayList<Object>();
		for(int i=0;i<cartoonList.size();i++){
			Map<String,Object> cartoonAndType=new HashMap<String,Object>();
			//将此漫画存入用于返回
			cartoonAndType.put("cartoon",cartoonList.get(i));
			//此漫画具备的所有的类型
			List<CartoonAllType> cartoonAllType = cartoonAllTypeService.getByCartoonId(cartoonList.get(i).getId());
			List<String> cartoonTypeStr=new ArrayList<String>();
			if(ParaClick.clickList(cartoonAllType)){
				for(int j=0;j<cartoonAllType.size();j++){
					CartoonType cartoonType=cartoonTypeService.get(cartoonAllType.get(j).getCartoonTypeId());
					cartoonTypeStr.add(cartoonType.getCartoonType());
				}
				//将此漫画的所有类型存入用于返回
				cartoonAndType.put("cartoonTypeAll",cartoonTypeStr);
			}else{
				cartoonAndType.put("cartoonTypeAll", "");
			}
			//查看漫画所属所有模块
			List<CartoonAllModel> cartoonAllModel = cartoonAllModelService.getThisCartoonModel(cartoonList.get(i).getId());
			List<String> cartoonModelStr=new ArrayList<String>();
			if (ParaClick.clickList(cartoonAllModel)) {
				for (CartoonAllModel cartoonAllModel2 : cartoonAllModel) {
					CartoonModel cartoonModel = cartoonModelService.get(cartoonAllModel2.getCartoonModelId());
					cartoonModelStr.add(cartoonModel.getModel());
				}
				//将此漫画的所有模塊存入用于返回
				cartoonAndType.put("cartoonAllModel",cartoonModelStr);
			}else{
				cartoonAndType.put("cartoonAllModel", "");
			}
			obj.add(cartoonAndType);
		}
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonVo.getNowpage()));
		model.setTotalpage((num + Integer.parseInt(cartoonVo.getPageNum()) - 1) / Integer.parseInt(cartoonVo.getPageNum()));
		model.setObj(obj);
		model.setTotalNum(num);
		return model;
		
	}
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONBYID, method = RequestMethod.POST)
	public Model selectCartoonById(CartoonVo cartoonVo){
		Cartoon cartoon = adminCartoonService.selectByIdCartoon(cartoonVo.getId());
		if(ParaClick.clickObj(cartoon)){
			return new Model(200,cartoon);
		}
		return new Model(500,"查询失败");
	}
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOONADDBANNER, method = RequestMethod.POST)
	public Model selectCartoonAddBanner(){
		List<String[]> cartoonAddBanner = adminCartoonService.selectCartoonAddBanner();
		if(ParaClick.clickList(cartoonAddBanner)){
			return new Model(200,cartoonAddBanner);
		}
		return new Model(500,"查询失败");
	}
	
	/**
	 * pp
	 *  分享商部分  
	 * @Remarks分页查询漫画（模糊查询）
	 * @throws Exception
	 * */
	
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_CARTOON_BYDISTRIBUTION, method = RequestMethod.POST)
	public Model selectCartoonByDistribution(CartoonData cartoonData) throws Exception{
		Model model=new Model();
		int num=0;
		if(ParaClick.clickString(cartoonData.getNowPage())){
			cartoonData.setNowPage("1");
		}
		if(ParaClick.clickString(cartoonData.getPageNum())){
			cartoonData.setPageNum("15");
		}
		List<Cartoon> cartoonList = adminCartoonService.selectAllCartoonByDistributor(cartoonData);
		if(!ParaClick.clickList(cartoonList)){
			return new Model(500,"暂无数据");
		}
		num=adminCartoonService.getCountByDistributor(cartoonData);
		model.setError(200);
		model.setNowpage(Integer.parseInt(cartoonData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(cartoonData.getPageNum()) - 1) / Integer.parseInt(cartoonData.getPageNum()));
		model.setObj(cartoonList);
		model.setTotalNum(num);
		return model;
		
	}
}
