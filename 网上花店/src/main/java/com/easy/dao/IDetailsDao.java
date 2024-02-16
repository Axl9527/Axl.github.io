package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.easy.bean.Detail;

@Mapper
public interface IDetailsDao {

	int add(Detail detail);
	List<Detail> list(Detail detail);
	int delDetail(Detail detail);
}
