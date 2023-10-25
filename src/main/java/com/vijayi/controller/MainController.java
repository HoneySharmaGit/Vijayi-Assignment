package com.vijayi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijayi.exception.InvalidRequestException;
import com.vijayi.model.CommentModel;
import com.vijayi.model.ResponseModel;
import com.vijayi.service.MainService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/vijayi/api/v1")
public class MainController {

	private static final String errorStatus = "ERROR";
	private static final String successStatus = "SUCCESS";

	@Autowired
	private MainService mainService;

	/**
	 * this API is used to add a comment.
	 *
	 * @param comment from (user A).
	 * @param comment to (user B).
	 * @param message to send to.
	 * @return a success message saying "comment added successfully".
	 * @throws InvalidRequestException if there's an issue adding the comment.
	 */
	@PostMapping("/comment/add")
	public ResponseEntity<ResponseModel> addComment(@RequestBody(required = false) CommentModel commentModel) {
		try {
			if (commentModel == null || commentModel.getCommentTo() == null || commentModel.getCommentTo().equals("")
					|| commentModel.getCommentFrom() == null || commentModel.getCommentTo().equals("")) {
				throw new InvalidRequestException("invalid request");
			}
			mainService.addComment(commentModel);
			return ResponseEntity
					.ok(new ResponseModel("comment added successfully.", successStatus, HttpStatus.OK.value(), null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseModel("invalid request", errorStatus, HttpStatus.BAD_REQUEST.value(), null));
		}
	}

	/**
	 * this API is used to get comment of user.
	 * 
	 * @param comment to
	 * @return list of all the comments that are available for the given user.
	 * @throws InvalidRequestException if there's an issue adding the comment.
	 */
	@GetMapping("/comment/get")
	public ResponseEntity<ResponseModel> getComment(@RequestBody(required = false) CommentModel commentModel) {
		try {
			if (commentModel == null || commentModel.getCommentTo() == null || commentModel.getCommentTo().equals("")) {
				throw new InvalidRequestException("invalid request");
			}
			List<Map<String, String>> data = mainService.getComment(commentModel.getCommentTo());
			return ResponseEntity
					.ok(new ResponseModel("comment fetched by user", successStatus, HttpStatus.OK.value(), data));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseModel("invalid request", errorStatus, HttpStatus.BAD_REQUEST.value(), null));
		}
	}
}
