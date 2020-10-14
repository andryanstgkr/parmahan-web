package com.parmahan.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.parmahan.web.common.CommonStaticMethod;
import com.parmahan.web.constant.WebPageConstant;
import com.parmahan.web.model.Category;

@Controller
public class CategoryWebController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryWebController.class);
	
	@Value("${api-url}")
	private String URL;

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/categories")
	public String getAllCategories(Model model) {
		ResponseEntity<List<Category>> responseEntity = restTemplate.exchange(URL + "/category/getAll"
				+ "/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {});
		List<Category> categoryList = responseEntity.getBody();
	
		model.addAttribute("categories", categoryList);
		model.addAttribute("pageTitle", "List of Category");
		model.addAttribute("content", WebPageConstant.CATEGORIES);
		return WebPageConstant.MAIN;
	}
	
	@RequestMapping("category/edit/{id}")
	public String editCategory(@PathVariable("id") String id, Model model) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		Category category = restTemplate.getForObject(URL + "/category/get/{id}", Category.class, params);
		model.addAttribute("categoryForm", category);
		CommonStaticMethod.generateDynamicPage("Edit Category - " + category.getName(),
				WebPageConstant.CATEGORY_FORM, model);
		return WebPageConstant.MAIN;
	}
	
	
	@GetMapping(value="category/add")
	public String addNewCategory(Model model) {
		model.addAttribute("categoryForm", new Category());
		CommonStaticMethod.generateDynamicPage("Add Category", WebPageConstant.CATEGORY_FORM, model);
		return WebPageConstant.MAIN;
	}
	
	@PostMapping(value = "/submitCategory")
	public String submitUser(@ModelAttribute("categoryForm") Category category) {
		if(null==category.getId())
			restTemplate.postForObject(URL + "/category/create", category, Category.class);
		else {
			restTemplate.postForObject(URL + "/category/edit", category, Category.class);
		}
		return "redirect:/categories";
	}
}
