package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.bean.Address;
import com.easy.bean.Detail;
import com.easy.bean.Goods;
import com.easy.bean.Order;
import com.easy.bean.Order2;
import com.easy.bean.OrderAll;
import com.easy.bean.User;
import com.easy.dao.IDetailsDao;
import com.easy.dao.IGoodsDao;
import com.easy.dao.IOrderDao;
import com.easy.dao.IShoppingCarDao;
import com.easy.service.IOrderService;
import com.easy.util.Page;
import com.easy.util.SnowFlakeUtil;

@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderDao dao;
	@Autowired
	IDetailsDao ddao;

	@Autowired
	IGoodsDao gdao;

	@Autowired
	IShoppingCarDao sdao;
	
	SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(12, 13);

	public List<Order2> orderList(Order2 order, Page page) {
		List<Order2> list = dao.orderList(order, page);
		return list;
	}

	public int orderCount(Order2 order) {
		int count = dao.orderCount(order);
		return count;
	}

	public List<User> parentList() {
		List<User> list = dao.parentList();
		return list;
	}

	public List<User> userA(User user) {
		List<User> list = dao.userA(user);
		return list;
	}

	public List<Address> address(Address address) {
		List<Address> list = dao.address(address);
		return list;
	}

	public int add(Address address) {
		int result = dao.add(address);
		return result;
	}
	@Transactional
	public int addOrder(OrderAll orderAll) {
		System.out.println("���Ƕ���");
		System.out.println("�۸�"+orderAll.getPaymoney());
		orderAll.setCode("" + snowFlakeUtil.getNextId());
		int result1 = dao.addOrder(orderAll);
		int result3 = 0;// ��¼����޸�����
		int yuanStock=0;//ԭ�����
		int maiStock=0; //����������
		int stock=0;   //ʣ������
		// �ش���������
		for (Detail detail : orderAll.getList()) {
			System.out.println(detail);
			detail.setOrderid(orderAll.getId());
			ddao.add(detail);
			List<Goods> list = gdao.byIdGoods(detail.getGoodsid());
			//��������Ʒ��
			maiStock=detail.getCount();
			 yuanStock=	list.get(0).getStock();
			//��Ʒ���ڿ��
				 stock= yuanStock-maiStock;
				 list.get(0).setStock(stock);
				 gdao.changeGoods(list.get(0));
			//ɾ�����ﳵ��Ӧ��Ʒ
				int goodid=(int)detail.getGoodsid();
				int userd=(int)detail.getUserid();	 
				sdao.deleteCar(goodid, userd);
		}
		
		return result1;
	}
	public List<OrderAll> checkList(OrderAll order) {
		
		List<OrderAll> list= dao.checkList(order);
		System.out.println(list);
		for(OrderAll o:list) {
		Detail	de= new Detail();
		    de.setOrderid(o.getId());
		
		List<Detail> delist=ddao.list(de);
		o.setList(delist);
		}
		
		return list;
	}
	public int editOrder(OrderAll order) {
		// TODO Auto-generated method stub
		return dao.editOrder(order);
	}
	public int faHuo(Order2 order) {
		// TODO Auto-generated method stub
		return dao.faHuo(order);
	}
	public int delOrder(List<Order2> order) {
		System.out.println("aaaaaaaaaaaaaaaaa"+order);
		
		
		
		return 0;
	}
	public int defClear(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	    @Transactional
	    public int editAddress(Address address) {
	        if(address.getDef()==1){
	            System.out.println("��Ҫ���");
	            dao.defClear(address.getUserid());
	        }
	        System.out.println("�޸�xxxxx");
	        int result=dao.editAddress(address);
	        return result;
	    }
	    public int delAddress(long id) {
	    	// TODO Auto-generated method stub
	    	return dao.delAddress(id);
	    }
	    
}
