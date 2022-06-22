package com.car.parking.model;

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

    public String getCarParkType() {
        return carParkType;
    }

    public void setCarParkType(String carParkType) {
        this.carParkType = carParkType;
    }

    public String getTypeOfParkingSystem() {
        return typeOfParkingSystem;
    }

    public void setTypeOfParkingSystem(String typeOfParkingSystem) {
        this.typeOfParkingSystem = typeOfParkingSystem;
    }

    public String getShortTermParking() {
        return shortTermParking;
    }

    public void setShortTermParking(String shortTermParking) {
        this.shortTermParking = shortTermParking;
    }

    public String getFreeParking() {
        return freeParking;
    }

    public void setFreeParking(String freeParking) {
        this.freeParking = freeParking;
    }

    public String getNightParking() {
        return nightParking;
    }

    public void setNightParking(String nightParking) {
        this.nightParking = nightParking;
    }

    public String getCarParkDecks() {
        return carParkDecks;
    }

    public void setCarParkDecks(String carParkDecks) {
        this.carParkDecks = carParkDecks;
    }

    public String getGantryHeight() {
        return gantryHeight;
    }

    public void setGantryHeight(String gantryHeight) {
        this.gantryHeight = gantryHeight;
    }

    public String getCarParkBasement() {
        return carParkBasement;
    }

    public void setCarParkBasement(String carParkBasement) {
        this.carParkBasement = carParkBasement;
    }

    public String getCarParkNo() {
        return carParkNo;
    }

    public void setCarParkNo(String carParkNo) {
        this.carParkNo = carParkNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getxCoord() {
        return xCoord;
    }

    public void setxCoord(String xCoord) {
        this.xCoord = xCoord;
    }

    public String getyCoord() {
        return yCoord;
    }

    public void setyCoord(String yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public String toString() {
        return "CarParking{" + "car_park_no='" + carParkNo + '\'' + ", address='" + address + '\'' + ", x_coord='" + xCoord + '\'' + ", y_coord='" + yCoord + '\'' + '}';
    }
}
