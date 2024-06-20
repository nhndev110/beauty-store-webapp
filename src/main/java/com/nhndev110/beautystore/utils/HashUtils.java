package com.nhndev110.beautystore.utils;

import com.nhndev110.beautystore.core.Constants;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class HashUtils {

	public static String getMd5(String input) {
		input = input + Constants.SECRET_KEY;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(HashUtils.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

}
