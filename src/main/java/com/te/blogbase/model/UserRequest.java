package com.te.blogbase.model;

import lombok.Data;

@Data
public class UserRequest {
	private String userName;
	private String password;
	private String role;

}
