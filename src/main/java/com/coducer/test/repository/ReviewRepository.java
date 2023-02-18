package com.coducer.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coducer.test.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(value = "select review from review where user_id= ?1", nativeQuery = true)
	public List<String> findAllReviewByUserId(long id);

	@Modifying
	@Transactional
	@Query(value = "delete from review where user_id= ?1", nativeQuery = true)
	public int deleteByUserId(long id);

}
