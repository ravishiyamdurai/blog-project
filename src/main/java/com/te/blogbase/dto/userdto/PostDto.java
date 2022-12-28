package com.te.blogbase.dto.userdto;

import java.util.Date;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PostDto {
	private String title;
	private String metaTitle;
	private String slug;
	private String summary;
	private String published;
	private Date createdAt;
	private Date updatedAt;
	private Date publishedAt;
	private String content;
	private Long authorid;
	private Long parentid;

}
