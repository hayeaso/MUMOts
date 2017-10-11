package com.pm.onlinetest.service;

import java.util.List;

import com.pm.onlinetest.domain.Category;
import com.pm.onlinetest.exception.DuplicateCategoryNameException;

public interface CategoryService {

	public List<Category> findAll();
	public List<Category> findAllEnabled();
	public Category findOne(Integer categoryId);
	public void softDelete(Integer categoryId);
	public void save(Category category) throws DuplicateCategoryNameException;
	public List<Category> findCategoryByName(String name);
	public List<String> findAllEnableCategoryNames();
	// void update(long id,Question book);
	// void delete(long id);
}
