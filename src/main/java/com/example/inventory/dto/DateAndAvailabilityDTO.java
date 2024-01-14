package com.example.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DateAndAvailabilityDTO {
    private LocalDate date;

    private BigDecimal price;

    private Integer availableRooms;
}
