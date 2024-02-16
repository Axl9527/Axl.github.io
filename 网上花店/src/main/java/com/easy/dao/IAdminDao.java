package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.easy.bean.Admin;
import com.easy.bean.User;
import com.easy.util.Page;
@Mapper
public interface IAdminDao {

	List<Admin> adminList(@Param("Admin") User user,@Param("page")Page page);
	int userCount(@Param("Admin")User user);
}
