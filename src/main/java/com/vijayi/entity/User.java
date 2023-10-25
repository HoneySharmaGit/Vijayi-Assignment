package com.vijayi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_no")
	private int sNo;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "comment_from")
	private String commentFrom;

	@Column(name = "comment_to")
	private String commentTo;

	@Column(name = "added_at")
	private String addedAt;

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentFrom() {
		return commentFrom;
	}

	public void setCommentFrom(String commentFrom) {
		this.commentFrom = commentFrom;
	}

	public String getCommentTo() {
		return commentTo;
	}

	public void setCommentTo(String commentTo) {
		this.commentTo = commentTo;
	}

	public String getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}

}
