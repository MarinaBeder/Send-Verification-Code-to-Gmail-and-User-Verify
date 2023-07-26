package com.email.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	User findByVerficationCode(String verficationCode);

}
