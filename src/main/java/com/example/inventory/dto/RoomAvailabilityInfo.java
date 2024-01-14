package com.example.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomAvailabilityInfo {
    private Long roomTypeId;
    private int availableRooms;

}