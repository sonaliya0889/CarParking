## README

* Spring Boot
  **2.0.2**
* Java Version
  **1.8**
* Maven
  **3.8**
* Database
  **EmbeddedDatabase H2**

* How To Build

  `mvn clean install`
  `mvn package`

* How To Execute Jar

  `java -jar target/car-parking-0.0.1-SNAPSHOT.jar`

## Models

#### 1. Car Parking
- carParkNo
- address
- xCoord
- yCoord
- carParkType
- typeOfParkingSystem
- shortTermParking
- freeParking
- nightParking
- carParkDecks
- gantryHeight
- carParkBasement

```
public class CarParking {
    String carParkNo;
    String address;
    String xCoord;
    String yCoord;
    String carParkType;
    String typeOfParkingSystem;
    String shortTermParking;
    String freeParking;
    String nightParking;
    String carParkDecks;
    String gantryHeight;
    String carParkBasement;
    ...
    setter & getters
    }
```

#### 2. Parking Lot

- carparkNumber,
- totalLots
- availableLots
- lotType
- updateDatetime

```public class ParkingLot {
    String carparkNumber;
    int totalLots;
    int availableLots;
    String lotType;
    String updateDatetime;
    ...
    setter & getters
    }
````
### SQL

#### Nearest Parking Lot SQL
This query will return the nearest parking lot with maximum available spots for parking, based on supplied latitude and longitude.
Here 111.2 constant value is denoting km/degree and 57.3 is value of pi/180 degree.
```
SELECT ADDRESS, TOTALLOTS,AVILABLELOTS, XCOORD, YCOORD, SQRT(
    POWER(111.2 * (ROUND(XCOORD)- [latitude]), 2) +
    POWER(111.2 * ([longitude]-ROUND(YCOORD)) * COS(XCOORD / 57.3), 2)) AS DISTANCE FROM CARPARKING 
INNER JOIN PARKINGLOT
ON CARPARKING.CARPARKNO = PARKINGLOT.CARPARKNO
ORDER BY DISTANCE ASC, AVILABLELOTS DESC OFFSET 1  LIMIT 3;
```

## Sample API
**Request**

GET `http://localhost:8080/carparks/nearest?latitude=1.37326&longitude=103.897&page=1&per_page=3`

**Response**
```json
[
  {
    "address": "BLK 901 JURONG WEST STREET 91",
    "latitude": "11542334.545999771",
    "longitude": "149167.5795805857",
    "totalLots": 200,
    "availableLots": 145
  },
  {
    "address": "BLK 912/932 JURONG WEST STREET 92",
    "latitude": "11542542.661911953",
    "longitude": "149190.2674119351",
    "totalLots": 779,
    "availableLots": 137
  },
  {
    "address": "BLK 909/911 JURONG WEST STREET 91",
    "latitude": "11542322.013292428",
    "longitude": "149379.83884815438",
    "totalLots": 150,
    "availableLots": 1
  }
]
```


