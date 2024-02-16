package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.bean.ShoppingCar;
import com.easy.dao.IShoppingCarDao;
import com.easy.service.IShoppingCarService;
@Service
public class ShopppingCarService implements IShoppingCarService{
	@Autowired
	IShoppingCarDao dao;
	@Transactional
	public int addCar(ShoppingCar car) {
		// 1.��鹺�ﳵ�Ƿ��и���Ʒ
		int count =dao.carCoun(car);
		int result=0;
		//2.����Ƿ����ظ���Ʒ ������+1
		if(count>0) {
			result=dao.carEditCount(car);
		}else {
			//û�оͲ���һ������
			result=dao.addCar(car);
		}
		return result;
	}

	public List<ShoppingCar> car(ShoppingCar car) {
		// TODO Auto-generated method stub
		return dao.car(car);
	}
public int editCount(ShoppingCar car) {
	// TODO Auto-generated method stub
	return dao.editCount(car);
}

public int deleteCar(int goodsid,int userid) {
	// TODO Auto-generated method stub
	return dao.deleteCar(goodsid,userid);
}
public List<ShoppingCar> choose() {
	// TODO Auto-generated method stub
	return dao.choose();
}
}
