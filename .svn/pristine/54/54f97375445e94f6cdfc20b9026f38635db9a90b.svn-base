package com.qin.crxl.comic.service.impl;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.UserOrder;
import com.qin.crxl.comic.entity.vo.AdminFansData;
import com.qin.crxl.comic.entity.vo.UserOrderData;
import com.qin.crxl.comic.exception.BusinessException;
import com.qin.crxl.comic.service.AdminUserOrderService;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Service
public class AdminUserOrdeServiceImpl extends BaseServiceImpl<UserOrder>
		implements AdminUserOrderService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> selectUserOrder(UserOrderData userOrderData) {
		// 多表模糊查询
		StringBuffer sb = new StringBuffer();

		sb.append("select o.id,o.orderNum,o.orderUserId,o.orderMoney,o.orderRemarks,o.orderIntegral,o.orderDescription,o.orderDate,o.orderUserName from UserOrder o where o.orderState=1");
		if (!ParaClick.clickString(userOrderData.getCondition())) {
			sb.append(" and ((o.orderNum like '%"
					+ userOrderData.getCondition()
					+ "%') or (o.orderDescription like '%"
					+ userOrderData.getCondition() + "%')"
					+ "or (o.orderUserId like '%"
					+ userOrderData.getCondition() + "%'))");
		}
		if (!ParaClick.clickString(userOrderData.getStarTime())
				&& !ParaClick.clickString(userOrderData.getEndTime())) {
			sb.append(" and o.orderDate BETWEEN '"
					+ userOrderData.getStarTime() + " 00:00:00' and '"
					+ userOrderData.getEndTime() + " 23:59:59'");
		}

		sb.append(" order by o.orderDate DESC limit "
				+ (Integer.parseInt(userOrderData.getNowPage()) - 1)
				* Integer.parseInt(userOrderData.getPageNum()) + ","
				+ Integer.parseInt(userOrderData.getPageNum()));
		System.out.println(sb.toString());
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCountUserOrder(UserOrderData userOrderData) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from UserOrder o,UserEntity u where o.orderUserId=u.userId and o.orderState=1");
		if (!ParaClick.clickString(userOrderData.getCondition())) {
			sb.append(" and ((o.orderNum like '%"
					+ userOrderData.getCondition()
					+ "%') or (o.orderDescription like '%"
					+ userOrderData.getCondition() + "%'))");
		}
		if (!ParaClick.clickString(userOrderData.getStarTime())
				&& !ParaClick.clickString(userOrderData.getEndTime())) {
			sb.append(" and o.orderDate BETWEEN '"
					+ userOrderData.getStarTime() + " 00:00:00' and '"
					+ userOrderData.getEndTime() + " 23:59:59'");
		}
		try {
			int gettotalpage = super.gettotalpage(sb.toString());
			return gettotalpage;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public UserOrder selectUserOrderById(UserOrderData userOrderData) {
		UserOrder userOrder = null;
		try {
			userOrder = super.get(userOrderData.getId());
			return userOrder;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public double moneyWater(UserOrderData userOrderData) {
		// 多表模糊查询
		StringBuffer sb = new StringBuffer();

		sb.append("select SUM(orderMoney) from UserOrder o,UserEntity u where o.orderUserId=u.userId and o.orderState=1");
		if (!ParaClick.clickString(userOrderData.getCondition())) {
			sb.append(" and ((o.orderNum like '%"
					+ userOrderData.getCondition()
					+ "%') or (o.orderDescription like '%"
					+ userOrderData.getCondition() + "%'))");
		}
		if (!ParaClick.clickString(userOrderData.getStarTime())
				&& !ParaClick.clickString(userOrderData.getEndTime())) {
			sb.append(" and o.orderDate BETWEEN '"
					+ userOrderData.getStarTime() + " 00:00:00' and '"
					+ userOrderData.getEndTime() + " 23:59:59'");
		}
		System.out.println(sb.toString());
		try {
			Double sum = super.getSUM(sb.toString());
			return sum;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return 0.0;
	}

	@Override
	public double moneyUserEntity(String userId) {
		return super
				.getSUM("select SUM(orderMoney) from UserOrder where orderUserId='"
						+ userId + "'");
	}

	@Override
	public List<Object[]> selectCartoonOrderProfit(String starTime,
			String endTime) {
		// 各公众号收益
		StringBuffer sb = new StringBuffer();
		sb.append("select u.PlatformIndex,sum(o.orderMoney) as a from UserEntity u,UserOrder o  where u.userId=o.orderUserId and o.orderState=1  ");
		if (!ParaClick.clickString(starTime) && !ParaClick.clickString(endTime)) {
			sb.append("  and  o.orderDate>='" + starTime + " 00:00:00"
					+ "'  and  o.orderDate<='" + endTime + " 23:59:59'");
		}
		sb.append(" GROUP BY u.PlatformIndex");
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public List<Object[]> selectCartoonOrderProfitNew(String starTime,
			String endTime) {
		// 各公众号收益
		StringBuffer sb = new StringBuffer();
		sb.append("select o.orderIntegral,sum(o.orderMoney) as a from UserEntity u,UserOrder o  where u.userId=o.orderUserId and o.orderState=1  ");
		if (!ParaClick.clickString(starTime) && !ParaClick.clickString(endTime)) {
			sb.append("  AND  o.orderDate>='" + starTime + " 00:00:00"
					+ "'  AND  o.orderDate<='" + endTime + " 23:59:59' ");
		}
		sb.append(" AND o.orderDate>='2018-02-24 14:08:30' GROUP BY o.orderIntegral");
		List<Object[]> list = null;
		try {
			list = getSessionFactory().createSQLQuery(sb.toString()).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

	@Override
	public void export(String[] titles, ServletOutputStream out, AdminFansData adminFansData) {
		// 生成Excel
		try {
			// 第一步，创建一个workbook，对应一个Excel文件
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFCell hssfCell = null;

			for (int i = 0; i < titles.length; i++) {
				hssfCell = hssfRow.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(titles[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}
			List<Object[]> profit = selectCartoonOrderProfitNew(adminFansData.getStarTime(),
							adminFansData.getEndTime());
			if (!ParaClick.clickList(profit)) {
				throw new BusinessException("导出信息失败！");
			}
			for (int i = 0; i < profit.size(); i++) {
				 hssfRow = hssfSheet.createRow(i+1);    
				 hssfRow.createCell(0).setCellValue(profit.get(i)[0]+"");
				 hssfRow.createCell(1).setCellValue(profit.get(i)[1]+"");
			}
			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("导出信息失败！");
		}

	}

}
