package com.easy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.easy.bean.Address;
import com.easy.bean.Order;
import com.easy.bean.Order2;
import com.easy.bean.OrderAll;
import com.easy.bean.User;
import com.easy.util.Page;

@Mapper
public interface IOrderDao {

	List<Order2> orderList(@Param("order") Order2 order, @Param("page") Page page);

	int orderCount(@Param("order") Order2 order);

	List<User> parentList();

	List<User> userA(User user);

	List<Address> address(Address address);

	int add(Address address);

	int add(Order order);

	int addOrder(OrderAll orderAll);

	List<OrderAll> checkList(@Param("order") OrderAll order);

	int editOrder(OrderAll order);

	int faHuo(Order2 order);

	int delOrder(@Param("order") Order2 order);

	int editAddress(Address address);

	int defClear(long id);
	
	int delAddress(long id);
}
