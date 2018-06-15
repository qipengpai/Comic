package com.qin.crxl.comic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qin.crxl.comic.base.BaseService;
import com.qin.crxl.comic.entity.TransactionIdentifier;

@Service
@Transactional
public interface TransactionIdentifierService extends BaseService<TransactionIdentifier>{

	TransactionIdentifier getByTransactionIdentifier(
			String transactionIdentifier);

	boolean addTransactionIdentifier(String transactionIdentifier, String string);

	
}
