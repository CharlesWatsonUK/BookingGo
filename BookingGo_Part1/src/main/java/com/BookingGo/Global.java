package com.BookingGo;

import com.BookingGo.model.CarType;
import com.BookingGo.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A global class for static data.
 *
 */

public class Global {

    public static final int HTTP_RESPONSE_TIMEOUT = 2000;

    public static final String COORDINATE_PATTERN = "^[-]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";

    public static final String MESSAGE_INVALID_INPUT = "Invalid input!\n" +
            "Please make sure your location coordinates are properly formed, e.g.:\n" +
            "51.470020,-0.454295" +
            "\n also please ensure your passenger number is between 1 and 16 inclusive.";

    public static final String MESSAGE_SEARCHING = "Searching for your ride (this may take a few seconds)...";

    public static final String MESSAGE_NO_RIDES = "Sorry there are currently no rides matching your criteria.";

    public static List<Supplier> initSuppliers(){
        List<Supplier> suppliers = new ArrayList();
        suppliers.add(new Supplier("Dave's Taxis", "https://techtest.rideways.com/dave"));
        suppliers.add(new Supplier("Eric's Taxis", "https://techtest.rideways.com/eric"));
        suppliers.add(new Supplier("Jeff's Taxis", "https://techtest.rideways.com/jeff"));
        return suppliers;
    }

    public static Map<CarType, Integer> initCarCapacities(){
        Map<CarType, Integer> carCapacities = new HashMap();
        carCapacities.put(CarType.STANDARD, 4);
        carCapacities.put(CarType.EXECUTIVE, 4);
        carCapacities.put(CarType.LUXURY, 4);
        carCapacities.put(CarType.PEOPLE_CARRIER, 6);
        carCapacities.put(CarType.LUXURY_PEOPLE_CARRIER, 6);
        carCapacities.put(CarType.MINIBUS, 16);
        return carCapacities;
    }
}
