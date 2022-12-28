package com.te.blogbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.blogbase.com.te.service.serviceinterface.AdminServiceInterface;
import com.te.blogbase.entity.Users;
import com.te.blogbase.model.UserRequest;
import com.te.blogbase.model.UserResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminServiceInterface adminServiceInterface;

	@PostMapping("/userregister")
	public ResponseEntity<String> getRegister(@RequestBody Users dto) {
		if (adminServiceInterface.register(dto))
			return ResponseEntity.ok("user account registered");

		return null;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> userLogin(@RequestBody UserRequest request) {
		String token = adminServiceInterface.Login(request);
		return ResponseEntity.ok(new UserResponse(token, "logged in successful!!!"));
	}

	@PostMapping("/deleteprofile/{id}")
	private ResponseEntity<String> deleteprofile(@PathVariable("id") Long id) {
		Long idinfo = adminServiceInterface.deleteprofile(id);
		String body = "user profile " + idinfo + " deleted";
		return ResponseEntity.ok(body);

	}

	@GetMapping("/countpost")
	public ResponseEntity<String> countPost() {
		Long count = adminServiceInterface.countPost();
		String body = " total" + count + " post";
		return ResponseEntity.ok(body);

	}

}
