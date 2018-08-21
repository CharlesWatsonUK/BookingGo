package com.BookingGo.service;

import com.BookingGo.model.Ride;
import com.BookingGo.model.Supplier;

import java.util.List;

/**
 * Supplier Request Service
 *
 */

public interface SupplierRequestService {

    /**
     * Get all rides from a single specified supplier
     * meeting the specified parameters.
     *
     * @param supplier
     * @param pickup
     * @param dropoff
     * @return
     */
    List<Ride> getRides(Supplier supplier, String pickup, String dropoff);
}
