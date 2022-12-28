package com.te.blogbase.dto.userdto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.te.blogbase.entity.Posts;

import lombok.Data;

@Component
@Data
public class UserPostDto {
	List<Posts> listPost = new ArrayList<>();

}
