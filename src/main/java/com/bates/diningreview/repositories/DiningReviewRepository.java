package com.bates.diningreview.repositories;

import com.bates.diningreview.models.DiningReview;
import org.springframework.data.repository.CrudRepository;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
}
