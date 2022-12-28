package com.te.blogbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogbase.entity.PostMeta;
@Repository
public interface MetaDataRepository extends JpaRepository<PostMeta, Long>{

}
