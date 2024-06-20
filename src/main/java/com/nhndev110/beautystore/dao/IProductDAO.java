package com.nhndev110.beautystore.dao;

import com.nhndev110.beautystore.core.GenericDAO;
import java.util.List;
import com.nhndev110.beautystore.model.ProductModel;

public interface IProductDAO extends GenericDAO<ProductModel> {

	public List<ProductModel> getAll(String query, int page);

	public int countByQuery(String query);

	public ProductModel getById(long id);

	public List<ProductModel> getByName(String query);

	public List<ProductModel> getByCategoryId(long categoryId);

}
