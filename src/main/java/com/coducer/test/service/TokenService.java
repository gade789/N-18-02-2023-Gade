package com.coducer.test.service;

import com.coducer.test.entity.User;

public interface TokenService {

	public User findByUsername(String username);

	public User findByUsernameAndPassword(String username, String password);

}
