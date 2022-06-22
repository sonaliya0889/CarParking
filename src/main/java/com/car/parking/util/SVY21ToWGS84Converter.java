package com.car.parking.util;

import com.car.parking.model.Coordinates;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Map;

public class SVY21ToWGS84Converter {
    private static final Log logger = LogFactory.getLog(SVY21ToWGS84Converter.class);

    public static Coordinates convert(String x, String y) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(CarParkingConstants.SVY21_TO_WGS84_URL, String.class, x, y);
        logger.debug("After Conversion SVY21_TO_WGS84 "+ result);

        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,String>>() {}.getType();
        Map<String,String> map = gson.fromJson(result,type);

        Coordinates coordinates = new Coordinates(map.get("X"), map.get("Y"));
        return coordinates;
    }
}
