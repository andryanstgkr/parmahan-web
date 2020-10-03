package com.parmahan.web.common;

import org.springframework.ui.Model;

public class CommonStaticMethod {

	public static void generateDynamicPage(String pageTitle, String content, Model model) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("content", content);
	}
}
