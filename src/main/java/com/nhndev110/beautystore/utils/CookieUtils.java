package com.nhndev110.beautystore.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static void setCookie(HttpServletResponse resp, String key, String value, int minutes) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(60 * minutes);
		resp.addCookie(cookie);
	}

	public static String getCookie(HttpServletRequest req, String key) {
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return cookie.getValue();
				}
			}
		}

		return null;
	}

	public static boolean isCookie(HttpServletRequest req, String key) {
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					return true;
				}
			}
		}

		return false;
	}

	public static void removeCookie(HttpServletRequest req, HttpServletResponse resp, String key) {
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}
		}
	}

}
