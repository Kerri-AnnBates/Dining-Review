package com.bates.diningreview.controllers;

import com.bates.diningreview.models.Restaurant;
import com.bates.diningreview.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("")
    public Restaurant addNewRestaurant(@RequestBody Restaurant newRestaurant) {
        Restaurant restaurant = restaurantRepository.findByNameAndZipCode(newRestaurant.getName(), newRestaurant.getZipCode());

        if (restaurant != null) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Restaurant already exists.");
        }

        return restaurantRepository.save(newRestaurant);
    }

    @GetMapping("/{restaurantId}")
    public Restaurant getRestaurantDetails(@PathVariable Long restaurantId) {
        Optional<Restaurant> restOpt = restaurantRepository.findById(restaurantId);

        if (restOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found by id" + restaurantId);
        }

        return restOpt.get();
    }

    @GetMapping("/")
    public List<Restaurant> getRestaurantsByZipcodeAndAllergyScores(@RequestParam String zipcode,
                                                                    @RequestParam(required = false, defaultValue = "false") Boolean peanutAllergy,
                                                                    @RequestParam(required = false, defaultValue = "false") Boolean diaryAllergy,
                                                                    @RequestParam(required = false, defaultValue = "false") Boolean eggAllergy) {
        List<Restaurant> restaurants = new ArrayList<>();

        if (peanutAllergy) {
          restaurants = restaurantRepository.findByZipCodeAndPeanutScoreNotNullOrderByZipCodeDesc(zipcode);
        } else if (diaryAllergy) {
            restaurants = restaurantRepository.findByZipCodeAndDairyScoreNotNullOrderByZipCodeDesc(zipcode);
        } else if (eggAllergy) {
            restaurants = restaurantRepository.findByZipCodeAndEggScoreNotNullOrderByZipCodeDesc(zipcode);
        }


        return restaurants;
    }
}
