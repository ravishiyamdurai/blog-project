package com.te.blogbase.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import lombok.Data;
@Entity
@Data
//@Component
public class PostComments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60)
	private String title;
	//@Column(nullable = false)
	//private Integer published;
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date publishedAt;
	@Column(length = 60)
	private String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="parentid")
	private PostComments comments;
	
	//@JsonIgnoreProperties({"categoryList", "tagsList","postMetaList","users"})
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="postid")
	private Posts posts;
	
	


}
