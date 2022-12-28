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

import com.te.blogbase.com.te.service.serviceinterface.ViewServiceInterface;
import com.te.blogbase.dto.viewer.PostCommentDto;
import com.te.blogbase.dto.viewer.UpdateCommentDto;
import com.te.blogbase.entity.PostComments;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.entity.Tags;

@RestController
@RequestMapping("/viewer")
public class ViewersController {
	
	@Autowired
	private ViewServiceInterface viewService;
	
	@PostMapping("/addpostcomment")
	public ResponseEntity<String> getRegister(@RequestBody PostCommentDto PostCommentDto) {
		Long id = viewService.add(PostCommentDto);
		String body="viewer shared "+id+" tag";
		return ResponseEntity.ok(body);
		
	}
	@PostMapping("/sharetag/{id}")
	public ResponseEntity<Tags> shareTag(@PathVariable Long id) {
		Tags idNum = viewService.shareTag(id);
		//String body="viewer shared "+idNum+" tag";
		return ResponseEntity.ok(idNum);

	}
	
	@DeleteMapping("deletecomment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Long id){
		Long idNum= viewService.deleteComment(id);
		String body="viewer shared "+idNum+" tag";
		return ResponseEntity.ok(body);
	}
	
	@PutMapping("/updatecomment")
	public ResponseEntity<String> updateComment(@RequestBody UpdateCommentDto commentDto){
		Long idNum= viewService.updateComment(commentDto);
		String body="comment "+idNum+" updated";
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/getallcomments")
	public ResponseEntity<List<PostComments>> getAllComments(){
		List<PostComments> comments=viewService.getAllComments();
		return ResponseEntity.ok(comments);
	}
	
	@GetMapping("/searchbyname/{fisrtName}")
	public ResponseEntity<List<Posts>> searchByName(@PathVariable("fisrtName") String fisrtName){
		List<Posts> listPost=viewService.searchByName(fisrtName);
		return ResponseEntity.ok(listPost);
	}
	
	
	@GetMapping("/countpost/{fisrtName}")
	public ResponseEntity<String> countPost(@PathVariable("fisrtName") String fisrtName){
		Long count=viewService.countPost(fisrtName);
		String body = fisrtName+" total post is " + count ;
		return ResponseEntity.ok(body);
		
	}
}
