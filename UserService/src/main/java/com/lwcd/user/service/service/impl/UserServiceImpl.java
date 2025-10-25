package com.lwcd.user.service.service.impl;

import com.lwcd.user.service.entity.Hotel;
import com.lwcd.user.service.entity.Rating;
import com.lwcd.user.service.entity.User;
import com.lwcd.user.service.exception.ResourceNotFOundException;
import com.lwcd.user.service.external.services.HotelService;
import com.lwcd.user.service.repositories.UserRepository;
import com.lwcd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User SaveUser(User user) {
        String randomUUID = UUID.randomUUID().toString();
        user.setUserId(randomUUID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFOundException("User with given id is not found on server: " + userId));

        Rating[] ratingsOfUser = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/user/"+user.getUserId(),
                Rating[].class
        );
        System.out.println("sachinkumar-------------------------------");

        List<Rating> ratings =Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList= ratings.stream().map(rating->{
//            ResponseEntity<Hotel> forEntity =restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel= hotelService.getHotel(rating.getHotelId());
//            logger.info("Response from get Status Code: {}", forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;


        }).collect(Collectors.toList());

        logger.info("Response from ratings service: {}", ratingsOfUser);

        user.setRatings(ratingList);
        return user;
    }
}
