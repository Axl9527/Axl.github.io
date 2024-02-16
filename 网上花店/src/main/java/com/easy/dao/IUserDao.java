package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.easy.bean.User;
import com.easy.bean.Zuji;
import com.easy.util.Page;
@Mapper
public interface IUserDao {
	List<User> userListL();
	List<User> userList(@Param("user") User user,@Param("page")Page page);
	int userCount(@Param("user")User user);
	User cha(@Param("user")User user);
	
	int chongfu(User user);
	int updateUser(User user);
	int addUser(User user);
	User editMiMaByName(String uname);
}
