package com.BookingGo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static Supplier davesTaxis;
    private static Supplier ericsTaxis;
    private static Supplier jeffsTaxis;

    public static void main(String[] args) {

        initSuppliers();

        String pickup = args[0];
        String dropoff = args[1];

        RequestRide req = new RequestRide(davesTaxis, pickup, dropoff);

        List<Ride> rides = sortRides(req.request());

        for(Ride ride : rides){
            System.out.println(ride.getCarType()+" - "+ride.getSupplier()+" - "+ride.getPrice().toString());
        }

    }

    private static void initSuppliers(){
        davesTaxis = new Supplier("Dave's Taxis", "https://techtest.rideways.com/dave");
        ericsTaxis = new Supplier("Eric's Taxis", "https://techtest.rideways.com/eric");
        jeffsTaxis = new Supplier("Jeff's Taxis", "https://techtest.rideways.com/jeff");
    }

    private static List<Ride> sortRides(List<Ride> rides){
        Collections.sort(rides, new Comparator<Ride>() {
            public int compare(Ride lhs, Ride rhs) {
                return lhs.getPrice() > rhs.getPrice() ? -1 : lhs.getPrice() < rhs.getPrice() ? 1 : 0;
            }
        });
        return rides;
    }

}
