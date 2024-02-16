package com.easy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.Category;
import com.easy.bean.Goods;
import com.easy.bean.ShoppingCar;
import com.easy.service.IGoodsService;
import com.easy.util.CommonResult;
import com.easy.util.Page;

@RestController
@CrossOrigin(origins="*")
public class GoodsController {
	@Autowired
	IGoodsService goodsSer;
	
	
	@GetMapping("goods")
	public CommonResult GoodsList(Goods goods,Page page) {
		List<Goods> list=goodsSer.list(goods, page);
		int count =goodsSer.count(goods);
		Map map=new HashMap();
		map.put("list",list);
		map.put("count",count);
		CommonResult result=CommonResult.success(map);
		return result;
		}
		
		
		@GetMapping("goodsHou")
		public CommonResult GoodsListHou(Goods goods,Page page) {
			List<Goods> list=goodsSer.listHou(goods, page);
			int count =goodsSer.countHou(goods);
			Map map=new HashMap();
			map.put("list",list);
			map.put("count",count);
			CommonResult result=CommonResult.success(map);
			return result;
		
		
	}
	@GetMapping("good")
	public CommonResult goodList() {
		List<Goods> list=goodsSer.goodList();
		
		
		CommonResult result=CommonResult.success(list);
		return result;
		
	}
	
	@GetMapping("goodsparent")
	public CommonResult parentList() {
		System.out.println(11111);
		List<Category> list=goodsSer.parentList();
		System.out.println(list);
		return CommonResult.success(list);
	}
	
	
	@DeleteMapping("delete/{id}")
	public CommonResult deleteGoods(@PathVariable int id) {
		System.out.println(11111);
		int count =goodsSer.delete(id);
		return CommonResult.success(count);
	}
	@PostMapping("goodseidt")
	public CommonResult editGoods(@RequestBody Goods goods) {

		int count =goodsSer.goodsEdit(goods);
		return CommonResult.success(count);
		
	}
	@PostMapping("goods")
	public CommonResult addGoods(@RequestBody Goods goods) {
		System.out.println(goods);
		goodsSer.goodsAdd(goods);
		return CommonResult.success(goods);
	}
	@PutMapping("goods")
	public CommonResult shangXiaJia(@RequestBody Goods goods) {
		return CommonResult.success(goodsSer.shangXiaJia(goods));
	}


}
