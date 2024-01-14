package com.example.inventory.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RoomAvailabilityId implements Serializable {

    private Long roomType;
    private LocalDate date;

}
