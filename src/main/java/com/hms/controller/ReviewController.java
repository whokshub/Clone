package com.hms.controller;

import com.hms.entity.AppUser;
import com.hms.entity.Review;
import com.hms.payload.ReviewDto;
import com.hms.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testing/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    //http://locahost:8080/api/testing/review/addreview
    @PostMapping("/addreview")
    public ResponseEntity<?> write(
            @RequestParam Long id,
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser)
    {
        ReviewDto reviewDto = reviewService.writeReview(id, review, appUser);

        return new ResponseEntity<>(reviewDto, HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReview(
            @RequestParam Long id,
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser
            ){
            Review existingReview = reviewService.getReview(id);
            if(existingReview==null){
                return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
            } else if (existingReview.getAppUser().getId()!=appUser.getId()) {
                return new ResponseEntity<>("This user cannot change the review", HttpStatus.UNAUTHORIZED);
            } else {
                existingReview.setRating(review.getRating());
                existingReview.setDescription(review.getDescription());

                ReviewDto reviewDto = reviewService.updateReview(existingReview);

                return new ResponseEntity<>(reviewDto, HttpStatus.OK);
            }

    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delReview(@RequestParam Long id, @AuthenticationPrincipal AppUser appUser){
        Review existingReview = reviewService.getReview(id);

        if (existingReview==null){
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }else if (existingReview.getAppUser().getId()!=appUser.getId()){
            return new ResponseEntity<>("This user cannot delete the review", HttpStatus.UNAUTHORIZED);
        }
        else {
            reviewService.deleteReview(id);
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }

    }

}
