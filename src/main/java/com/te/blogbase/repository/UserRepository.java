package com.te.blogbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.blogbase.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByFisrtName(String fisrtName);

}
