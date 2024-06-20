package com.nhndev110.beautystore.dto;

import com.nhndev110.beautystore.model.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private long id;
	private String name;
	private String image;
	private String description;
	private double price;
	private int quantity;
	private boolean status;
	private CategoryModel category;

}
