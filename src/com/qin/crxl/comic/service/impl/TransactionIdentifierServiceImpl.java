package com.qin.crxl.comic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qin.crxl.comic.base.BaseServiceImpl;
import com.qin.crxl.comic.entity.TransactionIdentifier;
import com.qin.crxl.comic.service.TransactionIdentifierService;

@Service
public class TransactionIdentifierServiceImpl extends BaseServiceImpl<TransactionIdentifier>
implements TransactionIdentifierService{

	@Override
	public TransactionIdentifier getByTransactionIdentifier(
			String transactionIdentifier) {
		// 验证苹果订单号不重复
		List<TransactionIdentifier> list=SQL("SELECT * FROM TransactionIdentifier WHERE identifier='"+transactionIdentifier+"'", TransactionIdentifier.class);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean addTransactionIdentifier(String transactionIdentifier,
			String string) {
		// 增加记录
		boolean flag= false;
		try {
			TransactionIdentifier identifier=new TransactionIdentifier();
			identifier.setOrderNum(string);
			identifier.setIdentifier(transactionIdentifier);
			save(identifier);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	

}
