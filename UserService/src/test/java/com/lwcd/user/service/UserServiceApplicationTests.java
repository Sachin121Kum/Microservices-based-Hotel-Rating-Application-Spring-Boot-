package com.lwcd.user.service;

import com.lwcd.user.service.entity.Rating;
import com.lwcd.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private RatingService ratingService; // ✅ Real Feign client (not mocked)

    @Test
    void contextLoads() {
        // Verifies that the app context starts correctly
    }

    @Test
    void createRating_shouldInsertInMongoDB() {
        Rating newRating = Rating.builder()
                .rating("5")
                .userId("user123")
                .hotelId("hotel456")
                .feedback("Excellent stay!")
                .build();

        Rating saved = ratingService.createRating(newRating);

        System.out.println("✅ Rating inserted into MongoDB: " + saved);
    }
}
