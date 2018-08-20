package com.BookingGo;


import java.util.List;

public interface RideService {

    List<Ride> getRides(String pickup, String dropoff, int passengers);

}
