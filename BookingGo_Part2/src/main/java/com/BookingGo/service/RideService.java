package com.BookingGo.service;

import com.BookingGo.exception.InvalidSearchParameterException;
import com.BookingGo.model.Ride;

import java.util.List;

/**
 * Ride Service
 *
 */

public interface RideService {

    /**
     * Search for all available rides meeting the specified parameters.
     *
     * @param pickup
     * @param dropoff
     * @param passengers
     * @return
     */
    List<Ride> getRides(String pickup, String dropoff, int passengers)
            throws InvalidSearchParameterException;

}
