package com.te.blogbase.dto.userdto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SearchDateDto {
	private Date createdAt;

}
