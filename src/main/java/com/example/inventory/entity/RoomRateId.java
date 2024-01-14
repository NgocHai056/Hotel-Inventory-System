package com.example.inventory.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RoomRateId implements Serializable {

    private Integer roomType;
    private Integer ratePlan;
    private LocalDate date;

}
