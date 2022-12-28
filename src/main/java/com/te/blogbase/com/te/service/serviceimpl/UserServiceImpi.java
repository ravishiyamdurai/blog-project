package com.te.blogbase.com.te.service.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.blogbase.com.te.service.serviceinterface.UserServiceInterface;
import com.te.blogbase.dto.userdto.CatergoryDto;
import com.te.blogbase.dto.userdto.MetaDataDto;
import com.te.blogbase.dto.userdto.PostDto;
import com.te.blogbase.dto.userdto.SearchDateDto;
import com.te.blogbase.dto.userdto.TagDto;
import com.te.blogbase.dto.userdto.UserUpdateDto;
import com.te.blogbase.entity.Category;
import com.te.blogbase.entity.PostComments;
import com.te.blogbase.entity.PostMeta;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.entity.Tags;
import com.te.blogbase.entity.Users;
import com.te.blogbase.exceptionpackage.BlogException;
import com.te.blogbase.model.UserRequest;
import com.te.blogbase.repository.CategoryRepository;
import com.te.blogbase.repository.MetaDataRepository;
import com.te.blogbase.repository.PostCommentRepository;
import com.te.blogbase.repository.PostRepository;
import com.te.blogbase.repository.TagRepository;
import com.te.blogbase.repository.UserRepository;

@Service
public class UserServiceImpi implements UserServiceInterface {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MetaDataRepository dataRepository;
	@Autowired
	private Posts postsInfo;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private Tags tags;
	@Autowired
	private PostCommentRepository commentRepository;

	@Override
	public Long register(PostDto posts) {
		if (posts != null) {
			Posts findById = postRepository.findById(posts.getParentid()).orElse(null);
			List<Posts> list = new ArrayList();
			list.add(findById);
			postsInfo.setParentPosts(list);

			Users users = userRepository.findById(posts.getAuthorid()).orElse(null);
			postsInfo.setUsers(users);
			BeanUtils.copyProperties(posts, postsInfo);
			return postRepository.save(postsInfo).getId();
		}

		throw new BlogException("post not added");
	}

	@Override
	public Long addCategory(CatergoryDto dto) {
		Category category = new Category();
		if (dto != null) {
			postsInfo = postRepository.findById(dto.getPostId()).orElse(null);
			Category categoryInfo = categoryRepository.findById(dto.getParentid()).orElse(null);
			List<Category> list = new ArrayList<>();
			list.add(categoryInfo);
			category.setCategoryList(list);
			category.setContent(postsInfo.getContent());
			category.setMetaTitle(postsInfo.getMetaTitle());
			category.setSlug(postsInfo.getSlug());
			category.setTitle(postsInfo.getTitle());
			BeanUtils.copyProperties(dto, category);
			postsInfo.getCategoryList().add(categoryInfo);
			return categoryRepository.save(category).getId();
		}
		throw new BlogException("category not added");
	}

	@Override
	public Long addMetaData(MetaDataDto dataDto) {
		postsInfo = postRepository.findById(dataDto.getPostid()).orElse(null);
		if (postsInfo != null) {
			PostMeta meta = new PostMeta();
			meta.setPosts(postsInfo);
			meta.setContent(postsInfo.getContent());
			BeanUtils.copyProperties(dataDto, meta);
			return dataRepository.save(meta).getId();
		}
		throw new BlogException("not properly added");
	}

	@Override
	public Long updateprofile(UserUpdateDto dto) {
		Users users = userRepository.findById(dto.getId()).orElse(null);
		if (users != null) {
			BeanUtils.copyProperties(dto, users);
			return userRepository.save(users).getId();
		}
		throw new BlogException("user profile not updated");
	}

	@Override
	public Long deleteprofile(Long id) {
		Users users = userRepository.findById(id).orElse(null);
		if (users != null) {
			userRepository.delete(users);
			return id;
		}
		throw new BlogException("profile not deleted");
	}

	@Override
	public List<Posts> searchByDate(SearchDateDto createdAt) {
		Date date = createdAt.getCreatedAt();
		List<Posts> list = postRepository.findAll();
		return list.stream().filter(i -> i.getCreatedAt().equals(date)).collect(Collectors.toList());
		// return list.stream().filter(i ->
		// i.getCreatedAt().equals(date)).map(m->m.get).collect(Collectors.toList());

	}

	@Override
	public Long addTag(TagDto tagDto) {
		List<Tags> list = new ArrayList<>();
		if (tagDto != null) {
			postsInfo = postRepository.findById(tagDto.getPostId()).orElse(null);
			// tags.setPosts(postsInfo);
			tags.setSlug(postsInfo.getSlug());
			tags.setContent(postsInfo.getContent());
			tags.setTitle(postsInfo.getTitle());
			tags.setMetaTitle(postsInfo.getMetaTitle());
			BeanUtils.copyProperties(tagDto, tags);
			list.add(tags);
			postsInfo.getTagsList().add(tags);
			return tagRepository.save(tags).getId();
		}
		throw new BlogException("tag not shared");
	}

	@Override
	public List<Posts> getAllPost() {
		return postRepository.findAll();
	}

	@Override
	public List<PostComments> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Posts> searchuser(Long id) {
		// UserPostDto postDto=new UserPostDto();
		List<Posts> list = postRepository.findAll();
		if (list != null) {
			return list.stream().filter(i -> i.getUsers().getId() == id).collect(Collectors.toList());
		}
		throw new BlogException("please enter correct id");
	}

	@Override
	public Long countPost(Long id) {
		List<Posts> listPost = postRepository.findAll();
		if (listPost != null) {
			return listPost.stream().filter(i -> i.getUsers().getId() == id).count();
		}
		throw new BlogException("please enter correct id");
	}

	@Override
	public Users findByFisrtName(String fisrtName) {
		return null;
	}

}
