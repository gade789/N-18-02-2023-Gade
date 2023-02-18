package com.coducer.test.service;

import java.util.List;

import com.coducer.test.entity.Review;

public interface ReviewService {

	public List<String> findAllReviewByUserId(Long id);

	public Review saveReview(long id, String uReview);

	public void deleteByUserId(long id);
}
