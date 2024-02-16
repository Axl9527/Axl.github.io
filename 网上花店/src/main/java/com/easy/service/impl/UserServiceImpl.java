package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.User;
import com.easy.dao.IUserDao;
import com.easy.service.IUserService;
import com.easy.util.MD5Util;
import com.easy.util.Page;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao dao;

	public List<User> userList(User user, Page page) {
		List<User> list = dao.userList(user, page);

		return list;
	}

	public int userCount(User user) {
		int count = dao.userCount(user);

		return count;
	}

	public List<User> userListL() {
		List<User> list = dao.userListL();

		return list;
	}

	public User cha(User user) {
		// TODO Auto-generated method stub
		return dao.cha(user);
	}
	
	public int addUser(User user) {
	
	int count=	dao.chongfu(user);
	if(count>0) {
		count=3;
	}else {
		System.out.println(user.getUpassword()+"测试加密密码");
		String result=MD5Util.enCode(user.getUpassword());
		System.out.println(result+"加密密码");
		user.setUpassword(result);
		System.out.println(user+"加密密码");
		dao.addUser(user);
		
		
	}
	
		return count;
	}
	
	
	public int updateUser(User user) {
	//无新密码即进入修改基本信息
		if(user.getNewPassWord()==null) {
			System.out.println(user+"aaa");
			return dao.updateUser(user);
		}//修改密码，判断密码是否正确，不正确 返回3
		else {
			System.out.println(user+"bbb");
			String uname=user.getUname();
			User userP=  dao.editMiMaByName(uname);
			String decode=MD5Util.deCode(user.getUpassword(),userP.getUpassword());
			if(userP.getUpassword().equals(decode)) {
			String result=MD5Util.enCode(user.getNewPassWord());
			user.setUpassword(result);
			return dao.updateUser(user);
		}else {
			System.out.println(user+"ccc");
			return 3;
		}
		}
	}

}
