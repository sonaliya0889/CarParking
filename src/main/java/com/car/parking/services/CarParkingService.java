package com.car.parking.services;

import com.car.parking.dao.CarParkingDAO;
import com.car.parking.model.CarParkingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParkingService {

    @Autowired
    CarParkingDAO carParkingDAO;

    public List<CarParkingDetails> getNearestCarParking(String latitude, String longitude, String page, String perPage){
        return carParkingDAO.getNearestCarParking(latitude,longitude,page, perPage);
    }
}
