package com.easy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.bean.Category;
import com.easy.bean.Goods;
import com.easy.bean.ShoppingCar;
import com.easy.util.Page;

public interface IGoodsService {

	List<Goods> list(Goods goods,Page page);
	int count(Goods goods);
	int delete(int id);
	int goodsEdit(Goods goods);
	int goodsAdd(Goods goods);
	List<Category> parentList();
	int addCar(ShoppingCar car);
	List<Goods> goodList();
	List<Goods> listHou( Goods goods, Page page);
	int countHou( Goods goods);
	int shangXiaJia(Goods goods);
}
