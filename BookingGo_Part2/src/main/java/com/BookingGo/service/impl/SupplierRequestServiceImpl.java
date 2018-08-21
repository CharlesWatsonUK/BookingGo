package com.BookingGo.service.impl;

import com.BookingGo.Global;
import com.BookingGo.model.CarType;
import com.BookingGo.model.Ride;
import com.BookingGo.model.Supplier;
import com.BookingGo.service.SupplierRequestService;
import com.google.gson.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Service for requesting rides from a single provider.
 *
 */

@Service
public class SupplierRequestServiceImpl implements SupplierRequestService {

    private final static Logger logger = Logger.getLogger(SupplierRequestServiceImpl.class.getName());

    /**
     * Get rides.
     *
     * @return
     */
    public List<Ride> getRides(Supplier supplier, String pickup, String dropoff){
        URL url;
        String response;
        List<Ride> rides = new ArrayList();

        // Build request URL.
        try{
            url = buildUrl(supplier, pickup, dropoff);
        }catch(Exception e){
            logger.log(Level.WARNING, "Unable to build request URL [supplier={0}, pickup={1}, dropoff={2}]\n" +
                            "Error: {3}.", new Object[]{supplier.getSupplierName(), pickup, dropoff});
            return rides; // If error return empty list of rides.
        }

        // Get response.
        try{
            response = executeRequest(url);
        }catch (Exception e){
            logger.log(Level.WARNING, "Could not get valid response from supplier [supplier={0}, pickup={1}, dropoff={2}]\n" +
                    "Error: {3}.", new Object[]{supplier.getSupplierName(), pickup, dropoff, e.getMessage()});
            return rides; // If error return empty list of rides.
        }

        // Serialize response into a list of Ride objects and return.
        try{
            rides = serializeResults(response, supplier);
        }catch(Exception e){
            logger.log(Level.WARNING, "Could not serialize supplier's response [supplier={0}, pickup={1}, dropoff={2}]\n" +
                    "Error: {3}.", new Object[]{supplier.getSupplierName(), pickup, dropoff, e.getMessage()});
        }finally {
            return rides; // If error return empty list of rides.
        }
    }


    /**
     * Build URL for GET request.
     *
     * @return
     */
    private URL buildUrl(Supplier supplier, String pickup, String dropoff)
        throws Exception {
        String url = supplier.getUrl();
        String pickupParam = "pickup=" + pickup;
        String dropoffParam = "dropoff=" + dropoff;
        return new URL(url + "?" + pickupParam + "&" + dropoffParam);
    }

    /**
     * Execute HTTP GET request - return response String.
     *
     * @return
     */
    private String executeRequest(URL url) throws Exception{
        StringBuffer content = new StringBuffer();
        // Execute HTTP request.
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(Global.HTTP_RESPONSE_TIMEOUT);
        BufferedReader res = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        while((inputLine = res.readLine()) != null){
            content.append(inputLine);
        }
        res.close();
        return content.toString();
    }


    /**
     * Serialize results in a response string
     * into Ride objects.
     *
     * @param res
     * @return
     */
    private List<Ride> serializeResults(String res, Supplier supplier)
        throws Exception{
        // Convert response string to JSON and get the 'options'
        JsonArray options;
        // Convert response string to JSON
        Gson g = new Gson();
        JsonObject resJson = new JsonParser().parse(res).getAsJsonObject();
        options = resJson.getAsJsonArray("options");
        // Serialize JSON as list of Ride objects.
        List<Ride> rides = new ArrayList<Ride>();
        for(JsonElement option : options){
            JsonObject ride = option.getAsJsonObject();
            String carType = ride.get("car_type").toString();
            carType = carType.substring(1, carType.length() - 1); // Strip quotation marks.
            Double price = Double.parseDouble(ride.get("price").toString());
            Ride rideObj = new Ride(supplier.getSupplierName(), CarType.valueOf(carType), price);
            rides.add(rideObj);
        }
        return rides;
    }
}
