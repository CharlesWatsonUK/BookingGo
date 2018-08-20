import com.BookingGo.CarType;
import com.BookingGo.Global;
import com.BookingGo.Ride;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Some test helper methods.
 *
 */

public class TestUtils {

    /**
     * Test all test criteria.
     *
     * @param rides
     * @param passengers
     * @return
     */
    public static boolean testAll(List<Ride> rides, int passengers){
        if(!validPassengerNumbers(rides, passengers)){
            return false;
        }
        if(!validSortOrder(rides)){
            return false;
        }
        if(!onlyOneCarType(rides)){
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
            String supplier = rideInfo[1].trim();
            Double price = Double.parseDouble(rideInfo[2].trim());
            rides.add(new Ride(supplier, carType, price));
        }
        return rides;
    }


    /**
     * True: all carTypes have passenger numbers greater than or equal to the passenger number.
     * False: otherwise.
     *
     * @param rides
     * @param passengers
     * @return
     */
    public static boolean validPassengerNumbers(List<Ride> rides, int passengers){
        Map<CarType, Integer> carCapacities = Global.initCarCapacities();
        for(Ride ride : rides){
            if(passengers > carCapacities.get(ride.getCarType())){
                return false;
            }
        }
        return true;
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


    /**
     * True: only zero or one of each car type.
     * False: otherwise.
     *
     * @param rides
     * @return
     */
    public static boolean onlyOneCarType(List<Ride> rides){
        CarType[] carTypes = CarType.values();
        for(final CarType carType : carTypes){
            for(Ride ride : rides){
                int count = 0;
                if(ride.getCarType().equals(carType)){
                    count++;
                    if(count > 1){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
