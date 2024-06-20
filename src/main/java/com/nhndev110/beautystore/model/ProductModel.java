package com.nhndev110.beautystore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

	private long id;
	private String name;
	private String image;
	private String description;
	private double price;
	private int quantity;
	private boolean status;
	private long categoryId;
	
	public ProductModel(String name, String image, String description, double price, int quantity, boolean status, long categoryId) {
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.categoryId = categoryId;
	}

}
