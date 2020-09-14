package com.parmahan.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.parmahan.web.constant.WebPageConstant;
import com.parmahan.web.model.User;

@Controller
public class UserWebController {

	@Value("${api-url}")
	private String URL;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/users")
	public String getAllUsers(Model model) {
		User user = restTemplate.getForObject(URL + "/employees", User.class);
		model.addAttribute("user", user.getCompleted());
		return WebPageConstant.USERS;
	}

	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return WebPageConstant.GREETING;
	}
}
