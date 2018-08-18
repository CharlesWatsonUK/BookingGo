package com.BookingGo;


import java.util.List;

public class Main {

    private static Supplier davesTaxis;
    private static Supplier ericsTaxis;
    private static Supplier jeffsTaxis;

    public static void main(String[] args) {
        System.out.println("hello");

        initSuppliers();

        RequestRide req = new RequestRide(davesTaxis, "51.470020,-0.454295", "51.00000,1.0000");

        List<Ride> rides = req.request();

        for(Ride ride : rides){
            System.out.println(ride.getSupplier() +" "+ride.getCarType()+" "+ride.getPrice().toString());
        }

    }

    private static void initSuppliers(){
        davesTaxis = new Supplier("Dave's Taxis", "https://techtest.rideways.com/dave");
        ericsTaxis = new Supplier("Eric's Taxis", "https://techtest.rideways.com/eric");
        jeffsTaxis = new Supplier("Jeff's Taxis", "https://techtest.rideways.com/jeff");
    }


}
