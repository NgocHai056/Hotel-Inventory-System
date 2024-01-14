package com.example.inventory.controller;

import com.example.inventory.common.RequestMappingVersionConstants;
import com.example.inventory.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "rooms" + RequestMappingVersionConstants.VERSION)
@RequestMapping(value = RequestMappingVersionConstants.VERSION_PATH + "/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> get(@RequestParam Long hotelId,
                                 @RequestParam String startDate,
                                 @RequestParam String endDate) throws Exception {


        return new ResponseEntity<>(roomService.getRoomRateAndAvailability(hotelId, startDate, endDate), HttpStatus.OK);
    }

}
