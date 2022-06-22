## README

* Java Version
  **1.8**

* Database
  **EmbeddedDatabase H2**

* How to execute api

  `java -jar target/car-parking-0.0.1-SNAPSHOT.jar`

* How to run the test

  ``

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


