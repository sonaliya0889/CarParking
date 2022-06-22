package com.car.parking.config;

import com.car.parking.model.ParkingLot;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParkingLotPreparedStatementSetter implements ItemPreparedStatementSetter<ParkingLot> {
    private static final Log logger = LogFactory.getLog(ParkingLotPreparedStatementSetter.class);

    @Override
    public void setValues(ParkingLot parkingLot, PreparedStatement preparedStatement) throws SQLException {
        logger.debug(parkingLot.toString());
        preparedStatement.setString(1, parkingLot.getCarparkNumber());
        preparedStatement.setInt(2, parkingLot.getTotalLots());
        preparedStatement.setInt(3, parkingLot.getAvailableLots());
        preparedStatement.setString(4, parkingLot.getLotType());
        preparedStatement.setString(5, parkingLot.getUpdateDatetime());
    }
}
