package com.car.parking.dao;

import com.car.parking.model.CarParkingDetails;
import com.car.parking.util.CarParkingConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarParkingDAO {

    private static final Log logger = LogFactory.getLog(CarParkingDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<CarParkingDetails> getNearestCarParking(String latitude, String longitude,String page, String perPage){
        String query = CarParkingConstants.QUERY_NEAREST_SEARCH.replace("LATITUDE",latitude)
                .replace("LONGITUDE",longitude)
                .replace("PERPAGE",perPage)
                .replace("PAGE",page);
        logger.debug("latitude: "+latitude+" longitude: "+longitude+" Offset: "+page+" Limit: "+perPage);

        return jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new CarParkingDetails(
                                rs.getString("ADDRESS"),
                                rs.getString("XCOORD"),
                                rs.getString("YCOORD"),
                                rs.getInt("TOTALLOTS"),
                                rs.getInt("AVILABLELOTS")
                        )
        );
    }
}
