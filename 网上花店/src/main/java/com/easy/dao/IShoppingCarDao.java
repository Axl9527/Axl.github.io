package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.easy.bean.ShoppingCar;
@Mapper
public interface IShoppingCarDao {
	List<ShoppingCar> car(@Param("car")ShoppingCar car);
	int addCar(@Param("car")ShoppingCar car);
	int carCoun(@Param("car")ShoppingCar car);
	int carEditCount(@Param("car")ShoppingCar car);
	int editCount(@Param("car")ShoppingCar car);
	int deleteCar(@Param("goodsid")int goodsid,@Param("userid")int userid);
	List<ShoppingCar> choose();
}
