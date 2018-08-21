package com.BookingGo.model;

public class Ride {

    private String supplier;
    private CarType carType;
    private Double price;

    public Ride(String supplier, CarType carType, Double price) {
        this.supplier = supplier;
        this.carType = carType;
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public CarType getCarType() {
        return carType;
    }

    public Double getPrice() {
        return price;
    }
}
