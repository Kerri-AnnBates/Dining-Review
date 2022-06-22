package com.bates.diningreview.repositories;

import com.bates.diningreview.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
