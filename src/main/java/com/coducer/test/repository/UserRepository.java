package com.coducer.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coducer.test.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsernameAndPassword(String username, String password);

	public User findByUsername(String username);

	public User findById(long id);

}
