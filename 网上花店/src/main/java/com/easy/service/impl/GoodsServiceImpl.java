package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.Category;
import com.easy.bean.Goods;
import com.easy.bean.ShoppingCar;
import com.easy.dao.IGoodsDao;
import com.easy.service.IGoodsService;
import com.easy.util.Page;

@Service
public class GoodsServiceImpl implements IGoodsService{

	@Autowired
	IGoodsDao dao;
	public List<Goods> list(Goods goods, Page page) {
		List<Goods> result=dao.list(goods, page);
		return result;
	}

	public int count(Goods goods) {
		int count=dao.count(goods);
		return count;
	}

	public int delete(int id) {
		int count =dao.delete(id);
		return count;
	}

	public int goodsEdit(Goods goods) {
		int count =dao.goodsEdit(goods);
		return count;
	}
	
public int goodsAdd(Goods goods) {
	int count =dao.goodsAdd(goods);
	return count;
}
public List<Category> parentList() {
	List<Category> list=dao.parentList();
	return list;
}

public int addCar(ShoppingCar car) {
	// 1.检查购物车是否有该商品
	int count =dao.carCount(car);
	int result=0;
	//2.检查是否有重复商品 有数量+1
	if(count>0) {
		result=dao.carEditCount(car);
	}else {
		//没有就插入一条数据
		result=dao.addCar(car);
	}
	return result;
}

public List<Goods> goodList() {
	// TODO Auto-generated method stub
	return dao.goodList();
}

public List<Goods> listHou(Goods goods, Page page) {
	// TODO Auto-generated method stub
	return dao.listHou(goods, page);
}

public int countHou(Goods goods) {
	// TODO Auto-generated method stub
	return dao.countHou(goods);
}

public int shangXiaJia(Goods goods) {
	// TODO Auto-generated method stub
	return dao.shangXiaJia(goods);
}

}
