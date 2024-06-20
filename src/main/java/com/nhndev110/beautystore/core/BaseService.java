package com.nhndev110.beautystore.core;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T, V> {
	
	protected abstract T convertToDTO(V objectModel);
	
	protected List<T> convertToDTOs(List<V> objectModels) {
		List<T> objectDTOs = new ArrayList<>();
		objectModels.stream().map(objectModel -> convertToDTO(objectModel));
		for (V objectModel : objectModels) {
			objectDTOs.add((T) convertToDTO(objectModel));
		}
		return objectDTOs;
	}
	
}
