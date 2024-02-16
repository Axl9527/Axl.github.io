package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.Collection1;
import com.easy.dao.ICollectionDao;
import com.easy.service.ICollectionService;

@Service
public class CollectionServiceImpl implements ICollectionService {
	@Autowired
	ICollectionDao dao;
	public List<Collection1> list(Collection1 collection) {
		
		return dao.list(collection);
	}

	public int addCollection(Collection1 collection) {

		int count=dao.chongfu(collection);
		
		if(count>0) {
			count=3;
		}else {
			dao.addCollection(collection);
		}
		
		return count;
	}

	public int delCollection(Collection1 collection) {
		// TODO Auto-generated method stub
		return 0;
	}

}
