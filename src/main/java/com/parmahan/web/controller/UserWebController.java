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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL + "/user/getAllUsers", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<User>>() {
				});
		List<User> userList = responseEntity.getBody();
		model.addAttribute("users", userList);

		return WebPageConstant.USERS;
	}

	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("id") String id, Model model) {
		System.out.println("ID: "+id);
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		User user = restTemplate.getForObject("http://localhost:8080/api/user/get/{id}", User.class, params);
		//User user = restTemplate.getForObject(URL + "/user/get/585B71D6-233A-4D49-8B79-E32A192976D9", User.class);
		model.addAttribute("user", user);
		return WebPageConstant.USER;
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return WebPageConstant.GREETING;
	}
}
