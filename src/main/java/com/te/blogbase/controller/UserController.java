package com.te.blogbase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.blogbase.com.te.service.serviceinterface.UserServiceInterface;
import com.te.blogbase.dto.userdto.CatergoryDto;
import com.te.blogbase.dto.userdto.MetaDataDto;
import com.te.blogbase.dto.userdto.PostDto;
import com.te.blogbase.dto.userdto.SearchDateDto;
import com.te.blogbase.dto.userdto.TagDto;
import com.te.blogbase.dto.userdto.UserUpdateDto;
import com.te.blogbase.entity.Category;
import com.te.blogbase.entity.PostComments;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.exceptionpackage.BlogException;
import com.te.blogbase.model.JwtToken;
import com.te.blogbase.model.UserRequest;
import com.te.blogbase.model.UserResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
//@Slf4j
public class UserController {

	@Autowired
	private UserServiceInterface userService;
	@Autowired
	private JwtToken jwtToken;

//	@PostMapping("/login")
//	public ResponseEntity<UserResponse> userLogin(@RequestBody UserRequest request) {
//		String token = jwtToken.generateToken(request.getUserName());
//		return ResponseEntity.ok(new UserResponse(token, "logged in successful!!!"));
//	}
	
	

	@PostMapping("/addpost")
	public ResponseEntity<String> getRegister(@RequestBody PostDto posts) {
		Long id = userService.register(posts);
		String body = "post  " + id + " is added";
		return ResponseEntity.ok(body);
	}

	@PostMapping("/addcategory")
	public ResponseEntity<String> addCategory(@RequestBody CatergoryDto dto) {
		Long id = userService.addCategory(dto);
		String body = "viewer category " + id + " register";
		return ResponseEntity.ok(body);

	}

	@PostMapping("/addtag")
	public ResponseEntity<String> addTag(@RequestBody TagDto dto) {
		Long id = userService.addTag(dto);
		String body = "viewer shared " + id + " tag";
		return ResponseEntity.ok(body);

	}

	@PostMapping("/addmetadata")
	private ResponseEntity<String> addMetaData(@RequestBody MetaDataDto dataDto) {
		Long id = userService.addMetaData(dataDto);
		String body = "meta data " + id + " added";
		return ResponseEntity.ok(body);

	}

	@PutMapping("/updateprofile")
	private ResponseEntity<String> updateprofile(@RequestBody UserUpdateDto dto) {
		Long id = userService.updateprofile(dto);
		String body = "user profile " + id + " updated";
		return ResponseEntity.ok(body);
	}

	@DeleteMapping("/deleteprofile/{id}")
	private ResponseEntity<String> deleteprofile(@PathVariable("id") Long id) {
		Long idinfo = userService.deleteprofile(id);
		String body = "user profile " + idinfo + " deleted";
		return ResponseEntity.ok(body);

	}

	@PostMapping("/postsearchbydate")
	private ResponseEntity<List<Posts>> searchByDate(@RequestBody SearchDateDto createdAt) {
		List<Posts> list = userService.searchByDate(createdAt);
		if (list != null) {
			return ResponseEntity.ok(list);
		}
		throw new BlogException("invalid date");
	}

	@GetMapping("/getallpost")
	private ResponseEntity<List<Posts>> getAllPost() {
		List<Posts> list = userService.getAllPost();
		return ResponseEntity.ok(list);

	}

	@GetMapping("/getallcomments")
	public ResponseEntity<List<PostComments>> getAllComments() {
		List<PostComments> comments = userService.getAllComments();
		return ResponseEntity.ok(comments);
	}

	@GetMapping("/getallcategory")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> comments = userService.getAllCategory();
		return ResponseEntity.ok(comments);
	}

	@PostMapping("/searchuserpost/{id}")
	public ResponseEntity<List<Posts>> searchuser(@PathVariable Long id) {
		List<Posts> idNum = userService.searchuser(id);
		// String body="viewer shared "+idNum+" tag";
		return ResponseEntity.ok(idNum);

	}

	@GetMapping("/countpost/{id}")
	public ResponseEntity<String> countPost(@PathVariable("id") Long id) {
		Long count = userService.countPost(id);
		String body = " total " + count + " post";
		return ResponseEntity.ok(body);

	}

}
