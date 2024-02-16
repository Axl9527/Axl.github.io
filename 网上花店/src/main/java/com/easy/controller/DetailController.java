package com.easy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.Category;
import com.easy.bean.Detail;
import com.easy.service.IDetailsService;
import com.easy.util.CommonResult;

@RestController
@CrossOrigin(origins="*")
public class DetailController {

	@Autowired
	IDetailsService ser;
	
	
	@GetMapping("detail")
	public CommonResult detailList(Detail detail) {
		System.out.println(11111);
		List<Detail> list=ser.list(detail);
		System.out.println(list);
		return CommonResult.success(list);
	}
}
