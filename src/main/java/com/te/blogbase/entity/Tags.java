package com.te.blogbase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Entity
@Data
public class Tags {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String metaTitle;
	private String slug;
	private String content;
	
//	@JsonIgnoreProperties({"categoryList","postMetaList","comments"})
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinTable(name = "post_tag")
//	private Posts posts;


}
