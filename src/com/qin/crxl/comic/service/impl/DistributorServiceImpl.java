package com.qin.crxl.comic.service.impl;

import java.util.List;

import javassist.bytecode.LineNumberAttribute.Pc;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Distributor;
import com.qin.crxl.comic.entity.vo.DistributorData;
import com.qin.crxl.comic.service.DistributorService;
import com.qin.crxl.comic.tool.ArithUtil;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.MD5;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class DistributorServiceImpl extends BaseServiceImpl<Distributor> implements DistributorService{

	@Override
	public List<Distributor> getByQd(String qd) {
		// 查看渠道是否存在
		List<Distributor> list=SQL("SELECT * FROM Distributor WHERE qd='"+qd+"'", Distributor.class);
		return list;
	}

	@Override
	public List<Distributor> getByUserName(String userName) {
		// 查看用户名是否存在
		List<Distributor> list=SQL("SELECT * FROM Distributor WHERE userName='"+userName+"'", Distributor.class);
		return list;
	}

	@Override
	public boolean registerDistributor(DistributorData distributorData) {
		// 注册用户
		boolean flag=false;
		try {
			Distributor distributor=new Distributor();
			if (!ParaClick.clickString(distributorData.getAccountName())) {
				distributor.setAccountName(distributorData.getAccountName());
			}
			if (!ParaClick.clickString(distributorData.getAccountNum())) {
				distributor.setAccountNum(distributorData.getAccountNum());
			}
			if (!ParaClick.clickString(distributorData.getEmail())) {
				distributor.setEmail(distributorData.getEmail());
			}
			if (!ParaClick.clickString(distributorData.getHeadImg())) {
				distributor.setHeadImg(distributorData.getHeadImg());
			}
			if (!ParaClick.clickString(distributorData.getNickName())) {
				distributor.setNickName(distributorData.getNickName());
			}
			if (!ParaClick.clickString(distributorData.getPayType())) {
				distributor.setPayType(distributorData.getPayType());
			}
			if (!ParaClick.clickString(distributorData.getUserName())) {
				distributor.setUserName(distributorData.getUserName());
			}
			if (!ParaClick.clickString(distributorData.getProportion())) {
				distributor.setProportion(Double.parseDouble(distributorData.getProportion()));
			}
			if (!ParaClick.clickString(distributorData.getPhone())) {
				distributor.setPhone(distributorData.getPhone());
			}
			if (!ParaClick.clickString(distributorData.getQd())) {
				distributor.setQd(distributorData.getQd().toUpperCase());
			}
			if (!ParaClick.clickString(distributorData.getUserPwd())) {
				distributor.setUserPwd(MD5.getMd5(distributorData.getUserPwd()));
			}
			distributor.setUserType("1");
			distributor.setWithdrawalsType(1);
			distributor.setState(1);
			distributor.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			distributor.setLastLoginDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			save(distributor);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateDistributorInfo(DistributorData distributorData) {
		// 修改信息
		boolean flag=false;
		try {
			Distributor distributor=this.get(distributorData.getId());
			if (!ParaClick.clickString(distributorData.getAccountName())) {
				distributor.setAccountName(distributorData.getAccountName());
			}
			if (!ParaClick.clickString(distributorData.getAccountNum())) {
				distributor.setAccountNum(distributorData.getAccountNum());
			}
			if (!ParaClick.clickString(distributorData.getEmail())) {
				distributor.setEmail(distributorData.getEmail());
			}
			if (!ParaClick.clickString(distributorData.getHeadImg())) {
				distributor.setHeadImg(distributorData.getHeadImg());
			}
			if (!ParaClick.clickString(distributorData.getNickName())) {
				distributor.setNickName(distributorData.getNickName());
			}
			if (!ParaClick.clickString(distributorData.getPayType())) {
				distributor.setPayType(distributorData.getPayType());
			}
			if (!ParaClick.clickString(distributorData.getUserName())) {
				distributor.setUserName(distributorData.getUserName());
			}
			if (!ParaClick.clickString(distributorData.getProportion())) {
				distributor.setProportion(Double.parseDouble(distributorData.getProportion()));
			}
			if (!ParaClick.clickString(distributorData.getPhone())) {
				distributor.setPhone(distributorData.getPhone());
			}
			if (!ParaClick.clickString(distributorData.getQd())) {
				distributor.setQd(distributorData.getQd().toUpperCase());
			}
			if (!ParaClick.clickString(distributorData.getUserPwd())) {
				distributor.setUserPwd(MD5.getMd5(distributorData.getUserPwd()));
			}
			if (!ParaClick.clickString(distributorData.getWithdrawalsType())) {
				distributor.setWithdrawalsType(Integer.parseInt(distributorData.getWithdrawalsType()));
			}
			if (!ParaClick.clickString(distributorData.getUserType())) {
				distributor.setUserType(distributorData.getUserType());
			}
			if (!ParaClick.clickString(distributorData.getState())) {
				distributor.setState(Integer.parseInt(distributorData.getState()));
			}
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public int getDistributorNum(String condition) {
		// 查询供销商数量
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT COUNT(*) FROM Distributor WHERE 1=1");
		if (!ParaClick.clickString(condition)) {
			sb.append(" AND (( nickName LIKE '%"+condition+"%' ) OR ( phone LIKE '%"+condition+"%' ) OR ( AccountNum LIKE '%"+condition+"%' )OR ( AccountName LIKE '%"+condition+"%' ))");
		}
		int num =super.gettotalpage(sb.toString());
		return num;
	}

	@Override
	public List<Distributor> getDistributor(String nowPage, String condition,String pagenum) {
		// 查询供销商
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM Distributor WHERE 1=1");
		if (!ParaClick.clickString(condition)) {
			sb.append(" AND ((nickName LIKE '%"+condition+"%')OR(phone LIKE '%"+condition+"%'))");
		}
		sb.append(" ORDER BY implDate DESC LIMIT "+(Integer.parseInt(nowPage)-1)*Integer.parseInt(pagenum)+","+Integer.parseInt(pagenum)+"");
		return SQL(sb.toString(), Distributor.class);
	}

	@Override
	public boolean updateReCharge(double sum,String id) {
		// 增加全部收益
		boolean flag=false;
		try {
			Distributor distributor=get(id);
			if (distributor==null) {
				return flag;
			}
			distributor.setAllRecharge(ArithUtil.add(distributor.getAllRecharge(), sum));
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateOverReCharge(double withdrawalsMoney, String id) {
		// 增加已结算收益
		boolean flag=false;
		try {
			Distributor distributor=get(id);
			if (distributor==null) {
				return flag;
			}
			distributor.setOverRecharge(ArithUtil.add(distributor.getOverRecharge(),withdrawalsMoney));
			distributor.setBalance(ArithUtil.sub(distributor.getBalance(), withdrawalsMoney));
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}


	
	
}
