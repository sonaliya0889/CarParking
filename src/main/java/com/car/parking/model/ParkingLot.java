package com.car.parking.model;

public class ParkingLot {
    String carparkNumber;
    int totalLots;
    int availableLots;
    String lotType;
    String updateDatetime;

    public String getCarparkNumber() {
        return carparkNumber;
    }

    public void setCarparkNumber(String carparkNumber) {
        this.carparkNumber = carparkNumber;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public int getAvailableLots() {
        return availableLots;
    }

    public void setAvailableLots(int availableLots) {
        this.availableLots = availableLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    @Override
    public String toString() {
        return "ParkingLot{" + "carparkNumber='" + carparkNumber + '\'' + ", totalLots=" + totalLots + ", availableLots=" + availableLots + ", lotType='" + lotType + '\'' + ", updateDatetime='" + updateDatetime + '\'' + '}';
    }
}
