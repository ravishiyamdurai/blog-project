package com.te.blogbase.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

import lombok.Data;

@Component
@Entity
@Data
public class Posts {
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
	private String summary;
	@Column(nullable = false, length = 60)
	private String published;
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date publishedAt;
	@Column(nullable = false, length = 60)
	private String content;

	@ElementCollection
	// @JsonIgnoreProperties({ "categoryList", "tagsList", "postMetaList", "users"})
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentid")
	private List<Posts> parentPosts = Lists.newArrayList();

	@ManyToOne
	@JoinColumn(name = "authorid")
	private Users users;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "post_category")
	private List<Category> categoryList = Lists.newArrayList();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "post_tag")
	private List<Tags> tagsList = Lists.newArrayList();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "posts")
	// @JoinColumn(name="postid")
	private List<PostMeta> postMetaList = Lists.newArrayList();

//	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "posts")
//	@JoinColumn(name = "postid")
//	private List<PostComments> comments;

}
