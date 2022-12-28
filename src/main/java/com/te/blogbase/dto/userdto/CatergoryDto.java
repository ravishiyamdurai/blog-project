package com.te.blogbase.dto.userdto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CatergoryDto {
	private Long parentid;
	private Long postId;

}
