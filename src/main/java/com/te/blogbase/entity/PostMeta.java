package com.te.blogbase.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class PostMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String key;
	private String content;
	
	//@JsonIgnoreProperties({"categoryList", "tagsList","comments"})
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "post_id", referencedColumnName = "id", nullable = true)
	private Posts posts;

}
