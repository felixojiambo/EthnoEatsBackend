package com.ethnoeats.restaurant.repository;

import com.ethnoeats.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCuisine(String cuisine);
}
