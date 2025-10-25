package com.lwcd.rating.repositry;

import com.lwcd.rating.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepo extends MongoRepository<Rating, String> {

    //custom finder method;

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);


}
