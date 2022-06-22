package com.car.parking.controllers;

import com.car.parking.model.CarParkingDetails;
import com.car.parking.services.CarParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CarParkingController {

    @Autowired
    CarParkingService carParkingService;

    @GetMapping(path = "/carparks/nearest", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CarParkingDetails> getNearestCarParking(@RequestParam(name = "latitude") String latitude, @RequestParam(name = "longitude") String longitude,
                                                        @RequestParam(name = "page", defaultValue = "0") String page,@RequestParam(name = "per_page", defaultValue = "999") String perPage){
        if (latitude == null || longitude == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return carParkingService.getNearestCarParking(latitude,longitude, page,perPage);
    }
}
