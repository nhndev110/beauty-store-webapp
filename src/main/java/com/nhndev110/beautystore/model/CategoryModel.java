package com.nhndev110.beautystore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {

	private Long id;
	private String name;
	private String thumbnail;
	
}
