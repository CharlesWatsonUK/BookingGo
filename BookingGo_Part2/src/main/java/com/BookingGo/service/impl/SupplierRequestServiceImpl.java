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

/**
 * A Service for requesting rides from a single provider.
 *
 */

@Service
public class SupplierRequestServiceImpl implements SupplierRequestService {

    /**
     * Get rides.
     *
     * @return
     */
    public List<Ride> getRides(Supplier supplier, String pickup, String dropoff){
        URL url = buildUrl(supplier, pickup, dropoff);
        String response = executeRequest(url);
        List<Ride> rides = serializeResults(response, supplier);
        return rides;
    }

    /**
     * Build URL for GET request.
     *
     * @return
     */
    private URL buildUrl(Supplier supplier, String pickup, String dropoff){
        String url = supplier.getUrl();
        String pickupParam = "pickup=" + pickup;
        String dropoffParam = "dropoff=" + dropoff;
        try {
            return new URL(url + "?" + pickupParam + "&" + dropoffParam);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    /**
     * Execute HTTP GET request - return response String.
     *
     * @return
     */
    private String executeRequest(URL url){
        StringBuffer content = new StringBuffer();
        // Execute HTTP request.
        try{
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
        }catch(Exception e){
            // In case of error return empty string.
            return "";
        }
        return content.toString();
    }


    /**
     * Serialize results in a response string
     * into Ride objects.
     *
     * @param res
     * @return
     */
    private List<Ride> serializeResults(String res, Supplier supplier){
        // Convert response string to JSON and get the 'options'
        JsonArray options;
        try {
            // Convert response string to JSON
            Gson g = new Gson();
            JsonObject resJson = new JsonParser().parse(res).getAsJsonObject();
            options = resJson.getAsJsonArray("options");
        }catch(Exception e){
            // In case of error an empty list of rides.
            return new ArrayList<Ride>();
        }
        // Serialize JSON as list of Ride objects.
        List<Ride> rides = new ArrayList<Ride>();
        for(JsonElement option : options){
            try {
                JsonObject ride = option.getAsJsonObject();
                String carType = ride.get("car_type").toString();
                carType = carType.substring(1, carType.length() - 1); // Strip quotation marks.
                Double price = Double.parseDouble(ride.get("price").toString());
                Ride rideObj = new Ride(supplier.getSupplierName(), CarType.valueOf(carType), price);
                rides.add(rideObj);
            }catch (Exception e){
                // If can't serialize a ride object (e.g. if car type doesn't exist) then skip it.
            }
        }
        return rides;
    }
}
