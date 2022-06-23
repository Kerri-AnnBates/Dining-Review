package com.bates.diningreview.repositories;

import com.bates.diningreview.models.DiningReview;
import com.bates.diningreview.models.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findByStatusIs(Status status);
    List<DiningReview> findByRestaurantIdAndStatusIs(Long id, Status status);
}
