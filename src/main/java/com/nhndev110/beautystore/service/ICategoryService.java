package com.nhndev110.beautystore.service;

import com.nhndev110.beautystore.dto.CategoryDTO;
import com.nhndev110.beautystore.model.CategoryModel;
import java.util.List;

public interface ICategoryService {

	public List<CategoryModel> getCategories();
	
	public CategoryDTO getCategoryById(String categoryId);
	
	public CategoryDTO insertCategory(CategoryModel category);

}
