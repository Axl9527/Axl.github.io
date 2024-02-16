package com.easy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.bean.Address;
import com.easy.bean.Order;
import com.easy.bean.Order2;
import com.easy.bean.OrderAll;
import com.easy.bean.User;
import com.easy.util.Page;

public interface IOrderService {

	List<Order2> orderList(Order2 order, Page page);

	int orderCount(Order2 order);

	List<User> parentList();

	List<User> userA(User user);

	List<Address> address(Address address);

	int add(Address address);

	int addOrder(OrderAll orderAll);

	List<OrderAll> checkList(OrderAll order);

	int editOrder(OrderAll order);

	int faHuo(Order2 order);

	int delOrder(List<Order2> order);

	int editAddress(Address address);

	int defClear(long id);
	
	int delAddress(long id);
}
