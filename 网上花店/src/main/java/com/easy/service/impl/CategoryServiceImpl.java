package com.easy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.bean.Category;
import com.easy.dao.ICategoryDao;
import com.easy.service.ICategoryService;
import com.easy.util.Page;
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	ICategoryDao dao;
	public List<Category> categoryList(Category category, Page page) {
		List<Category> result=dao.categoryList(category,page);
		return result;
	}
	public int categoryCount(Category category) {
		int count =dao.categoryCount(category);
		return count;
	}
	public int categoryEidt(Category category) {
		int count =dao.categoryEidt(category);
		return count;
	}
	public int categoryDel(int id) {
		int count=dao.categoryDel(id);
		return count;
	}
	public int categoryAdd(Category category) {
		int count = dao.categoryAdd(category);
		return count;
	}

}
