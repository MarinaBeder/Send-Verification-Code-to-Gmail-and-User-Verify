package com.email.util;

import jakarta.servlet.http.HttpServletRequest;

public class utility {
	public static String getSiteUrl(HttpServletRequest request) {
		String siteURL = request.getRequestURI().toString();
		return siteURL.replace(request.getServletPath(),"");
	}
}
