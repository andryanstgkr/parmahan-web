package com.parmahan.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.parmahan.web.common.CommonStaticMethod;
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
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL + "/user/getAllUsers", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<User>>() {
				});
		List<User> userList = responseEntity.getBody();
		model.addAttribute("users", userList);
		model.addAttribute("pageTitle", "List of Users");
		model.addAttribute("content", WebPageConstant.USERS);
		return WebPageConstant.MAIN;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("id") String id, Model model) {
		System.out.println("ID: " + id);
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		User user = restTemplate.getForObject(URL + "/user/get/{id}", User.class, params);
		model.addAttribute("user", user);
		CommonStaticMethod.generateDynamicPage("Pofile - " + user.getFirstName() + " " + user.getLastName(),
				WebPageConstant.USER, model);
		return WebPageConstant.MAIN;
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		CommonStaticMethod.generateDynamicPage("Greeting", WebPageConstant.GREETING, model);
		return WebPageConstant.MAIN;
	}

	@RequestMapping("/user/edit/{id}")
	public String editUser(@PathVariable("id") String id, Model model) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		User user = restTemplate.getForObject(URL + "/user/get/{id}", User.class, params);
		model.addAttribute("userForm", user);
		CommonStaticMethod.generateDynamicPage("Edit User: " + user.getFirstName() + " " + user.getLastName(), WebPageConstant.USER_FORM, model);
		return WebPageConstant.MAIN;
	}

	@PostMapping(value = "/submitUser")
	public String submitUser(Model model) {
		CommonStaticMethod.generateDynamicPage("Done Edit User", WebPageConstant.USER, model);
		return WebPageConstant.MAIN;
	}

	@GetMapping(value = "/main")
	public String mainPage() {
		return "main";
	}
}
