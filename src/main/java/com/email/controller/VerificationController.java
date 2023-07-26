package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.service.EmailVerficationService;

@Controller
@RequestMapping("/verify")

public class VerificationController {
	@Autowired
	private EmailVerficationService emailVerificationService;

	@GetMapping("")
	public String verifyAccount(@Param("code") String code) {

		return emailVerificationService.verfiy(code);
	}

}
