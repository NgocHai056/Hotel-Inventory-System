package com.example.inventory.dto;

import lombok.Data;

@Data
public class UpdateInventory {
    private int type;

    private Long roomAvailable;
}
