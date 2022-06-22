package com.bates.diningreview.repositories;

import com.bates.diningreview.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findByZipcodeAndPeanutScoreGreaterThanEqual(String zip, Integer score);
    List<Restaurant> findByZipcodeAndEggScoreGreaterThanEqual(String zip, Integer score);
    List<Restaurant> findByZipcodeAndDairyScoreGreaterThanEqual(String zip, Integer score);
}
