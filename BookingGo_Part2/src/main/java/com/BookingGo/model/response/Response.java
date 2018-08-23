package com.BookingGo.model.response;

/**
 * Abstract Response Class.
 *
 */

public abstract class Response {

    String pickup;
    String dropoff;
    int passengers;

    public Response(String pickup, String dropoff, int passengers) {
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.passengers = passengers;
    }

    public String getPickup() {
        return pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public int getPassengers() {
        return passengers;
    }

}
