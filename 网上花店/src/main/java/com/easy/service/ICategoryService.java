package com.easy.service;

import java.util.List;



import com.easy.bean.Category;
import com.easy.util.Page;

public interface ICategoryService {
	List<Category> categoryList(Category category,Page page);
	int categoryCount(Category category);
	int categoryEidt(Category category);
	int categoryDel (int id);
	int categoryAdd(Category category);
	
}
