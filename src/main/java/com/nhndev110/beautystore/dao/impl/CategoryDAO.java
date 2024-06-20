package com.nhndev110.beautystore.dao.impl;

import com.nhndev110.beautystore.core.AbstractDAO;
import com.nhndev110.beautystore.core.CURDDAO;
import java.util.List;
import com.nhndev110.beautystore.model.CategoryModel;
import com.nhndev110.beautystore.dao.ICategoryDAO;
import com.nhndev110.beautystore.model.mapper.CategoryModelMapper;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO, CURDDAO<CategoryModel> {

	@Override
	public List<CategoryModel> getAll() {
		String sql = "SELECT * FROM categories";
		return queryDAO(sql, new CategoryModelMapper());
	}

	@Override
	public CategoryModel getById(long id) {
		String sql = "SELECT * FROM categories WHERE id = ?";
		List<CategoryModel> categoryModels = queryDAO(sql, new CategoryModelMapper(), id);
		return categoryModels.isEmpty() ? null : categoryModels.get(0);
	}

	@Override
	public Long insert(CategoryModel category) {
		String sql = "INSERT INTO categories (`name`, thumbnail) VALUES (?, ?)";
		return insertDAO(sql, category.getName(), category.getThumbnail());
	}

	@Override
	public boolean update(CategoryModel category) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public boolean deleteById(long id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
