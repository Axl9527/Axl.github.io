package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.Detail;
import com.easy.dao.IDetailsDao;
import com.easy.service.IDetailsService;
@Service
public class DetailsServiceImpl implements IDetailsService {

	@Autowired
	IDetailsDao dao;
	public int add(Detail detail) {
		// TODO Auto-generated method stub
		return dao.add(detail);
	}
	public List<Detail> list(Detail detail) {
		// TODO Auto-generated method stub
		return dao.list(detail);
	}

}
