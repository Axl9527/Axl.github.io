package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.easy.bean.Category;
import com.easy.bean.Goods;
import com.easy.bean.ShoppingCar;
import com.easy.util.Page;

@Mapper
public interface IGoodsDao {

	
	List<Goods> list(@Param("goods") Goods goods,@Param("page") Page page);
	List<Goods> listHou(@Param("goods") Goods goods,@Param("page") Page page);
	int countHou(@Param("goods") Goods goods);
	int count(@Param("goods") Goods goods);
	int delete(@Param("id") int id);
	int goodsEdit(@Param("goods") Goods goods);
	int goodsAdd(@Param("goods") Goods goods);
	List<Category> parentList();
	int addGoods(@Param("goods")Goods goods);
	int addCar(@Param("car")ShoppingCar car);
	int carCount(@Param("car")ShoppingCar car);
	int carEditCount(@Param("car")ShoppingCar car);
	int changeGoods(Goods goods);
	List<Goods> byIdGoods(long id);
	List<Goods> goodList();
	int shangXiaJia(Goods goods);
	//É¾³ý¶©µ¥ºó»Ø¹ö
	int rollBack(Goods goods);
}
