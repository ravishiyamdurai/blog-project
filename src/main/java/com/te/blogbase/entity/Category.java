package com.te.blogbase.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import lombok.Data;
@Component
@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String title;
	@Column(nullable = false, length = 60)
	private String metaTitle;
	@Column(nullable = false, length = 60)
	private String slug;
	@Column(nullable = false, length = 60)
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="parentid")
	private List<Category> categoryList=Lists.newArrayList();
}
