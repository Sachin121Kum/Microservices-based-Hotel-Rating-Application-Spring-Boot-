package com.lcwd.hotel.repositry;

import com.lcwd.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepositry extends JpaRepository<Hotel, String> {
}

