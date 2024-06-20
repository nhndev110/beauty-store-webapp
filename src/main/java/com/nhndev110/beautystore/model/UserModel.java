package com.nhndev110.beautystore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

	private long id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String password;
	private String role;
	private String rememberToken;

	public UserModel(String name, String email, String phone, String password) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

}
