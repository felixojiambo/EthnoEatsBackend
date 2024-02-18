package com.ethnoeats.restaurant.service;
import com.ethnoeats.restaurant.model.Restaurant;
import com.ethnoeats.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl  implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
    public Restaurant createRestaurant(Restaurant restaurant) {
        // Perform validation checks if needed
        // For example, ensure that required fields are not null

        // Save the restaurant
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurant) throws ChangeSetPersister.NotFoundException {
        // Find the existing restaurant by ID
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Update the existing restaurant with the new data
        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setCuisine(restaurant.getCuisine());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setCity(restaurant.getCity());
        existingRestaurant.setState(restaurant.getState());
        existingRestaurant.setCountry(restaurant.getCountry());
        existingRestaurant.setPostalCode(restaurant.getPostalCode());
        existingRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        existingRestaurant.setWebsite(restaurant.getWebsite());

        // Save the updated restaurant
        return restaurantRepository.save(existingRestaurant);
    }
}
