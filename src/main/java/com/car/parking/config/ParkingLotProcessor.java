package com.car.parking.config;
 
import com.car.parking.model.CarParking;
import com.car.parking.model.ParkingLot;
import org.springframework.batch.item.ItemProcessor;

public class ParkingLotProcessor implements ItemProcessor<ParkingLot, ParkingLot>
{
    public ParkingLot process(ParkingLot parkingLot) throws Exception {
        //System.out.println("Inserting ParkingLot : " + parkingLot);
        return parkingLot;
    }
}