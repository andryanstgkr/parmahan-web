package com.parmahan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.parmahan.web.constant.WebPageConstant;
import com.parmahan.web.model.Product;

public class ProductController {
	@Value("${api-url}")
	private String URL;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/products")
	public String getAllProducts(Model model) {
		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(URL + "/product/getAllProducts", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Product>>() {
				});
		List<Product> productList = responseEntity.getBody();
		model.addAttribute("products", productList);
		model.addAttribute("pageTitle", "List of Products");
		model.addAttribute("content", WebPageConstant.PRODUCTS);
		return WebPageConstant.MAIN;
	}
}
