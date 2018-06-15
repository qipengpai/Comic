package com.qin.crxl.comic.action;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.UserEntityData;
import com.qin.crxl.comic.service.AdminUserEntityService;
import com.qin.crxl.comic.service.AdminUserOrderService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;
import com.qin.crxl.comic.tool.StringToInt;

@Controller
public class AdminUserEntityController {
	@Autowired
	private AdminUserEntityService adminUserEntityService;
	@Autowired
	private AdminUserOrderService adminUserOrderService;
	
	//查询用户
	@ResponseBody
	@RequestMapping(value = ActionUrl.SELECT_USERENTITY, method = RequestMethod.POST)
	public Model selectUserEntity(UserEntityData userEntityData){
		Model model=new Model();
		int num=0;
		if(ParaClick.clickString(userEntityData.getNowPage())){
			userEntityData.setNowPage("1");
		}
		if(ParaClick.clickString(userEntityData.getPageNum())){
			userEntityData.setPageNum("10");
		}
		List<UserEntity> userList = adminUserEntityService.selectUser(userEntityData);
		num=adminUserEntityService.getCount(userEntityData);
		if(!ParaClick.clickList(userList)){
			return new Model(500,"无用户数据");
		}else{
			DecimalFormat    df   = new DecimalFormat("######0.00");
			for(int i=0;i<userList.size();i++){
				userList.get(i).setCountry(StringToInt.toString(userList.get(i).getCountry()));
				userList.get(i).setCity(StringToInt.toString(userList.get(i).getCity()));
				double moneyUserEntity = adminUserOrderService.moneyUserEntity(userList.get(i).getOpenid());
				userList.get(i).setObj(df.format(moneyUserEntity));
				if(ParaClick.clickObj(userList.get(i).getStartDate())&&ParaClick.clickObj(userList.get(i).getEndDate())){
					userList.get(i).setStartTime(userList.get(i).getStartDate());
					userList.get(i).setOverTime(userList.get(i).getEndDate());
				}
				try {
					userList.get(i).setNickname(StringToInt.toString(userList.get(i).getNickname()));
					userList.get(i).setUsername(StringToInt.toString(userList.get(i).getUsername()));
				} catch (Exception e) {
					userList.get(i).setNickname("潮人用户");
				}
			}
		}
		
		model.setError(200);
		model.setNowpage(Integer.parseInt(userEntityData.getNowPage()));
		model.setTotalpage((num + Integer.parseInt(userEntityData.getPageNum()) - 1) / Integer.parseInt(userEntityData.getPageNum()));
		model.setObj(userList);
		model.setTotalNum(num);
		return model;
	}
	
	
	
}