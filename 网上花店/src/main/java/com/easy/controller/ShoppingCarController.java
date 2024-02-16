package com.easy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.bean.ShoppingCar;
import com.easy.service.IShoppingCarService;
import com.easy.util.CommonResult;

@RestController
@CrossOrigin(origins="*")
public class ShoppingCarController {

	@Autowired
	IShoppingCarService ser;
	@PostMapping("addCar")
	public CommonResult addCar(@RequestBody ShoppingCar car) {
		System.out.println(car);
		int result=ser.addCar(car);
		return CommonResult.success(result);
	}
	@GetMapping("car")
	public CommonResult Car(ShoppingCar car) {
	List<ShoppingCar> list=ser.car(car);
	System.out.println(list);
	return CommonResult.success(list);
	}
	@GetMapping("choosegood")
	public CommonResult choose() {
	List<ShoppingCar> list=ser.choose();
	System.out.println(list);
	return CommonResult.success(list);
	}
	@PutMapping("editcount")
	public CommonResult editCount(@RequestBody ShoppingCar car) {
		
		int count =ser.editCount(car);
		System.out.println(1111112222);
		return CommonResult.success(count);
	}
	
	@PostMapping("deletecar")
	public CommonResult deleteCar(@RequestBody ShoppingCar car) {
		int goodid=(int) car.getGoodsid();
		int userd=(int)car.getUserid();
		
		int count=ser.deleteCar(goodid,userd);
		return CommonResult.success(count);
	}
	@PostMapping("ordercar")
	public void  Car() {

	System.out.println("¼Ó¶©µ¥");
	//return CommonResult.success(list);
	}
	
}
