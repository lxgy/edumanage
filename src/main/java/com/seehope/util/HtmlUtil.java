package com.seehope.util;

import org.springframework.web.util.HtmlUtils;

public class HtmlUtil {
	public static String htmlToString(String html) {
		html = HtmlUtils.htmlEscape(html);
		html = html.replaceAll("\n", "<br>");
		html = html.replaceAll("\r", "<br>");
		return html;
	}

	public static void main(String[] args) {
		System.out.println(htmlToString("<div><input\"\"/></div>  \n"));
		System.out.println(htmlToString("<div><input\"\"/></div>    \r"));
	}
}
