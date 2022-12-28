package com.te.blogbase.com.te.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.blogbase.com.te.service.serviceinterface.ViewServiceInterface;
import com.te.blogbase.dto.viewer.PostCommentDto;
import com.te.blogbase.dto.viewer.UpdateCommentDto;
import com.te.blogbase.entity.PostComments;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.entity.Tags;
import com.te.blogbase.exceptionpackage.BlogException;
import com.te.blogbase.repository.PostCommentRepository;
import com.te.blogbase.repository.PostRepository;
import com.te.blogbase.repository.TagRepository;


@Service
public class ViewServiceImpl implements ViewServiceInterface {
	@Autowired
	private PostCommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private TagRepository tagRepository;
//	@Autowired
//	private PostComments comments;

	@Override
	public Long add(PostCommentDto postCommentDto) {
		PostComments comments=new PostComments();
		if (postCommentDto != null) {
			PostComments findById = commentRepository.findById(postCommentDto.getParentid()).orElse(null);
			comments.setComments(findById);
			Posts posts = postRepository.findById(postCommentDto.getPostid()).orElse(null);
			comments.setPosts(posts);
			if (posts != null) {
				BeanUtils.copyProperties(posts, comments);
				return commentRepository.save(comments).getId();
			}
		}

		throw new BlogException("post comment return null");
	}

	@Override
	public Tags shareTag(Long id) {
		Tags tags = tagRepository.findById(id).orElse(null);
		if(tags!=null) {
		return tags;
	}
		throw new BlogException("enter correct tad id");
	}

	@Override
	public Long deleteComment(Long id) {
		PostComments comments=new PostComments();
		comments = commentRepository.findById(id).orElse(null);
		if(comments!=null) {
		commentRepository.delete(comments);
		return id;
		}
		throw new BlogException("Comment not deleted");
	}

	@Override
	public Long updateComment(UpdateCommentDto commentDto) {
		PostComments comments=new PostComments();
		comments= commentRepository.findById(commentDto.getCommentId()).orElse(null);
		if(comments!=null) {
		BeanUtils.copyProperties(commentDto, comments);
		return commentRepository.save(comments).getId();
		}
		throw new BlogException("post comment not updated");
	}

	@Override
	public List<PostComments> getAllComments() {
		return 	commentRepository.findAll();
	}

	@Override
	public List<Posts> searchByName(String fisrtName) {
		List<Posts> listPost= postRepository.findAll();
		return listPost.stream().filter(i->i.getUsers().getFisrtName().equals(fisrtName)).collect(Collectors.toList());
	}

	@Override
	public Long countPost(String fisrtName) {
		List<Posts> listPost= postRepository.findAll();
		return listPost.stream().filter(i->i.getUsers().getFisrtName().equals(fisrtName)).count();
	}


}
