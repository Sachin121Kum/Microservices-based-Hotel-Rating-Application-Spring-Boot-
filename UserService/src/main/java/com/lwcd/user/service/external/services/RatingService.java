package com.lwcd.user.service.external.services;

import com.lwcd.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    public Rating createRating(Rating values);


    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);


    @DeleteMapping("/rating/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);

}
