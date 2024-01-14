package com.example.inventory.service;

import com.example.inventory.common.BaseResponse;
import com.example.inventory.common.JmsMessageHandler;
import com.example.inventory.dto.UpdateInventory;
import com.example.inventory.entity.RoomAvailability;
import com.example.inventory.entity.RoomRate;
import com.example.inventory.entity.RoomType;
import com.example.inventory.model.RoomRatesAndAvailability;
import com.example.inventory.repository.RoomAvailabilityRepository;
import com.example.inventory.repository.RoomRateRepository;
import com.example.inventory.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomRateRepository roomRateRepository;

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    public BaseResponse<Object> getRoomRateAndAvailability(Long hotelId, String startDate, String endDate) throws Exception {
        BaseResponse<Object> response = new BaseResponse<>();

        List<RoomRatesAndAvailability> result = new ArrayList<>();
        List<RoomAvailability> roomAvailabilities = new ArrayList<>();
        List<RoomRate> roomRates = new ArrayList<>();

        // Get list room base on hotel id
        List<RoomType> roomTypes = roomTypeRepository.findByHotelId(hotelId);


        for (RoomType roomType : roomTypes) {
            roomAvailabilities.addAll(roomAvailabilityRepository.
                    findByRoomTypeIdAndDateBetween(roomType.getRoomTypeId(), LocalDate.parse(startDate), LocalDate.parse(endDate)));
            roomRates.addAll(roomRateRepository.findByRoomTypeAndDate(roomType.getRoomTypeId(), LocalDate.parse(startDate), LocalDate.parse(endDate)));

        }
        response.setData(RoomRatesAndAvailability.mapToRoomRatesAndAvailability(roomAvailabilities, roomRates));
        return response;

    }


    @JmsListener(destination = "Inventory.Topic")
    public void updateRoomAvaiable(final Message jsonMessage) throws Exception {

        if (jsonMessage instanceof TextMessage) {

            UpdateInventory updateInventory = JmsMessageHandler.convertMessageToObject(jsonMessage, UpdateInventory.class);

            RoomAvailability roomAvailable = roomAvailabilityRepository.findById(updateInventory.getRoomAvailable()).get();

            // type = 1: cập nhật thông tin phòng chồng, type = 2 hủy phòng
            if(updateInventory.getType() == 1){

                roomAvailable.setAvailableRooms(roomAvailable.getAvailableRooms() + 1);
                roomAvailabilityRepository.save(roomAvailable);

            }else {
                roomAvailable.setAvailableRooms(roomAvailable.getAvailableRooms() + 1);
                roomAvailabilityRepository.save(roomAvailable);
            }

        }

    }
}
