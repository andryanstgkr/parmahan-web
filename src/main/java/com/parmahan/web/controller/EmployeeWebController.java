package com.parmahan.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.parmahan.web.constant.WebPageConstant;
import com.parmahan.web.model.Employee;

@Controller
public class EmployeeWebController {

	@Value("${api-url}")
	private String URL;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/employees")
	public String getAllEmployees(Model model) {
		ResponseEntity<List<Employee>> responseEntity  = restTemplate.exchange(URL + "/employees"
				+ "/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});
		List<Employee> employeeList = responseEntity.getBody();
		for (Employee employee : employeeList) {
			System.out.println(employee.getId()+". "+employee.getName());
		}
		model.addAttribute("employees", employeeList);
		
		return WebPageConstant.EMPLOYEE;
	}
}
