package com.vijayi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijayi.entity.Comment;
import com.vijayi.entity.User;
import com.vijayi.exception.InvalidRequestException;
import com.vijayi.helper.CommonUtils;
import com.vijayi.model.CommentModel;
import com.vijayi.repository.CommentRepository;
import com.vijayi.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private CommonUtils commonUtils;

	@Override
	@Transactional
	public void addComment(CommentModel commentModel) throws Exception {
		String commentTo = commentModel.getCommentTo();
		String commentFrom = commentModel.getCommentFrom();
		String message = commentModel.getMessage();

		Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
		Matcher matcher1 = pattern.matcher(commentTo);
		Matcher matcher2 = pattern.matcher(commentFrom);

		if (!matcher1.matches() || !matcher2.matches()) {
			throw new InvalidRequestException("invalid request");
		}

		User savedUser = userRepo.findByCommentFromAndCommentTo(commentFrom, commentTo);
		if (savedUser == null) {
			User user = new User();
			user.setAddedAt(commonUtils.generateDateAndTime());
			user.setUserId(commonUtils.generateUserId());
			user.setCommentFrom(commentFrom);
			user.setCommentTo(commentTo);
			savedUser = userRepo.save(user);
		}

		Comment comment = new Comment();
		comment.setCommentDate(commonUtils.generateDateAndTime());
		comment.setCommentId(commonUtils.generateCommentId(message));
		comment.setMessage(message);
		comment.setPostedByUserId(savedUser.getUserId());
		commentRepo.save(comment);
	}

	@Override
	public List<Map<String, String>> getComment(String commentTo) throws Exception {
		Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
		Matcher matcher = pattern.matcher(commentTo);

		if (!matcher.matches()) {
			throw new InvalidRequestException("invalid request");
		}

		List<Map<String, String>> responseList = new ArrayList<>();

		List<User> userList = userRepo.findByCommentTo(commentTo);
		if (!userList.isEmpty()) {
			for (User user : userList) {
				List<Comment> commentList = commentRepo.findByPostedByUserId(user.getUserId());
				for (Comment comment : commentList) {
					Map<String, String> map = new HashMap<>();
					map.put("message", comment.getMessage());
					map.put("message by", comment.getPostedByUserId());
					responseList.add(map);
				}
			}
		}
		return responseList;
	}

}
