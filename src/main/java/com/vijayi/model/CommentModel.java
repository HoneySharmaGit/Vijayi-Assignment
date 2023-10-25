package com.vijayi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

	private String commentTo;
	private String commentFrom;
	private String message;

	public CommentModel() {

	}

	public String getCommentTo() {
		return commentTo;
	}

	public void setCommentTo(String commentTo) {
		this.commentTo = commentTo;
	}

	public String getCommentFrom() {
		return commentFrom;
	}

	public void setCommentFrom(String commentFrom) {
		this.commentFrom = commentFrom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
