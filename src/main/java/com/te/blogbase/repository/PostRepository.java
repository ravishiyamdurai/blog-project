package com.te.blogbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogbase.entity.Posts;
@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{

}
