package com.te.blogbase.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Entity
@Data
public class Users{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String fisrtName;
	@Column(nullable = false, length = 30)
	private String middleName;
	@Column(nullable = false, length = 30)
	private String lastName;
	@Column(nullable = false, length = 30)
	private String mobile;
	@Column(nullable = false, length = 30)
	private String email;
	@Column(nullable = false, length = 30)
	private String password;
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date registeredAt;
	@Column(nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date lastLogin;
	@Column(nullable = false, length = 30)
	private String intro;
	@Column(nullable = false, length = 30)
	private String profile;
	
	@ElementCollection
	@CollectionTable(name="roletable",joinColumns = @JoinColumn(name="id"))
	@Column(name="role")
	private Set<String> role;
	
//	@JsonIgnoreProperties({"categoryList", "tagsList","postMetaList","parentPosts"})
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="authorid")
//	private List<Posts> posts;

}
