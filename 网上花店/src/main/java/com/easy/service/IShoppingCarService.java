package com.easy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.bean.ShoppingCar;

public interface IShoppingCarService {

	int addCar(ShoppingCar car);
	List<ShoppingCar> car(ShoppingCar car);
	int editCount(ShoppingCar car);
	int deleteCar(int goodsid,int userid);
	List<ShoppingCar> choose();
}
