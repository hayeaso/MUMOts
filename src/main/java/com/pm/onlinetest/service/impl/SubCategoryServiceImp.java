package com.pm.onlinetest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.onlinetest.domain.Category;
import com.pm.onlinetest.domain.Subcategory;
import com.pm.onlinetest.exception.DuplicateCategoryNameException;
import com.pm.onlinetest.exception.DuplicateSubCategoryNameException;
import com.pm.onlinetest.repository.SubCategoryRepository;
import com.pm.onlinetest.service.SubCategoryService;


@Service
public class SubCategoryServiceImp implements SubCategoryService {
	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Override
	public Subcategory findOne(Integer subCategoryId) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findOne(subCategoryId);
	}

	@Override
	public List<Subcategory> findAllEnabled() {
		// TODO Auto-generated method stub
		return subCategoryRepository.findAllEnabled();
	}

	@Override
	public void save(Subcategory subcategory) throws DuplicateSubCategoryNameException {
		// TODO Auto-generated method stub
		
		Subcategory temp = subCategoryRepository.findFirstByName(subcategory.getName());
		if (temp != null && !(temp.getId().equals(subcategory.getId()))) {
			throw new DuplicateSubCategoryNameException("Sub-Category is already exist!");
		}		
		//categoryRepository.save(category);
		
		
		subCategoryRepository.save(subcategory);
	}

	@Override
	public void softDelete(Integer categoryId) {
		// TODO Auto-generated method stub
		subCategoryRepository.softDelete(categoryId);
	}

	@Override
	public List<Subcategory> findSubCategoryByName(String name) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findSubCategoryByName(name);
	}

	@Override
	public List<Subcategory> findByCategoryId(Category category) {
		// TODO Auto-generated method stub
		return subCategoryRepository.findByCategoryId(category);
	}
}
