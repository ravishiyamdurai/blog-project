package com.te.blogbase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponse {
	private String token;
	private String message;

	public UserResponse(String token, String message) {
		this.token = token;
		this.message = message;
	}

}
