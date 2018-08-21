package com.BookingGo.api;

import com.BookingGo.Global;
import com.BookingGo.exception.InvalidSearchParameterException;
import com.BookingGo.service.RideService;
import com.BookingGo.model.Ride;
import com.BookingGo.service.impl.RideServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST API controller class.
 *
 */

@RestController
public class RideApi {

    @Autowired
    private RideService rideService;

    private final static Logger logger = Logger.getLogger(RideApi.class.getName());

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
            logger.log(Level.INFO, "Searching rides [pickup={0}, dropoff={1}, passengers={2}]"
                    ,new Object[]{pickup, dropoff, passengers});
            List<Ride> rides = rideService.getRides(pickup, dropoff, passengers);
            if(rides.size() < 1){
                // If no rides - return no rides message.
                logger.log(Level.INFO, "No rides currently available [pickup={0}, dropoff={1}, passengers={2}]"
                        ,new Object[]{pickup, dropoff, passengers});
                return ResponseEntity.ok().body(Global.MESSAGE_NO_RIDES);
            }
            logger.log(Level.INFO, "Found {0} rides [pickup={1}, dropoff={2}, passengers={3}]",
                    new Object[]{rides.size(), pickup, dropoff, passengers});
            return ResponseEntity.ok().body(rides);
        }
        catch (InvalidSearchParameterException ispe){
            // If bad params - return bad params exception message.
            logger.log(Level.WARNING, "Invalid search parameters [pickup={0}, dropoff={1}, passengers={2}]",
                    new Object[]{pickup, dropoff, passengers});
            return ResponseEntity.badRequest().body(ispe.getMessage());
        }
    }
}
