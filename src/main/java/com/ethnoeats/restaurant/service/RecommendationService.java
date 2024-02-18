package com.ethnoeats.restaurant.service;


import com.ethnoeats.restaurant.model.Restaurant;
import com.ethnoeats.restaurant.repository.RestaurantRepository;
import com.ethnoeats.user.model.User;
import com.ethnoeats.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

    @Service
    public class RecommendationService {

        @Autowired
        private RestaurantRepository restaurantRepository;

        @Autowired
        private UserRepository userRepository;

        public List<Restaurant> getRecommendations(String userId) {
            // Retrieve the user's favorite cuisine
            User user = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            String favoriteCuisine = user.getFavoriteCuisine();

            // Fetch restaurants that match the user's favorite cuisine
            List<Restaurant> recommendations = restaurantRepository.findByCuisine(favoriteCuisine);

            return recommendations;
        }
    }


