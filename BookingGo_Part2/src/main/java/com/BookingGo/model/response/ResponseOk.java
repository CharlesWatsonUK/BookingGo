package com.BookingGo.model.response;

import com.BookingGo.model.Ride;

import java.util.List;

/**
 * Ok Response class
 *
 */

public class ResponseOk extends Response{

    List<Ride> rides;

    public ResponseOk(String pickup, String dropoff, int passengers, List<Ride> rides){
        super(pickup, dropoff,passengers);
        this.rides = rides;
    }

    public List<Ride> getRides() {
        return rides;
    }
}
