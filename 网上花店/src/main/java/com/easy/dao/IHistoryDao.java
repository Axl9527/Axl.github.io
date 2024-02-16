package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.easy.bean.Collection1;
import com.easy.bean.Zuji;

@Mapper
public interface IHistoryDao {
	List<Zuji> list(Zuji zuji);
	int addCollection(Zuji zuji);
	int delHistory(Zuji zuji);
	int chongfu(Zuji zuji);
	int editHistory(Zuji ji);
}
