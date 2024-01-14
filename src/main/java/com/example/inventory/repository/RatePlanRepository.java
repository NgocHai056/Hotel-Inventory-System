package com.example.inventory.repository;

import com.example.inventory.entity.RatePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatePlanRepository extends JpaRepository<RatePlan, Long> {

//    List<RatePlan> findByRoomType_Id(Long roomTypeId);
}
