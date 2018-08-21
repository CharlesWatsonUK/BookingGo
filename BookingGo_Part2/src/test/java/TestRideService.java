import com.BookingGo.model.Ride;
import com.BookingGo.service.RideService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Test the Ride Service.
 *
 * This test class executes the same tests as the test class
 * found in my solution to part 1.
 *
 */

public class TestRideService {

    @Autowired
    private RideService rideService;

    @Test
    @DisplayName("Testing valid input...")
    void testValidInput1() {
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 3);
            Assertions.assertTrue(TestUtils.testAll(rides, 3));
        }catch(Exception e){}
    }

    @Test
    @DisplayName("Testing valid input...")
    void testValidInput2() {
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 6);
            Assertions.assertTrue(TestUtils.testAll(rides, 6));
        }catch(Exception e){}
    }

    @Test
    @DisplayName("Testing valid input...")
    void testValidInput3() {
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 16);
            Assertions.assertTrue(TestUtils.testAll(rides, 6));
        }catch(Exception e){}
    }

    @Test
    @DisplayName("Testing invalid input - passengers > 16")
    void testInvalidPassengers(){
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 18);
            Assertions.assertTrue(TestUtils.testAll(rides, 18));
        }catch(Exception e){}
    }

    @Test
    @DisplayName("Testing invalid input - bad coordinates")
    void testInvalidCoordinates(){
        try {
            List<Ride> rides = rideService.getRides("51.470020", "51.00000,1.0000", 4);
            Assertions.assertTrue(TestUtils.testAll(rides, 4));
        }catch(Exception e){}
    }
}
