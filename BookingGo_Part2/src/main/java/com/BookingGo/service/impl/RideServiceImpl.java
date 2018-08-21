package com.BookingGo.service.impl;

import com.BookingGo.Global;
import com.BookingGo.exception.InvalidSearchParameterException;
import com.BookingGo.model.CarType;
import com.BookingGo.model.Ride;
import com.BookingGo.model.Supplier;
import com.BookingGo.service.RideService;
import com.BookingGo.service.SupplierRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Ride Service Implementation.
 *
 */

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private SupplierRequestService supplierRequestService;

    private static Map<CarType, Integer> carCapacities = Global.initCarCapacities();
    private static List<Supplier> suppliers = Global.initSuppliers();


    /**
     * Get all available rides for all available
     * suppliers for the specified parameters.
     *
     * @param pickup
     * @param dropoff
     * @param passengers
     * @return
     * @throws InvalidSearchParameterException
     */
    public List<Ride> getRides(String pickup, String dropoff, int passengers)
            throws InvalidSearchParameterException
    {
        // Validate params.
        if(!validInput(pickup, dropoff, passengers)){
            throw new InvalidSearchParameterException();
        }

        // Get, filter, sort and output rides.
        List<Ride> rides = getRides(pickup, dropoff);
        rides = filterRides(rides, passengers);
        sortRides(rides, true);
        return rides;
    }


    /**
     * Get all rides for pickup and dropoff locations.
     *
     * @param pickup
     * @param dropoff
     * @return
     */
    private List<Ride> getRides(String pickup, String dropoff){
        System.out.println(Global.MESSAGE_SEARCHING);
        List<Ride> rides = new ArrayList();
        for(Supplier supplier : suppliers){
            rides.addAll(this.supplierRequestService.getRides(supplier, pickup, dropoff));
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
    private List<Ride> sortRides(List<Ride> rides, final boolean descending){
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
    private List<Ride> filterRides(List<Ride> rides, int passengers){
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
     * Validate input params.
     *
     * @param pickup
     * @param dropoff
     * @param passengers
     * @return
     */
    private boolean validInput(String pickup, String dropoff, int passengers){
        boolean pickupValid = pickup.matches(Global.COORDINATE_PATTERN);
        boolean dropOffValid = dropoff.matches(Global.COORDINATE_PATTERN);
        boolean passengersValid = passengers <= 16 && passengers > 0;
        return pickupValid && dropOffValid && passengersValid;
    }

}
