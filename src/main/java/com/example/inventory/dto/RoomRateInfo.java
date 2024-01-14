package com.example.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RoomRateInfo {
    private Long roomTypeId;
    private Long ratePlanId;
    private BigDecimal price;
}