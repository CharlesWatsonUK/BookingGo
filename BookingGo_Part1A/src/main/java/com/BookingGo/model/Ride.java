package com.BookingGo.model;

/**
 * Ride class
 *
 */

public class Ride {

    private CarType carType;
    private Double price;

    public Ride(CarType carType, Double price) {
        this.carType = carType;
        this.price = price;
    }

    public CarType getCarType() {
        return carType;
    }

    public Double getPrice() {
        return price;
    }
}
