package com.ethnoeats.restaurant.service;

import com.ethnoeats.restaurant.model.Restaurant;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    List<Restaurant> getAllRestaurants();

    Optional<Restaurant> getRestaurantById(Long id);

    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Long id);

    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Long id, Restaurant restaurant) throws ChangeSetPersister.NotFoundException;
}
