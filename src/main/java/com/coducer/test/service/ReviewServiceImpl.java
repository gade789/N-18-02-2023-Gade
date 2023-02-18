package com.coducer.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coducer.test.entity.Review;
import com.coducer.test.entity.User;
import com.coducer.test.repository.ReviewRepository;
import com.coducer.test.repository.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<String> findAllReviewByUserId(Long id) {

		return reviewRepository.findAllReviewByUserId(id);
	}

	@Override
	@Transactional
	public Review saveReview(long id, String uReview) {

		User user = userRepository.findById(id);

		Review newReview = new Review();
		newReview.setReview(uReview);
		newReview.setUser(user);

		return reviewRepository.save(newReview);

	}

	@Override
	public void deleteByUserId(long id) {
		System.out.println(" service >>"+id);

		reviewRepository.deleteByUserId(id);
		System.out.println("deleted");
	}

}
