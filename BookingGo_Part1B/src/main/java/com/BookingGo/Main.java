package com.BookingGo;

import com.BookingGo.model.CarType;
import com.BookingGo.model.Ride;
import com.BookingGo.model.Supplier;

import java.util.*;

/**
 * Main Application class
 *
 */

public class Main {

    private static Map<CarType, Integer> carCapacities = Global.initCarCapacities();
    private static List<Supplier> suppliers = Global.initSuppliers();

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        String pickup;
        String dropoff;
        int passengers;
        // Get params from CLI.
        try {
            pickup = args[0];
            dropoff = args[1];
            passengers = Integer.parseInt(args[2]);
        }catch(Exception e){
            System.out.println(Global.MESSAGE_MISSING_PARAMETERS);
            return;
        }

        // Validate user input.
        if(!validInput(pickup, dropoff, passengers)){
            System.out.print(Global.MESSAGE_INVALID_INPUT);
            return;
        }

        // Get, filter, sort and output rides.
        List<Ride> rides = getRides(pickup, dropoff);
        rides = filterRides(rides, passengers);
        sortRides(rides, true);
        outputRides(rides, passengers);
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
        for(Supplier supplier : suppliers){
           SupplierRequest req = new SupplierRequest(supplier, pickup, dropoff);
           rides.addAll(req.getRides());
       }
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
     * Filter rides (i.e. remove duplicate Car Types and
     * cars that don't hold enough passengers).
     *
     * @param rides
     * @param passengers
     * @return
     */
    private static List<Ride> filterRides(List<Ride> rides, int passengers){
       List<CarType> carTypes = Arrays.asList(CarType.values());
       List<Ride> filteredRides = new ArrayList();
       sortRides(rides, false);
       // Once the rides are sorted in ascending price order, select the first ride for each car type.
       for(CarType carType : carTypes){
           for(Ride ride : rides){
               if(ride.getCarType() == carType
                       && carCapacities.get(ride.getCarType()) >= passengers){
                   filteredRides.add(ride);
                   break;
               }
           }
       }
       return filteredRides;
    }


    /**
     * Output rides to the CLI.
     *
     * @param rides
     * @param passengers
     */
    private static void outputRides(List<Ride> rides, int passengers){
        if(rides.size() < 1){
            System.out.println(Global.MESSAGE_NO_RIDES);
        }else {
            for (Ride ride : rides) {
                System.out.println(ride.getCarType() + " - " + ride.getSupplier() + " - " + ride.getPrice().toString());
            }
        }
    }


    /**
     * Validate input params.
     *
     * @param pickup
     * @param dropoff
     * @param passengers
     * @return
     */
    private static boolean validInput(String pickup, String dropoff, int passengers){
        boolean pickupValid = pickup.matches(Global.COORDINATE_PATTERN);
        boolean dropOffValid = dropoff.matches(Global.COORDINATE_PATTERN);
        boolean passengersValid = passengers <= 16 && passengers > 0;
        return pickupValid && dropOffValid && passengersValid;
    }

}
