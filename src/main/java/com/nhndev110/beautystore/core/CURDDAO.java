package com.nhndev110.beautystore.core;

import java.util.List;

public interface CURDDAO<T> {

	public List<T> getAll();
	
	public Long insert(T object);
	
	public boolean update(T object);
	
	public boolean deleteById(long id);
	
}
