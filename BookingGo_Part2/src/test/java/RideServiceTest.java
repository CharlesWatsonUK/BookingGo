import com.BookingGo.model.Ride;
import com.BookingGo.service.RideService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Test the Ride Service.
 *
 * This test class executes the same test cases as the test class
 * found in my solution to part 1.
 *
 */

public class RideServiceTest {

    @Autowired
    private RideService rideService;

    @Test
    public void testValidInput1() {
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 3);
            Assert.assertTrue(TestUtils.testAll(rides, 3));
        }catch(Exception e){}
    }

    @Test
    public void testValidInput2() {
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 6);
            Assert.assertTrue(TestUtils.testAll(rides, 6));
        }catch(Exception e){}
    }

    @Test
    public void testValidInput3() {
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 16);
            Assert.assertTrue(TestUtils.testAll(rides, 6));
        }catch(Exception e){}
    }

    @Test
    public void testInvalidPassengers(){
        try {
            List<Ride> rides = rideService.getRides("51.470020,-0.454295", "51.00000,1.0000", 18);
            Assert.assertTrue(TestUtils.testAll(rides, 18));
        }catch(Exception e){}
    }

    @Test
    public void testInvalidCoordinates(){
        try {
            List<Ride> rides = rideService.getRides("51.470020", "51.00000,1.0000", 4);
            Assert.assertTrue(TestUtils.testAll(rides, 4));
        }catch(Exception e){}
    }
}
