import com.BookingGo.model.CarType;
import com.BookingGo.Global;
import com.BookingGo.model.Ride;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test Utils class
 *
 * Some test helper methods.
 *
 */

public class TestUtils {

    /**
     * Test all test criteria.
     *
     * @param rides
     * @return
     */
    public static boolean test(List<Ride> rides){
        if(!validSortOrder(rides)){
            return false;
        }
        return true;
    }


    /**
     * Split a String by lines.
     *
     * @param s
     * @return
     */
    public static String[] splitStringLines(String s){
        return s.split("\\r?\\n");
    }


    /**
     * Serialize a String array of rides into a list of Ride objects.
     *
     * @param lines
     * @return
     */
    public static List<Ride> serializeRides(String[] lines){
        List<Ride> rides = new ArrayList();
        if(lines[1].equals(Global.MESSAGE_NO_RIDES)){
            return rides; // If no rides, return empty list.
        }
        for(int i=1; i < lines.length; i++){
            String[] rideInfo = lines[i].split("-");
            CarType carType = CarType.valueOf(rideInfo[0].trim());
            Double price = Double.parseDouble(rideInfo[1].trim());
            rides.add(new Ride(carType, price));
        }
        return rides;
    }


    /**
     * True: rides in descending price order.
     * False: otherwise.
     *
     * @param rides
     * @return
     */
    public static boolean validSortOrder(List<Ride> rides){
        for(int i=1; i<rides.size(); i++){
            if(rides.get(i).getPrice() > rides.get(i-1).getPrice()){
                return false;
            }
        }
        return true;
    }
}
