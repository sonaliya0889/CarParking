package com.car.parking.model;

public class CarParking {
    String carParkNo;
    String address;
    String xCoord;
    String yCoord;
    String car_park_type;
    String type_of_parking_system;
    String short_term_parking;
    String free_parking;
    String night_parking;
    String car_park_decks;
    String gantry_height;
    String car_park_basement;

    public String getCar_park_type() {
        return car_park_type;
    }

    public void setCar_park_type(String car_park_type) {
        this.car_park_type = car_park_type;
    }

    public String getType_of_parking_system() {
        return type_of_parking_system;
    }

    public void setType_of_parking_system(String type_of_parking_system) {
        this.type_of_parking_system = type_of_parking_system;
    }

    public String getShort_term_parking() {
        return short_term_parking;
    }

    public void setShort_term_parking(String short_term_parking) {
        this.short_term_parking = short_term_parking;
    }

    public String getFree_parking() {
        return free_parking;
    }

    public void setFree_parking(String free_parking) {
        this.free_parking = free_parking;
    }

    public String getNight_parking() {
        return night_parking;
    }

    public void setNight_parking(String night_parking) {
        this.night_parking = night_parking;
    }

    public String getCar_park_decks() {
        return car_park_decks;
    }

    public void setCar_park_decks(String car_park_decks) {
        this.car_park_decks = car_park_decks;
    }

    public String getGantry_height() {
        return gantry_height;
    }

    public void setGantry_height(String gantry_height) {
        this.gantry_height = gantry_height;
    }

    public String getCar_park_basement() {
        return car_park_basement;
    }

    public void setCar_park_basement(String car_park_basement) {
        this.car_park_basement = car_park_basement;
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
