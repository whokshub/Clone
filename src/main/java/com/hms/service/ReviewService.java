package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Review;
import com.hms.payload.ReviewDto;

public interface ReviewService {

    public ReviewDto writeReview(Long id, Review review, AppUser appUser);

    Review getReview(Long id);

    ReviewDto updateReview(Review existingReview);

    void deleteReview(Long id);
}
