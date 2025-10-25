package com.lcwd.hotel.service;

import com.lcwd.hotel.entity.Hotel;

import java.util.List;


public interface HotelService {

    //create
    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);



}
