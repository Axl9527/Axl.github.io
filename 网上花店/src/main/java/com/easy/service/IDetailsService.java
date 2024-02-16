package com.easy.service;

import java.util.List;

import com.easy.bean.Detail;

public interface IDetailsService {

	List<Detail> list(Detail detail);
	int add(Detail detail);
}
