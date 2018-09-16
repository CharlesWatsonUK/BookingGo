package com.BookingGo;

import com.BookingGo.model.CarType;
import com.BookingGo.model.Ride;
import com.BookingGo.model.Supplier;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Supplier Request class
 *
 * A Class to facilitate requesting rides from a single provider.
 *
 */

public class SupplierRequest {

    private Supplier supplier;
    private String pickup;
    private String dropoff;

    public SupplierRequest(Supplier supplier, String pickup, String dropoff){
        this.supplier = supplier;
        this.pickup = pickup;
        this.dropoff = dropoff;
    }

    /**
     * Get rides.
     *
     * @return
     */
    public List<Ride> getRides(){
        URL url;
        String response;
        List<Ride> rides = new ArrayList();

        // Build request URL.
        try{
            url = buildUrl(supplier, pickup, dropoff);
        }catch(Exception e){
            System.out.println("Could build URL for "+supplier.getSupplierName());
            return rides; // If error return empty list of rides.
        }

        // Get response.
        try{
            response = executeRequest(url);
        }catch (Exception e){
            return rides; // If error return empty list of rides (servers' often give 500)
        }

        // Serialize response into a list of Ride objects and return.
        try{
            rides = serializeResults(response, supplier);
        }catch(Exception e){
            System.out.println("Could not serialize response from "+supplier.getSupplierName());
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
            Ride rideObj = new Ride(CarType.valueOf(carType), price);
            rides.add(rideObj);
        }
        return rides;
    }
}
