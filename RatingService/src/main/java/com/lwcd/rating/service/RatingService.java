package com.lwcd.rating.service;

import com.lwcd.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    //create rating
    Rating create(Rating rating);


    List<Rating> getRatings();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);


}
