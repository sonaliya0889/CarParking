package com.car.parking.config;
 
import com.car.parking.model.CarParking;
import org.springframework.batch.item.ItemProcessor;

public class CarParkingProcessor implements ItemProcessor<CarParking, CarParking>
{
    public CarParking process(CarParking carParking) throws Exception {
        //System.out.println("Inserting CarParking : " + carParking);
        return carParking;
    }
}