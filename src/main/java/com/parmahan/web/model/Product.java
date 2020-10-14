package com.parmahan.web.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Product extends Base{
	private String sku;

	private String name;

	private String description;

	private String productStatus;

	private boolean taxable;

	private BigInteger regularPrice;

	private BigInteger discountPrice;

	private int quantity;
}
