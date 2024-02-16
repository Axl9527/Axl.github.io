package com.easy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.Collection1;
import com.easy.bean.Zuji;
import com.easy.service.IHistoryService;
import com.easy.util.CommonResult;

@RestController
public class HistoryController {

	@Autowired
	IHistoryService ser;
	
	@GetMapping("chahistory")
	public CommonResult searchHistory( Zuji zuji) {
		System.out.println(zuji);
		List<Zuji> result=ser.list(zuji);
		System.out.println(result);
		return CommonResult.success(result);
		
	}
	
	@PostMapping("addhistory")
	public CommonResult addHistory(@RequestBody Zuji zuji) {
		System.out.println(zuji);
		int result=ser.addCollection(zuji);
		return CommonResult.success(result);
		
	}
	@DeleteMapping("delhistory")
	public CommonResult delHistory(@RequestBody Zuji zuji) {
		System.out.println(111111);
		int result=ser.delHistory(zuji);
		return CommonResult.success(result);
		
	}
	
	
	
}
