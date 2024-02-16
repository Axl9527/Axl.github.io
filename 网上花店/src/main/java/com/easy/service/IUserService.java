package com.easy.service;

import java.util.List;

import com.easy.bean.User;
import com.easy.util.Page;

public interface IUserService {
	List<User> userListL ();
	List<User> userList (User user,Page page);
	int userCount(User user);
	User cha(User user);
	

	int updateUser(User user);
	int addUser(User user);

	
}
