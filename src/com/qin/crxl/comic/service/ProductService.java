package com.qin.crxl.comic.service;

import java.util.List;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.Product;
import com.qin.crxl.comic.entity.vo.ProductData;

@Service
@Transactional
public interface ProductService extends BaseService<Product>{

	List<Product> getProductList(String type);

	boolean addProduct(ProductData productData);

	boolean updateProduct(ProductData productData);

}