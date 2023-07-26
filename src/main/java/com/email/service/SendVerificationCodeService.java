package com.email.service;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.domain.User;
import com.email.repository.UserRepository;

//import net.bytebuddy.utility.RandomString;
@Service

public class SendVerificationCodeService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JavaMailSender mailSender;

	public void sendVerificationEmail(User user, String siteURL)
			throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		String subject = "Please verify your registeration";
		String senderName = "Our Team";
		String mailContent = "<p>Dear " + user.getUsername() + ",</p>";
		mailContent += "<p>please click the line below to verify to your registeration: </p>";
		String verifyURL = siteURL + "http://localhost:8080/verify?code=" + user.getVerficationCode();//you can change port
		mailContent += "<h3><a href=\"" + verifyURL + "\">VERIFY</a></h3>";
		mailContent += "<p>Thank you<br>Our Team</p>";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("{ put gmail that will send message }", senderName);
		helper.setTo(user.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		mailSender.send(message);
		user.setEnable(false);
		;
		userRepo.save(user);
	}

	public String saveUse(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
		Optional<User> name = Optional.empty();
		name = userRepo.findByEmail(user.getEmail());

		if (!name.isPresent()) {

			String randomCode = generateAlphaNumericString(64);
			user.setVerficationCode(randomCode);
			userRepo.save(user);
			sendVerificationEmail(user, siteURL);

			return "We send a link to your email, Please verify it";
		}
		if (name.isPresent() && !name.get().isEnable()) {
			return "Please verify your email";
		} else
			return "Email already exists";

	}

	// Create a String of the characters which can be included in the string
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXY"
			+ "Zabcdefghijklmnopqrstuvwxyz0123456789";

	// Method to generate Random AlphaNumeric String
	public static String generateAlphaNumericString(int length) {

		SecureRandom random = new SecureRandom();
		StringBuilder builder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			builder.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
		}

		return builder.toString();
	}
}
