package com.nhndev110.beautystore.utils;

import java.util.Date;

public class GenerateUtils {
	
	public static String generateToken(long id) {
		String token = id + String.valueOf(new Date().getTime());
		return HashUtils.getMd5(token);
	}
	
}
