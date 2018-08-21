package com.BookingGo.api;

import com.BookingGo.Global;
import com.BookingGo.exception.InvalidSearchParameterException;
import com.BookingGo.service.RideService;
import com.BookingGo.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API controller class.
 *
 */

@RestController
public class RideApi {

    @Autowired
    private RideService rideService;

    /**
     * GET request controller for the Ride API.
     *
     * @param pickup
     * @param dropoff
     * @param passengers
     * @return
     */
    @RequestMapping("/ride")
    public ResponseEntity<?> getRides(@RequestParam("pickup") String pickup,
                                      @RequestParam("dropoff") String dropoff,
                                      @RequestParam("passengers") int passengers){
        try{
            List<Ride> rides = rideService.getRides(pickup, dropoff, passengers);
            if(rides.size() < 1){
                // If no rides - return no rides message.
                return ResponseEntity.ok().body(Global.MESSAGE_NO_RIDES);
            }
            return ResponseEntity.ok().body(rides);
        }
        catch (InvalidSearchParameterException ispe){
            // If bad params - return bad params exception message.
            return ResponseEntity.badRequest().body(ispe.getMessage());
        }
    }
}
