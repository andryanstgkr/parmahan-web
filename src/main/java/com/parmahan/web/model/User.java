package com.parmahan.web.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String password;

	private boolean active;
}
