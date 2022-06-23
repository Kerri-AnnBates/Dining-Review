package com.bates.diningreview.controllers;

import com.bates.diningreview.models.DiningReview;
import com.bates.diningreview.repositories.DiningReviewRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class DiningReviewController {
    private final DiningReviewRepository diningReviewRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository) {
        this.diningReviewRepository = diningReviewRepository;
    }

    // Allow user to submit a review
    @PostMapping("")
    public DiningReview addReview(@RequestBody DiningReview newReview) {
        return diningReviewRepository.save(newReview);
    }
}
