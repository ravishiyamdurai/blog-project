package com.te.blogbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogbase.entity.PostComments;
@Repository
public interface PostCommentRepository extends JpaRepository<PostComments, Long>{

}
