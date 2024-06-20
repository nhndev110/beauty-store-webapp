package com.nhndev110.beautystore.utils;

public class AuthUtils {
	
	public static boolean verifyPassword(String inputPassword, String handPassword) {
		String handInputPassword = HashUtils.getMd5(inputPassword);
		return handInputPassword.equals(handPassword);
	}
	
}
