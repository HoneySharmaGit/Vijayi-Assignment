package com.vijayi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vijayi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("SELECT c.commentId FROM Comment c ORDER BY c.sNo DESC LIMIT 1")
	String findLastGeneratedCommentId();

	List<Comment> findByPostedByUserId(String userId);

}
