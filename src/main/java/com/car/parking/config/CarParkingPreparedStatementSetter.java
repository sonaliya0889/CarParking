package com.car.parking.config;

import com.car.parking.model.CarParking;
import com.car.parking.model.Coordinates;
import com.car.parking.util.SVY21ToWGS84Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

final public class CarParkingPreparedStatementSetter implements ItemPreparedStatementSetter<CarParking> {
    private static final Log logger = LogFactory.getLog(CarParkingPreparedStatementSetter.class);

    @Override
    public void setValues(CarParking carParking, PreparedStatement preparedStatement) throws SQLException {
        logger.debug(carParking.toString());
        preparedStatement.setString(1, carParking.getCarParkNo());
        preparedStatement.setString(2, carParking.getAddress());

        Coordinates coordinates = SVY21ToWGS84Converter.convert(carParking.getxCoord(),carParking.getyCoord());
        logger.debug("X: "+coordinates.getLatitude() +" Y: "+coordinates.getLongitude());
        preparedStatement.setString(3, coordinates.getLatitude());
        preparedStatement.setString(4, coordinates.getLongitude());

        preparedStatement.setString(5, carParking.getCarParkType());
        preparedStatement.setString(6, carParking.getTypeOfParkingSystem());
        preparedStatement.setString(7, carParking.getShortTermParking());
        preparedStatement.setString(8, carParking.getFreeParking());
        preparedStatement.setString(9, carParking.getNightParking());
        preparedStatement.setString(10, carParking.getCarParkDecks());
        preparedStatement.setString(11, carParking.getGantryHeight());
        preparedStatement.setString(12, carParking.getCarParkBasement());
    }
}
