package com.nhndev110.beautystore.core;

import java.sql.ResultSet;

public interface RowMapper<T> {

	T mapRow(ResultSet rs);

}
