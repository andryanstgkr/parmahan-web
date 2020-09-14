package com.parmahan.web.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Address {
	private String id;
	private String street;
	private String city;
	private String zipcode;
	private Geo geo; 
}
