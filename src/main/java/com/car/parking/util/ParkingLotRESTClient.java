package com.car.parking.util;

import com.car.parking.model.ParkingLot;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class ParkingLotRESTClient {
    private static final Log logger = LogFactory.getLog(ParkingLotRESTClient.class);

    public static List<ParkingLot> read() {
        RestTemplate restTemplate = new RestTemplate();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        String result = restTemplate.getForObject(CarParkingConstants.PARKING_AVAILABILITY_URL, String.class);
        logger.trace("PARKING AVAILABILITY RESPONSE");
        logger.trace(result);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            JSONObject itemsArray = (JSONObject) jsonObject.getJSONArray("items").get(0);
            JSONArray carParkData = (JSONArray) itemsArray.get("carpark_data");
            for (int i = 0; i < carParkData.length(); i++) {
                JSONObject object = (JSONObject) carParkData.get(i);
                ParkingLot parkingLot = new ParkingLot();
                parkingLot.setCarparkNumber(String.valueOf(object.get("carpark_number")));
                parkingLot.setUpdateDatetime(String.valueOf(object.get("update_datetime")));

                JSONObject carparkInfo = (JSONObject) object.getJSONArray("carpark_info").get(0);
                parkingLot.setTotalLots(Integer.parseInt(String.valueOf(carparkInfo.get("total_lots"))));
                parkingLot.setAvailableLots(Integer.parseInt(String.valueOf(carparkInfo.get("lots_available"))));
                parkingLot.setLotType(String.valueOf(carparkInfo.get("lot_type")));
                parkingLotList.add(parkingLot);
                logger.debug(parkingLot.toString());
            }
        } catch (JSONException e) {
            logger.error("Exception While Parsing Parking Availability Response.");
            e.printStackTrace();
        }

        return parkingLotList;
    }
}
