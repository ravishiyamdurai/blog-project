package com.te.blogbase.dto.userdto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MetaDataDto {
	private String key;
	private String content; 
	private Long postid;

}
