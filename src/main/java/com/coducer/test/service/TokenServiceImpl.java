package com.coducer.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coducer.test.entity.User;
import com.coducer.test.repository.UserRepository;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {

		return userRepository.findByUsernameAndPassword(username, password);
	}

}
