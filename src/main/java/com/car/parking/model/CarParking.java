package com.car.parking.model;

public class CarParking {
    String car_park_no;
    String address;
    String x_coord;
    String y_coord;

    public String getCar_park_no() {
        return car_park_no;
    }

    public void setCar_park_no(String car_park_no) {
        this.car_park_no = car_park_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getX_coord() {
        return x_coord;
    }

    public void setX_coord(String x_coord) {
        this.x_coord = x_coord;
    }

    public String getY_coord() {
        return y_coord;
    }

    public void setY_coord(String y_coord) {
        this.y_coord = y_coord;
    }

    @Override
    public String toString() {
        return "CarParking{" + "car_park_no='" + car_park_no + '\'' + ", address='" + address + '\'' + ", x_coord='" + x_coord + '\'' + ", y_coord='" + y_coord + '\'' + '}';
    }
}
