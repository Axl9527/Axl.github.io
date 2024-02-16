package com.easy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.Category;
import com.easy.service.ICategoryService;
import com.easy.util.CommonResult;
import com.easy.util.Page;

@RestController
@CrossOrigin(origins="*")
public class CategoryController {
	@Autowired
	ICategoryService cateServ;
	@GetMapping("category")
	public CommonResult categoryList(Category category,Page page) {
		List<Category> list = cateServ.categoryList(category,page);
		System.out.println(list.toString());
		int count =cateServ.categoryCount(category);
		Map map=new HashMap();
		map.put("list", list);
		map.put("count", count);
		CommonResult result = CommonResult.success(map);

		return result;
		
	}
	@PostMapping("categoryeidt")
	public CommonResult categoryEidt(@RequestBody Category category) {
		System.out.println(111111);
		cateServ.categoryEidt(category);
		CommonResult result = CommonResult.success(category);

		return result;
	}
	@DeleteMapping("category/{id}")
	public CommonResult categoryDel(@PathVariable int id) {
		// 执行删除
		int count = cateServ.categoryDel(id);
		// 封装返回结果
		CommonResult result = CommonResult.success(count);
		return result;

	}
	@PostMapping("category") // 接收json数据
	public CommonResult categoryAdd(@RequestBody Category category) {
		System.out.println("zzzzzzzzzzzzzzz");
		cateServ.categoryAdd(category);
		// 里面有自增长的id
		CommonResult result = CommonResult.success(category);
		return result;
	}

}
