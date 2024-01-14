package com.example.inventory.model;

import com.example.inventory.dto.RoomAvailabilityInfo;
import com.example.inventory.dto.RoomRateInfo;
import com.example.inventory.entity.RoomAvailability;
import com.example.inventory.entity.RoomRate;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class RoomRatesAndAvailability {

    private LocalDate date;
    private List<RoomAvailabilityInfo> roomAvailabilityInfo;
    private List<RoomRateInfo> roomRateInfo;

    public static List<RoomRatesAndAvailability> mapToRoomRatesAndAvailability(
            List<RoomAvailability> roomAvailabilities, List<RoomRate> roomRates) {

        // Kết hợp hai danh sách thành một danh sách chung
        List<Object> combinedList = new ArrayList<>();
        combinedList.addAll(roomAvailabilities);
        combinedList.addAll(roomRates);

        // Nhóm dữ liệu theo trường date
        Map<LocalDate, List<Object>> groupedByDate = combinedList.stream()
                .collect(Collectors.groupingBy(obj -> {
                    if (obj instanceof RoomAvailability) {
                        return ((RoomAvailability) obj).getDate();
                    } else if (obj instanceof RoomRate) {
                        return ((RoomRate) obj).getDate();
                    }
                    return null;
                }));

        // Tạo đối tượng RoomRatesAndAvailability cho mỗi nhóm
        return groupedByDate.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<Object> groupedObjects = entry.getValue();

                    List<RoomAvailabilityInfo> availabilityList = groupedObjects.stream()
                            .filter(obj -> obj instanceof RoomAvailability)
                            .map(obj -> {
                                RoomAvailability roomAvailability = (RoomAvailability) obj;
                                return new RoomAvailabilityInfo(
                                        roomAvailability.getRoomType().getRoomTypeId(),
                                        roomAvailability.getAvailableRooms());
                            })
                            .collect(Collectors.toList());

                    List<RoomRateInfo> rateList = groupedObjects.stream()
                            .filter(obj -> obj instanceof RoomRate)
                            .map(obj -> {
                                RoomRate roomRate = (RoomRate) obj;
                                return new RoomRateInfo(
                                        roomRate.getRoomType().getRoomTypeId(),
                                        roomRate.getRatePlan().getRatePlanId(),
                                        roomRate.getPrice());
                            })
                            .collect(Collectors.toList());

                    RoomRatesAndAvailability result = new RoomRatesAndAvailability();
                    result.setDate(date);
                    result.setRoomAvailabilityInfo(availabilityList);
                    result.setRoomRateInfo(rateList);

                    return result;
                })
                .collect(Collectors.toList());
    }
}

