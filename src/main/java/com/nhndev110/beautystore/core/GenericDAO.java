package com.nhndev110.beautystore.core;

import java.util.List;

public interface GenericDAO<T> {

	public List<T> queryDAO(String sql, RowMapper<T> rowMapper, Object... params);

	public Long insertDAO(String sql, Object... params);

	public boolean updateDAO(String sql, Object... params);

}
