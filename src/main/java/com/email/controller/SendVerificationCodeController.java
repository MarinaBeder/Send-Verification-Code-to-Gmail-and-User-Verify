package com.email.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.domain.User;
import com.email.service.SendVerificationCodeService;
import com.email.util.utility;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/register-send-verfication-code")
public class SendVerificationCodeController {
	@Autowired
	SendVerificationCodeService sendVerificationCodeService;

	@PostMapping("")
	public String registerPost(@RequestBody User user, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {
		String siteURL = utility.getSiteUrl(request);
		String newuser = sendVerificationCodeService.saveUse(user, siteURL);
		return newuser;
	}

}
