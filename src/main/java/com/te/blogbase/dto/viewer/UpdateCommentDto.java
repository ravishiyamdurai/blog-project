package com.te.blogbase.dto.viewer;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UpdateCommentDto {
	private Long commentId;
	private String title;
	private Date createdAt;
	private Date publishedAt;
	private String content;
	//private Long postid;
	//private Long parentid;
	

}
