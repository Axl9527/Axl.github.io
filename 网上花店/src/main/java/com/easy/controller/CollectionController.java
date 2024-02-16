package com.easy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.Collection1;
import com.easy.service.ICollectionService;
import com.easy.util.CommonResult;

@RestController
public class CollectionController {
	@Autowired
	ICollectionService ser;
	
	@PostMapping("addc")
	public CommonResult addColletion(@RequestBody Collection1 collection) {
		System.out.println(collection);
		int result =ser.addCollection(collection);
		return CommonResult.success(result);
		
	}
	@GetMapping("chac")
	public CommonResult searchColletion( Collection1 collection) {
		System.out.println(collection);
		List<Collection1> result=ser.list(collection);
		
		return CommonResult.success(result);
		
	}
	
	
}
