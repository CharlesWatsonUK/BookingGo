package com.BookingGo;

public class Ride {

    private String supplier;
    private String carType;
    private Double price;

    public Ride(String supplier, String carType, Double price) {
        this.supplier = supplier;
        this.carType = carType;
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getCarType() {
        return carType;
    }

    public Double getPrice() {
        return price;
    }
}
