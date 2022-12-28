package com.te.blogbase.dto.viewer;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PostCommentDto {
	private String title;
	//private Integer published;
	private Date createdAt;
	private Date publishedAt;
	private String content;
	private Long postid;
	private Long parentid;

}
