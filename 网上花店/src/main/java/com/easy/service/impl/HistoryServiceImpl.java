package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.Zuji;
import com.easy.dao.IHistoryDao;
import com.easy.service.IHistoryService;

@Service
public class HistoryServiceImpl implements IHistoryService{

	@Autowired
	IHistoryDao dao;
	public List<Zuji> list(Zuji zuji) {
		// TODO Auto-generated method stub
		return dao.list(zuji);
	}

	public int addCollection(Zuji zuji) {
		int count =dao.chongfu(zuji);
		if(count>=1) {
			dao.editHistory(zuji);
		}else {
			 dao.addCollection(zuji);
		}
		
		return count;
	}

	public int delHistory(Zuji zuji) {
		// TODO Auto-generated method stub
		return dao.delHistory(zuji);
	}

	public int chongfu(Zuji zuji) {
		// TODO Auto-generated method stub
		return 0;
	}

}
