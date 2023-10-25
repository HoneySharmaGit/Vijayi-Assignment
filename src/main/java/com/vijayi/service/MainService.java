package com.vijayi.service;

import java.util.List;
import java.util.Map;

import com.vijayi.model.CommentModel;

public interface MainService {

	void addComment(CommentModel commentModel) throws Exception;

	List<Map<String, String>> getComment(String commentTo) throws Exception;

}
