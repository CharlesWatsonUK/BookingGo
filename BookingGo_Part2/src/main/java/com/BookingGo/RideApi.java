package com.BookingGo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RideApi {

    @Autowired
    private RideService rideService;

    @RequestMapping("/ride")
    public List<Ride> getRides(@RequestParam("pickup") String pickup,
                               @RequestParam("dropoff") String dropoff,
                               @RequestParam("passengers") int passengers){

        return rideService.getRides(pickup, dropoff, passengers);
    }
}
