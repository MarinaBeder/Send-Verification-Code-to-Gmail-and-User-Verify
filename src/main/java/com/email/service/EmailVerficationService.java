package com.email.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.email.domain.User;
import com.email.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class EmailVerficationService {
	@Autowired
	private UserRepository userRepo;

	public String verfiy(String Verfication_code) {
		User user;
		try {
			user = userRepo.findByVerficationCode(Verfication_code);
		} catch (Exception ex) {
			return "invalidVerfiaction.html";
		}

		if (user == null) {
			System.out.println("no");
			return "invalidVerfiaction.html";
		} else if (user.isEnable()) {
			return "alreadyVerified.html";
		} else {
			user.setEnable(true);
			;
			userRepo.save(user);
			return "congratulation.html";
		}

	}
}
