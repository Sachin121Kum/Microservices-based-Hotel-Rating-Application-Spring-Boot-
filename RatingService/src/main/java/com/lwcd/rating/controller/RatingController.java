package com.lwcd.rating.controller;

import com.lwcd.rating.entity.Rating;
import com.lwcd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){

            return  ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));

    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        return  ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelid(@PathVariable String hotelId){
        return  ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }


}
