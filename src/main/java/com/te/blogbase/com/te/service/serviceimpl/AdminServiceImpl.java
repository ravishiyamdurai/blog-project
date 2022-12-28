package com.te.blogbase.com.te.service.serviceimpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.te.blogbase.com.te.service.serviceinterface.AdminServiceInterface;
import com.te.blogbase.entity.Posts;
import com.te.blogbase.entity.Users;
import com.te.blogbase.exceptionpackage.BlogException;
import com.te.blogbase.model.UserRequest;
import com.te.blogbase.repository.PostRepository;
import com.te.blogbase.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminServiceInterface, UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public boolean register(Users dto) {
		if (dto != null) {

			repository.save(dto);
			return true;
		}
		return false;
	}
	
	@Override
	public String Login(UserRequest request) {
		Users users = repository.findByFisrtName(request.getUserName());
		 Set<String> role = users.getRole();

		if (request.getUserName().equals(users.getFisrtName()) && request.getPassword().equals(users.getPassword())) {
			if(role.contains(request.getRole())) {
			return users.getFisrtName() + "login sucessful!!!";
			}

		}
		throw new BlogException("some one not match");
	}
	

	@Override
	public UserDetails loadUserByUsername(String fisrtName) throws UsernameNotFoundException {
		Users findByFisrtName = repository.findByFisrtName(fisrtName);
		if (findByFisrtName == null) {
			throw new UsernameNotFoundException("user" + fisrtName + "not exist");
		}
		if (findByFisrtName.getRole() == null || findByFisrtName.getRole().isEmpty()) {
			throw new UsernameNotFoundException("user has no roles");
		}
		Collection<GrantedAuthority> authorities = findByFisrtName.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

		return new User(findByFisrtName.getFisrtName(), findByFisrtName.getPassword(), authorities);
	}

	@Override
	public Long deleteprofile(Long id) {
		Users users = repository.findById(id).orElse(null);
		if (users != null) {
			repository.delete(users);
			return id;
		}
		throw new BlogException("profile not deleted");
	}

	@Override
	public Long countPost() {
		List<Posts> listPost = postRepository.findAll();
		return listPost.stream().count();
	}

	

}
