package com.nhndev110.beautystore.core;

import java.util.HashMap;

public class Validation {
	private boolean required(String str) {
		return str.isEmpty();
	}
	
	public static void validate (HashMap<String, String> params) {
		params.put("name", "required|max:30");
		
		
	}
}
