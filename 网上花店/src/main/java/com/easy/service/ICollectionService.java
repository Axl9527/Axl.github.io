package com.easy.service;

import java.util.List;

import com.easy.bean.Collection1;

public interface ICollectionService {
	List<Collection1> list(Collection1 collection);
	int addCollection(Collection1 collection);
	int delCollection(Collection1 collection);
	
}
