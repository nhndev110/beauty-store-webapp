package com.nhndev110.beautystore.dao;

import com.nhndev110.beautystore.core.GenericDAO;
import com.nhndev110.beautystore.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {

	public CategoryModel getById(long id);

}
