package com.pm.onlinetest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.onlinetest.domain.Category;
import com.pm.onlinetest.exception.DuplicateCategoryNameException;
import com.pm.onlinetest.repository.CategoryRepository;
import com.pm.onlinetest.service.CategoryService;

@Service

public class CategoryServiceImp implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public void save(Category category) throws DuplicateCategoryNameException {
		Category temp = categoryRepository.findFirstByName(category.getName());
		if (temp != null && !(temp.getId().equals(category.getId()))) {
			throw new DuplicateCategoryNameException("Category is already exist!");
		}		
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findAllEnabled() {
		// TODO Auto-generated method stub
		return categoryRepository.findAllEnabled();
	}

	@Override
	public Category findOne(Integer categoryId) {
		// TODO Auto-generated method stub
		return categoryRepository.findOne(categoryId);
	}

	@Override
	public void softDelete(Integer categoryId) {
		// TODO Auto-generated method stub
		categoryRepository.softDelete(categoryId);
	}

	@Override
	public List<Category> findCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findCategoryByName(name);
	}

	@Override
	public List<String> findAllEnableCategoryNames() {
		return categoryRepository.findAllEnableCategoryNames();
	}

}
