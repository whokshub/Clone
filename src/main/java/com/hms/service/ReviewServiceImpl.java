package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.entity.Property;
import com.hms.entity.Review;
import com.hms.payload.ReviewDto;
import com.hms.repository.PropertyRepository;
import com.hms.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDto writeReview(Long id, Review review, AppUser appUser) {
        Property property = propertyRepository.findById(id).get();
        review.setProperty(property);
        review.setAppUser(appUser);

        Review saved = reviewRepository.save(review);
        ReviewDto reviewDto = mapToDto(saved);
        return reviewDto;
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public ReviewDto updateReview(Review existingReview) {
        Review saved = reviewRepository.save(existingReview);

        ReviewDto reviewDto = mapToDto(saved);
        return reviewDto;
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }


    Review mapToEntity (ReviewDto reviewDto){
        Review review = modelMapper.map(reviewDto, Review.class);
        return review;
    }

    ReviewDto mapToDto (Review review){
        ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
        return reviewDto;
    }

}
