package com.BookingGo;

import java.util.*;

public class Main {

    private static final String COORDINATE_PATTERN = "^[-]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";

    private static Map<CarType, Integer> carCapacities = new HashMap();
    private static List<Supplier> suppliers = new ArrayList();

    public static void main(String[] args) {
        // Initialise our static data - suppliers and car types.
        initSuppliers();
        initCarCapacities();

        // Get params from CLI.
        String pickup = args[0];
        String dropoff = args[1];
        int passengers = Integer.parseInt(args[2]);

        // Validate user input.
        if(!validInput(pickup, dropoff, passengers)){
            System.out.print("Invalid input!\n" +
                    "Please make sure your location coordinates are properly formed, e.g.:\n" +
                    "51.470020,-0.454295" +
                    "\n also please ensure your passenger number is between 1 and 16 inclusive.");
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
        System.out.println("Searching for your ride (this may take a few seconds)...");
        List<Ride> rides = new ArrayList();
        for(Supplier supplier : suppliers){
           RequestRide req = new RequestRide(supplier, pickup, dropoff);
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
            System.out.println("Sorry there are currently no rides matching your criteria.");
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
        boolean pickupValid = pickup.matches(COORDINATE_PATTERN);
        boolean dropOffValid = dropoff.matches(COORDINATE_PATTERN);
        boolean passengersValid = passengers <= 16 && passengers > 0;
        return pickupValid && dropOffValid && passengersValid;
    }

    /**
     * Initialise suppliers.
     *
     */
    private static void initSuppliers(){
        suppliers.add(new Supplier("Dave's Taxis", "https://techtest.rideways.com/dave"));
        suppliers.add(new Supplier("Eric's Taxis", "https://techtest.rideways.com/eric"));
        suppliers.add(new Supplier("Jeff's Taxis", "https://techtest.rideways.com/jeff"));
    }

    /**
     * Initialise Car Capacities.
     *
     */
    private static void initCarCapacities(){
        carCapacities.put(CarType.STANDARD, 4);
        carCapacities.put(CarType.EXECUTIVE, 4);
        carCapacities.put(CarType.LUXURY, 4);
        carCapacities.put(CarType.PEOPLE_CARRIER, 6);
        carCapacities.put(CarType.LUXURY_PEOPLE_CARRIER, 6);
        carCapacities.put(CarType.MINIBUS, 16);
    }

}
