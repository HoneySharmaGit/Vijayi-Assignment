package com.vijayi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vijayi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u.userId FROM User u ORDER BY u.sNo DESC LIMIT 1")
	String findLastGeneratedUserId();

	User findByCommentFromAndCommentTo(String commentFrom, String commentTo);

	List<User> findByCommentTo(String commentTo);

}
