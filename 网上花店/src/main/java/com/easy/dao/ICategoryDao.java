package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.easy.bean.Category;
import com.easy.util.Page;

@Mapper
public interface ICategoryDao {
 List<Category> categoryList(@Param("category") Category category,@Param("page")Page page);
 int categoryCount(@Param("category")Category category);
 
 int categoryEidt(@Param("category")Category category);
 
 int categoryDel(@Param("id") int id);
 int categoryAdd(@Param("category")Category category);
	


}
