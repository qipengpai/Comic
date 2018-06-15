package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.Product;
import com.qin.crxl.comic.entity.vo.ProductData;
import com.qin.crxl.comic.service.ProductService;
import com.qin.crxl.comic.tool.DateUtil;
import com.qin.crxl.comic.tool.ParaClick;
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

	@Override
	public List<Product> getProductList(String type) {
		// 查询商品
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM Product WHERE 1=1 AND state =1 ");
		if (Integer.parseInt(type)==101) {
			sb.append(" AND type=101");
		}else {
			sb.append(" AND type=102");
		}
		sb.append(" ORDER BY SORT");
		List<Product> list=SQL(sb.toString(), Product.class);
		return list;
	}

	@Override
	public boolean addProduct(ProductData productData) {
		// 增加商品
		boolean flag=false;
		try {
			Product product=new Product();
			if (!ParaClick.clickString(productData.getHot())) {
				product.setHot(Integer.parseInt(productData.getHot()));
			}
			if (!ParaClick.clickString(productData.getIntroduc())) {
				product.setIntroduc(productData.getIntroduc());
			}
			if (!ParaClick.clickString(productData.getIntroduction())) {
				product.setIntroduction(productData.getIntroduction());
			}
			if (!ParaClick.clickString(productData.getPhoto())) {
				product.setPhoto(productData.getPhoto());
			}
			if (!ParaClick.clickString(productData.getPrice())) {
				product.setPrice(Double.parseDouble(productData.getPrice()));
			}
			if (!ParaClick.clickString(productData.getProductId())) {
				product.setProductId(productData.getProductId());
			}
			if (!ParaClick.clickString(productData.getSort())) {
				product.setSort(Integer.parseInt(productData.getSort()));
			}
			if (!ParaClick.clickString(productData.getState())) {
				product.setState(Integer.parseInt(productData.getState()));
			}
			if (!ParaClick.clickString(productData.getType())) {
				product.setType(productData.getType());
			}
			product.setImplDate(DateUtil.getdate_yyyy_MM_dd_HH_MM_SS());
			save(product);
			flag=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	@Override
	public boolean updateProduct(ProductData productData) {
		// 修改商品
		boolean flag=false;
		try {
			Product product=get(productData.getId());
			if (!ParaClick.clickString(productData.getHot())) {
				product.setHot(Integer.parseInt(productData.getHot()));
			}
			if (!ParaClick.clickString(productData.getState())) {
				product.setState(Integer.parseInt(productData.getState()));
			}
			if (!ParaClick.clickString(productData.getType())) {
				product.setHot(Integer.parseInt(productData.getHot()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
