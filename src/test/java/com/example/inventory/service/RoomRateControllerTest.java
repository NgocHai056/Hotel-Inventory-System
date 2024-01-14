package com.example.inventory.service;

import com.example.inventory.common.BaseResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoomRateControllerTest {

    @Autowired
    private RoomService roomService;

    @Test
    public void getRoomRatesAndAvailability_validRequest() throws Exception {

        BaseResponse<Object> response = roomService.getRoomRateAndAvailability(1L, "2024-01-01", "2024-01-31");

        assertEquals(200, response.getStatus());
        assertTrue(response.getData() != null);
    }

    @Test
    public void getRoomRatesAndAvailability_missingHotelId() throws Exception {
        BaseResponse<Object> response = roomService.getRoomRateAndAvailability(null, "2024-01-01", "2024-01-31");

        assertEquals(200, response.getStatus());
    }


    @Test
    public void getRoomRatesAndAvailability_invalidDateFormat() throws Exception {
        BaseResponse<Object> response = roomService.getRoomRateAndAvailability(1L, "2024-01-30", "2024-01-31");

        assertEquals(200, response.getStatus());
        assertTrue(response.getData() != null);
        assertEquals(response.getMessage(),  "Start date must be in the format YYYY-MM-DD");
    }

    @Test
    public void getRoomRatesAndAvailability_endDateBeforeStartDate() throws Exception {
        BaseResponse<Object> response = roomService.getRoomRateAndAvailability(1L, "2024-01-15", "2024-01-10");

        assertEquals(200, response.getStatus());
        assertTrue(response.getData() != null);
    }

}
