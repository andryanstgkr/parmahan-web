package com.parmahan.web.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Base {

	private String id;

	private Date insertedAt;

	private Date updatedAt;

	

}
