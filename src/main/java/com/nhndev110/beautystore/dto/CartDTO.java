package com.nhndev110.beautystore.dto;

import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private long id;
	private UserModel user;
	private ProductModel product;
	private int quantity;

}
