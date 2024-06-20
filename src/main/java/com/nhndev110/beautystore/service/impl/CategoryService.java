package com.nhndev110.beautystore.service.impl;

import com.nhndev110.beautystore.core.BaseService;
import com.nhndev110.beautystore.dao.impl.CategoryDAO;
import com.nhndev110.beautystore.dao.impl.ProductDAO;
import com.nhndev110.beautystore.dto.CategoryDTO;
import com.nhndev110.beautystore.model.CategoryModel;
import com.nhndev110.beautystore.service.ICategoryService;
import jakarta.inject.Inject;
import java.util.List;

public class CategoryService extends BaseService<CategoryDTO, CategoryModel> implements ICategoryService {

	@Inject
	private CategoryDAO categoryDAO;
	
	@Override
	public List<CategoryModel> getCategories() {
		return categoryDAO.getAll();
	}

	@Override
	public CategoryDTO getCategoryById(String categoryId) {
		CategoryModel categoryModel = categoryDAO.getById(Long.parseLong(categoryId));
		return categoryModel == null ? new CategoryDTO() : convertToDTO(categoryModel);
	}

	@Override
	public CategoryDTO insertCategory(CategoryModel category) {
		Long categoryId = categoryDAO.insert(category);
		CategoryModel newCategory = categoryDAO.getById(categoryId);
		return convertToDTO(newCategory);
	}

	@Override
	protected CategoryDTO convertToDTO(CategoryModel objectModel) {
		return new CategoryDTO(
			objectModel.getId(),
			objectModel.getName(),
			objectModel.getThumbnail()
		);
	}

}
