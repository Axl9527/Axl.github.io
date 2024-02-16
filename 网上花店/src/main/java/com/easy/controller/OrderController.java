package com.easy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.easy.bean.Address;
import com.easy.bean.Order;
import com.easy.bean.Order2;
import com.easy.bean.OrderAll;
import com.easy.bean.User;
import com.easy.service.IOrderService;
import com.easy.service.IUserService;
import com.easy.util.CommonResult;
import com.easy.util.Page;

@RestController
@CrossOrigin(origins="*")
public class OrderController {

	@Autowired
	IOrderService orderSer;
	
	
	@GetMapping("order")
	public CommonResult orderList(Order2 order ,Page page) {
		List<Order2> list=orderSer.orderList(order, page);
		
		int count =orderSer.orderCount(order);
		Map map =new HashMap();
		map.put("list", list);
		map.put("count",count);
		return CommonResult.success(map);
	
		
	}
	@GetMapping("userSeach")
	public CommonResult parentList() {
		List<User> list=orderSer.parentList();
		return CommonResult.success(list);
	}
	@GetMapping("useradress")
	public CommonResult userA(Address address) {
		System.out.println(address);
		List<Address> list=orderSer.address(address);
		System.out.println(list);
		return CommonResult.success(list);
	}
	@PostMapping("address")
	public CommonResult add(@RequestBody Address address) {
		int result =orderSer.add(address);
		return CommonResult.success(address);
	}
	@PostMapping("addorder")
	public CommonResult addOrder(@RequestBody OrderAll orderAll) {
		
	
		
		
		int result =orderSer.addOrder(orderAll);
		return CommonResult.success(orderAll);
	}
	@GetMapping("orders")
	public CommonResult test(OrderAll order) {
		
		List<OrderAll> list=orderSer.checkList(order);
		return CommonResult.success(list);
	}
	@PutMapping("editorder")
	public CommonResult editOrder(@RequestBody OrderAll order) {
		System.out.println("**************");
		System.out.println(order);
		System.out.println("**************");
		int result = orderSer.editOrder(order);
		return CommonResult.success(result);
		
	}
	@PostMapping("fahuo")
	public CommonResult faHuo(@RequestBody Order2 order) {
		int result = orderSer.faHuo(order);
		return CommonResult.success(result);
		
	}
	@PostMapping("tui")
	public CommonResult tui(@RequestBody List<Order2> order) {
		int result = orderSer.delOrder(order);
		return CommonResult.success(result);
		
	}
	  @PutMapping("editaddress")
	    public CommonResult editAddress(@RequestBody Address address){
	        System.out.println("ÐÞ¸ÄµØÖ·"+address);
	        return CommonResult.success(orderSer.editAddress(address));
	    }
	  
	  @DeleteMapping("delAddress/{id}")
	  public CommonResult delAddress(@PathVariable long id) {
		  
		  return CommonResult.success(orderSer.delAddress(id));
	  }

}