package com.bates.diningreview.controllers;

import com.bates.diningreview.models.AdminReview;
import com.bates.diningreview.models.DiningReview;
import com.bates.diningreview.models.Restaurant;
import com.bates.diningreview.models.Status;
import com.bates.diningreview.repositories.DiningReviewRepository;
import com.bates.diningreview.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class DiningReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Allow user to submit a review
    @PostMapping("")
    public DiningReview addReview(@RequestBody DiningReview newReview) {
        Optional<Restaurant> restOpt = restaurantRepository.findById(newReview.getRestaurantId());

        if (restOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This restaurant does not exists");
        }

        return diningReviewRepository.save(newReview);
    }

    // As an admin, I want to approve or reject a given dining review.
    @PutMapping("admin/review/{reviewId}")
    public DiningReview updateReview(@PathVariable Long reviewId, @RequestBody AdminReview adminReview) {
        Optional<DiningReview> reviewOpt = diningReviewRepository.findById(reviewId);

        if (reviewOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }

        DiningReview reviewToUpdate = reviewOpt.get();

        if (adminReview.getAccepted()) {
            reviewToUpdate.setStatus(Status.ACCEPTED);
        } else {
            reviewToUpdate.setStatus(Status.REJECTED);
        }

        return diningReviewRepository.save(reviewToUpdate);
    }

    // Get pending reviews
    @GetMapping("admin/review/pending")
    public List<DiningReview> getPendingReviews() {
        return diningReviewRepository.findByStatusIs(Status.PENDING);
    }

    // Get approved reviews per restaurant
    @GetMapping("/accepted/{restaurantId}")
    public List<DiningReview> getApprovedReviewsByRestaurantId(Long restaurantId) {
        return diningReviewRepository.findByRestaurantIdAndStatusIs(restaurantId, Status.ACCEPTED);
    }

    @GetMapping("/{reviewId}")
    public DiningReview getReviewById(@PathVariable Long reviewId) {
        Optional<DiningReview> reviewOpt = diningReviewRepository.findById(reviewId);

        if (reviewOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }

        return reviewOpt.get();
    }
}
