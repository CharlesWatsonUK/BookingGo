package com.BookingGo.controller;

import com.BookingGo.exception.InvalidSearchParameterException;
import com.BookingGo.model.response.Response;
import com.BookingGo.model.response.ResponseError;
import com.BookingGo.model.response.ResponseOk;
import com.BookingGo.service.RideService;
import com.BookingGo.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ride Controller
 *
 * REST API controller.
 *
 */

@RestController
public class RideController {

    @Autowired
    private RideService rideService;

    private final static Logger logger = Logger.getLogger(RideController.class.getName());

    /**
     * GET request controller for the Ride API.
     *
     * @param pickup
     * @param dropoff
     * @param passengers
     * @return
     */
    @GetMapping(value = "/ride")
    public ResponseEntity<Response> getRides(@RequestParam("pickup") String pickup,
                                         @RequestParam("dropoff") String dropoff,
                                         @RequestParam("passengers") int passengers){
        logger.log(Level.INFO, "Searching rides [pickup={0}, dropoff={1}, passengers={2}]"
                ,new Object[]{pickup, dropoff, passengers});
        try{
            List<Ride> rides = rideService.getRides(pickup, dropoff, passengers);
            logger.log(Level.INFO, "Found {0} rides [pickup={1}, dropoff={2}, passengers={3}]",
                    new Object[]{rides.size(), pickup, dropoff, passengers});
            return ResponseEntity.ok().body(new ResponseOk(pickup, dropoff, passengers, rides));
        }
        catch (InvalidSearchParameterException ispe){
            // If bad params - return bad params exception message.
            logger.log(Level.WARNING, "Invalid search parameters [pickup={0}, dropoff={1}, passengers={2}]",
                    new Object[]{pickup, dropoff, passengers});
            return ResponseEntity.badRequest().body(new ResponseError(pickup, dropoff, passengers, ispe.getMessage()));
            //return null;
        }
    }
}
