package com.te.blogbase.com.te.service.serviceinterface;

import java.util.List;

import com.te.blogbase.dto.userdto.CatergoryDto;
import com.te.blogbase.dto.userdto.MetaDataDto;
import com.te.blogbase.dto.userdto.PostDto;
import com.te.blogbase.dto.userdto.SearchDateDto;
import com.te.blogbase.dto.userdto.TagDto;
import com.te.blogbase.dto.userdto.UserUpdateDto;
import com.te.blogbase.entity.Category;
import com.te.blogbase.entity.PostComments;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.entity.Users;
import com.te.blogbase.model.UserRequest;

public interface UserServiceInterface {
	
	Users findByFisrtName(String fisrtName);
	
	Long register(PostDto posts);

	Long addCategory(CatergoryDto dto);

	Long addMetaData(MetaDataDto dataDto);

	Long updateprofile(UserUpdateDto dto);

	Long deleteprofile(Long id);

	List<Posts> searchByDate(SearchDateDto createdAt);
	
	Long addTag(TagDto dto);

	List<Posts> getAllPost();

	List<PostComments> getAllComments();

	List<Category> getAllCategory();

	List<Posts> searchuser(Long id);

	Long countPost(Long id);


}
