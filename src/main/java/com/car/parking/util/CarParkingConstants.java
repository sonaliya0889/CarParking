package com.car.parking.util;

public class CarParkingConstants {

     public static final String SVY21_TO_WGS84_URL = "https://developers.onemap.sg/commonapi/convert/3414to3857?X={x}&Y={y}";
     public static final String PARKING_AVAILABILITY_URL = "https://api.data.gov.sg/v1/transport/carpark-availability";
     public static final String QUERY_INSERT_CARPARKING = "REPLACE INTO CARPARKING (CARPARKNO, ADDRESS, XCOORD, YCOORD, " +
        "CARPARKTYPE, TYPEOFPARKINGSYS, SHORTTERMPARKING, FREEPARKING, NIGHTPARKING, PARKINGDECKS, GANTRYHEIGHT,PARKINGBASEMENT) " +
        "VALUES (?, ?, ?, ?,?,?,?,?,?,?,?,?)";
     public static final String QUERY_INSERT_PARKINGLOT = "REPLACE INTO PARKINGLOT (CARPARKNO, TOTALLOTS, AVILABLELOTS, LOTTYPE, UPDATEDATETIME) VALUES (?, ?, ?, ?, ?)";
     //here 111.2 constant is denoting km/degree and 57.3 for value of pi/180 degree
     public static final String QUERY_NEAREST_SEARCH = "SELECT ADDRESS, TOTALLOTS,AVILABLELOTS, XCOORD, YCOORD, SQRT(\n" +
             "    POWER(111.2 * (ROUND(XCOORD)- LATITUDE), 2) +\n" +
             "    POWER(111.2 * (LONGITUDE-ROUND(YCOORD)) * COS(XCOORD / 57.3), 2)) AS DISTANCE FROM CARPARKING \n" +
             "INNER JOIN PARKINGLOT\n" +
             "ON CARPARKING.CARPARKNO = PARKINGLOT.CARPARKNO\n" +
             "ORDER BY DISTANCE ASC, AVILABLELOTS DESC OFFSET PAGE  LIMIT PERPAGE";
}
