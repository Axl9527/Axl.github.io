package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.easy.bean.Collection1;

@Mapper
public interface ICollectionDao {
	List<Collection1> list(Collection1 collection);
	int addCollection(Collection1 collection);
	int delCollection(Collection1 collection);
	int chongfu(Collection1 collection);
}
