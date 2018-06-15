package com.qin.crxl.comic.action;

import java.io.IOException;
import java.util.List;

import javassist.bytecode.LineNumberAttribute.Pc;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.crxl.comic.entity.Product;
import com.qin.crxl.comic.entity.UserEntity;
import com.qin.crxl.comic.entity.vo.ProductData;
import com.qin.crxl.comic.service.ProductService;
import com.qin.crxl.comic.service.UserService;
import com.qin.crxl.comic.system.ActionUrl;
import com.qin.crxl.comic.tool.Model;
import com.qin.crxl.comic.tool.ParaClick;

@Controller
public class ProductController {

	@Resource
	private ProductService productService;
	@Autowired
	private UserService userService;
	/**
	 * 查询商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.GET_PRODUCT_LIST, method = RequestMethod.POST)
	public Model getALL (ProductData productData) throws Exception {
		productData.clickUser();
		UserEntity userEntity = userService.getUserInfoById(productData.getUserId());
		if (userEntity == null) {
			return new Model(404, "无用户");
		}
		List<Product> list= productService.getProductList(productData.getType());
		if (!ParaClick.clickList(list)) {
			return new Model(500,"暂无商品");
		}
		return new Model(200,list);
	}
	
	/**
	 * 增加商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_PRODUCT_LIST, method = RequestMethod.POST)
	public Model addProduct(ProductData productData) throws Exception {
		boolean flag= productService.addProduct(productData);
		if (!flag) {
			return new Model(500,"添加失败");
		}
		return new Model(200,"增加成功");
	}
	
	/**
	 * 修改商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = ActionUrl.ADD_PRODUCT_LIST, method = RequestMethod.POST)
	public Model updateProduct(ProductData productData) throws Exception {
		boolean flag= productService.updateProduct(productData);
		if (!flag) {
			return new Model(500,"添加失败");
		}
		return new Model(200,"增加成功");
	}
	
}
