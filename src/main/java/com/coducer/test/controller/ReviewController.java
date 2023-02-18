package com.coducer.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coducer.test.entity.Review;
import com.coducer.test.jwtauth.JwtUtils;
import com.coducer.test.service.ReviewService;

@RestController
@RequestMapping("/service/v1/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/{id}")
	public ResponseEntity<List<String>> getReviewById(@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable("id") Long id) {

		try {
			String token = jwtUtils.getBearerToken(authorizationHeader);

			if (!jwtUtils.validateJwtToken(token) || (id != Long.parseLong(jwtUtils.getUserIdFromJwtToken(token)))) {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

			}

			List<String> reviews = reviewService.findAllReviewByUserId(id);

			if (reviews == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(reviews);

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> addReview(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody Review review) {
		try {

			String token = jwtUtils.getBearerToken(authorizationHeader);

			if (!jwtUtils.validateJwtToken(token)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			long id = Long.parseLong(jwtUtils.getUserIdFromJwtToken(token));

			reviewService.saveReview(id, review.getReview());

			return ResponseEntity.ok("Review created Successfully..");

		} catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping()
	public ResponseEntity<String> deleteReview(@RequestHeader("Authorization") String authorizationHeader) {
		try {

			String token = jwtUtils.getBearerToken(authorizationHeader);

			if (!jwtUtils.validateJwtToken(token)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			long id = Long.parseLong(jwtUtils.getUserIdFromJwtToken(token));

			System.out.println(id);

			reviewService.deleteByUserId(id);

			return ResponseEntity.ok("Review Deleted Successfully..");

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
