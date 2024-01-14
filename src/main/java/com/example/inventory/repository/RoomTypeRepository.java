package com.example.inventory.repository;

import com.example.inventory.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    @Query("SELECT r FROM RoomType r WHERE r.hotel.hotelId = :hotelId")
    List<RoomType> findByHotelId(Long hotelId);
}
