package com.example.inventory.dto;

import com.example.inventory.entity.RatePlan;
import com.example.inventory.entity.RoomType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class RoomRateAndAvailabilityDTO {
    private Long roomTypeId;

    private Long ratePlanId;

    private List<DateAndAvailabilityDTO> dates;
}
