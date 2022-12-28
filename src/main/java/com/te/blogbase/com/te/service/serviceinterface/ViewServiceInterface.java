package com.te.blogbase.com.te.service.serviceinterface;

import java.util.List;

import com.te.blogbase.dto.viewer.PostCommentDto;
import com.te.blogbase.dto.viewer.UpdateCommentDto;
import com.te.blogbase.entity.PostComments;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.entity.Tags;

public interface ViewServiceInterface {

	Long add(PostCommentDto postCommentDto);

	Tags shareTag(Long id);

	Long deleteComment(Long id);

	Long updateComment(UpdateCommentDto commentDto);

	List<PostComments> getAllComments();

	List<Posts> searchByName(String fisrtName);

	Long countPost(String fisrtName);


}
