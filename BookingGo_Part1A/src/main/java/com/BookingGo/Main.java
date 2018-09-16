package com.BookingGo;

import com.BookingGo.model.Ride;
import com.BookingGo.model.Supplier;
import java.util.*;

/**
 * Main Application class
 *
 */

public class Main {

    private static Supplier davesTaxis = new Supplier("Dave's Taxis", "https://techtest.rideways.com/dave");

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        String pickup;
        String dropoff;
        // Get params from CLI.
        try {
            pickup = args[0];
            dropoff = args[1];
        }catch(Exception e){
            System.out.println(Global.MESSAGE_MISSING_PARAMETERS);
            return;
        }

        // Validate user input.
        if(!validInput(pickup, dropoff)){
            System.out.print(Global.MESSAGE_INVALID_INPUT);
            return;
        }

        // Get, filter, sort and output rides.
        List<Ride> rides = getRides(pickup, dropoff);
        sortRides(rides, true);
        outputRides(rides);
    }


    /**
     * Get all rides for pickup and dropoff locations.
     *
     * @param pickup
     * @param dropoff
     * @return
     */
    private static List<Ride> getRides(String pickup, String dropoff){
        System.out.println(Global.MESSAGE_SEARCHING);
        List<Ride> rides = new ArrayList();
        SupplierRequest request = new SupplierRequest(davesTaxis, pickup, dropoff);
        rides.addAll(request.getRides());
        return rides;
    }


    /**
     * Sort rides (either price descending or ascending)
     *
     * @param rides
     * @param descending
     * @return
     */
    private static List<Ride> sortRides(List<Ride> rides, final boolean descending){
        Collections.sort(rides, new Comparator<Ride>() {
            public int compare(Ride lhs, Ride rhs) {
                if(descending){
                    return lhs.getPrice() > rhs.getPrice() ? -1 : lhs.getPrice() < rhs.getPrice() ? 1 : 0;
                }else{
                    return lhs.getPrice() < rhs.getPrice() ? -1 : lhs.getPrice() > rhs.getPrice() ? 1 : 0;
                }
            }
        });
        return rides;
    }


    /**
     * Output rides to the CLI.
     *
     * @param rides
     */
    private static void outputRides(List<Ride> rides){
        if(rides.size() < 1){
            System.out.println(Global.MESSAGE_NO_RIDES);
        }else {
            for (Ride ride : rides) {
                System.out.println(ride.getCarType() + " - " + ride.getPrice().toString());
            }
        }
    }


    /**
     * Validate input params.
     *
     * @param pickup
     * @param dropoff
     * @return
     */
    private static boolean validInput(String pickup, String dropoff){
        boolean pickupValid = pickup.matches(Global.COORDINATE_PATTERN);
        boolean dropOffValid = dropoff.matches(Global.COORDINATE_PATTERN);
        return pickupValid && dropOffValid;
    }

}
