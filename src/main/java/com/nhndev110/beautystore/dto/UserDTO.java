package com.nhndev110.beautystore.dto;

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
public class UserDTO {
	
	private long id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String role;
	private String rememberToken;
	
}
