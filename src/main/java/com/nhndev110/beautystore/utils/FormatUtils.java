package com.nhndev110.beautystore.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {

	public static String getMoneyString(double amount) {
		Locale locale = new Locale("en", "US");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(amount);
	}

}
