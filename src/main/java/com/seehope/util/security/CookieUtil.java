package com.seehope.util.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static String DEFAULT_PATH = "/";
	public static int DEFAULT_MAXAGE = 2592000;

	public static void addCookie(String name, String value, HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie(name, Base64Util.encode(value));
		cookie.setPath(DEFAULT_PATH);
		cookie.setMaxAge(DEFAULT_MAXAGE);
		response.addCookie(cookie);
	}

	public static void addCookie(String domain, int maxAge, String name, String value, HttpServletResponse response)
			throws Exception {
		Cookie cookie = new Cookie(name, Base64Util.encode(value));
		if ((domain != null) && (!"".equals(domain.trim()))) {
			cookie.setDomain(domain);
		}
		cookie.setPath(DEFAULT_PATH);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void removeCookie(String name, HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(DEFAULT_PATH);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static void removeCookie(String domain, String name, HttpServletResponse response) throws Exception {
		Cookie cookie = new Cookie(name, null);
		cookie.setDomain(domain);
		cookie.setPath(DEFAULT_PATH);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static String getCookieValue(String name, HttpServletRequest request) {
		String value = "";
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				Cookie[] arrayOfCookie1;
				int j = (arrayOfCookie1 = cookies).length;
				for (int i = 0; i < j; i++) {
					Cookie cookie = arrayOfCookie1[i];
					if (cookie.getName().equals(name)) {
						value = Base64Util.decode(cookie.getValue());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void removeAll(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookie.setMaxAge(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Cookie getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if ((cookies != null) && (cookies.length > 0)) {
			Cookie[] arrayOfCookie1;
			int j = (arrayOfCookie1 = cookies).length;
			for (int i = 0; i < j; i++) {
				Cookie cookie = arrayOfCookie1[i];
				if (cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}
}
