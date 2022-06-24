package com.bates.diningreview.repositories;

import com.bates.diningreview.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Restaurant findByNameAndZipCode(String name, String zipcode);
    List<Restaurant> findByZipCodeAndPeanutScoreNotNullOrderByZipCodeDesc(String zipcode);
    List<Restaurant> findByZipCodeAndEggScoreNotNullOrderByZipCodeDesc(String zipcode);
    List<Restaurant> findByZipCodeAndDairyScoreNotNullOrderByZipCodeDesc(String zipcode);
}
