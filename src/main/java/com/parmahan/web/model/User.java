package com.parmahan.web.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter
	private String id;

	@Getter @Setter
	private String userId;

	@Getter @Setter
	private String title;

	@Getter @Setter
	private String completed;
}
