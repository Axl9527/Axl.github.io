package com.easy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.User;
import com.easy.dao.IUserDao;
import com.easy.service.IUserService;
import com.easy.util.CommonResult;
import com.easy.util.Page;

@RestController
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	IUserService usera;
	
	@GetMapping("user")
	public CommonResult userList(User user, Page page) {
		System.out.println("我是用户");
		List<User> list=usera.userList(user, page);
		for(User users:list) {
			users.setUpassword("*********");
			String phone=users.getUphone();
			
			String left=phone.substring(0,3);
			
			String right=phone.substring(7);
			
			String phones=left+"****"+right;
			users.setUphone(phones);
			String e=users.getEmail();
			String eleft= e.substring(0,3);
			int lastIndex=e.indexOf("@");
			String eright=e.substring(lastIndex);
			String emails=eleft+"****"+eright;
			users.setEmail(emails);
		}
		int count=usera.userCount(user);
		Map map=new HashMap();
		map.put("list", list);
		map.put("count", count);
		return CommonResult.success(map);
		
	}
	@GetMapping("cha")
	public CommonResult cha(User user) {
		 User u=  usera.cha(user);
		 return CommonResult.success(u);
	}
	
	@PostMapping("addUser")
	public CommonResult addUser(@RequestBody User user) {
		int data=usera.addUser(user);
		return CommonResult.success(data);
	}
	@PutMapping("editUser")
	public CommonResult editUser(@RequestBody User user) {
		System.out.println(user);
		int data=usera.updateUser(user);
		return CommonResult.success(data);
	}
	
}
