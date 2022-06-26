package com.bates.diningreview.controllers;

import com.bates.diningreview.models.AdminReview;
import com.bates.diningreview.models.DiningReview;
import com.bates.diningreview.models.Restaurant;
import com.bates.diningreview.models.Status;
import com.bates.diningreview.repositories.DiningReviewRepository;
import com.bates.diningreview.repositories.RestaurantRepository;
import com.bates.diningreview.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public AdminController(DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    // As an admin, I want to approve or reject a given dining review.
    @PutMapping("/review/{reviewId}")
    public DiningReview updateReview(@PathVariable Long reviewId, @RequestBody AdminReview adminReview) {
        Optional<DiningReview> reviewOpt = diningReviewRepository.findById(reviewId);

        if (reviewOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }

        DiningReview reviewToUpdate = reviewOpt.get();

        // Update restaurant scores
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(reviewToUpdate.getRestaurantId());
        Restaurant restaurant = restaurantOpt.get();

        if (adminReview.getAccepted()) {
            reviewToUpdate.setStatus(Status.ACCEPTED);

            if (reviewToUpdate.getDairyScore() != null) {
                Integer score = (reviewToUpdate.getDairyScore() + restaurant.getDairyScore()) / 2;
                reviewToUpdate.setDairyScore(score);
            }

            if (reviewToUpdate.getEggScore() != null) {
                Integer score = (reviewToUpdate.getEggScore() + restaurant.getEggScore()) / 2;
                reviewToUpdate.setEggScore(score);
            }

            if (reviewToUpdate.getPeanutScore() != null) {
                Integer score = (reviewToUpdate.getPeanutScore() + restaurant.getPeanutScore()) / 2;
                reviewToUpdate.setPeanutScore(score);
            }

            Integer overAllScore = (restaurant.getOverallScore() +
                                    restaurant.getEggScore() +
                                    restaurant.getDairyScore() +
                                    restaurant.getPeanutScore()) / 4;

            restaurant.setOverallScore(overAllScore);

        } else {
            reviewToUpdate.setStatus(Status.REJECTED);
        }

        return diningReviewRepository.save(reviewToUpdate);
    }

    // Get pending reviews
    @GetMapping("/review/pending")
    public List<DiningReview> getPendingReviews() {
        return diningReviewRepository.findByStatusIs(Status.PENDING);
    }

}
