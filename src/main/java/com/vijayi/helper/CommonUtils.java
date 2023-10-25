package com.vijayi.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vijayi.repository.CommentRepository;
import com.vijayi.repository.UserRepository;

@Component
public class CommonUtils {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private UserRepository userRepo;

	public String generateDateAndTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
		String date = dateTimeFormatter.format(now);
		return date;
	}

	public String generateUserId() {
		String lastGeneratedUserId = userRepo.findLastGeneratedUserId();
		if (lastGeneratedUserId == null) {
			return "USERID1";
		} else {
			int num = Integer.parseInt(lastGeneratedUserId.replace("USERID", ""));
			return "USERID" + (num + 1);
		}
	}

	public String generateCommentId(String userId) {
		String lastGeneratedCommentId = commentRepo.findLastGeneratedCommentId();
		if (lastGeneratedCommentId == null) {
			return "COMMENTID1";
		} else {
			int num = Integer.parseInt(lastGeneratedCommentId.replace("COMMENTID", ""));
			return "COMMENTID" + (num + 1);
		}
	}
}
