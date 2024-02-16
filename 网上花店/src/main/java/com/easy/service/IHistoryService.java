package com.easy.service;

import java.util.List;

import com.easy.bean.Zuji;

public interface IHistoryService {
	List<Zuji> list(Zuji zuji);
	int addCollection(Zuji zuji);
	int delHistory(Zuji zuji);
	int chongfu(Zuji zuji);

}
