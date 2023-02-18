package com.coducer.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(length = 1000)
  private String review;
  
  @ManyToOne
  private User user;
  
  public Long getId() {
		return id;
	}
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getReview() {
    return review;
  }

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public void setReview(String review) {
	this.review = review;
}
  
}
