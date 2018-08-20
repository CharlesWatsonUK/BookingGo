package com.BookingGo;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestRide {

    private final int HTTP_RESPONSE_TIMEOUT = 2000;

    private Supplier supplier;
    private String pickup;
    private String dropoff;

    public RequestRide(Supplier supplier, String pickup, String dropoff) {
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
        return serializeResults(executeRequest());
    }

    /**
     * Build URL for GET request.
     *
     * @return
     */
    private URL buildUrl(){
        String url = this.supplier.getUrl();
        String pickup = "pickup=" + this.pickup;
        String dropoff = "dropoff=" + this.dropoff;
        try {
            return new URL(url + "?" + pickup + "&" + dropoff);
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
    private String executeRequest(){
        URL url = buildUrl();
        StringBuffer content = new StringBuffer();
        // Execute HTTP request.
        try{
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(HTTP_RESPONSE_TIMEOUT);
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
    private List<Ride> serializeResults(String res){
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
                Ride rideObj = new Ride(this.supplier.getSupplierName(), CarType.valueOf(carType), price);
                rides.add(rideObj);
            }catch (Exception e){
                // If can't serialize a ride object (e.g. if car type doesn't exist) then skip it.
            }
        }
        return rides;
    }
}
