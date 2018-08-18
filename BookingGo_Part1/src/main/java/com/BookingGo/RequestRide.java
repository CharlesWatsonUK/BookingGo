package com.BookingGo;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestRide {

    Supplier supplier;
    String pickup;
    String dropoff;

    public RequestRide(Supplier supplier, String pickup, String dropoff) {
        this.supplier = supplier;
        this.pickup = pickup;
        this.dropoff = dropoff;
    }

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

    public List<Ride> request(){
        URL url = buildUrl();
        StringBuffer content = new StringBuffer();
        // Execute HTTP request.
        try{
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader res = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            while((inputLine = res.readLine()) != null){
                content.append(inputLine);
            }
            res.close();

        }catch(Exception e){
            System.out.println(e);
        }

        // Convert response string to JSON
        Gson g = new Gson();
        JsonObject resJson = new JsonParser().parse(content.toString()).getAsJsonObject();
        JsonArray options = resJson.getAsJsonArray("options");

        // Serialize JSON as list of Ride objects.
        List<Ride> rides = new ArrayList<Ride>();
        for(JsonElement option : options){
            JsonObject ride = option.getAsJsonObject();
            Ride rideObj = new Ride(this.supplier.getSupplierName(), ride.get("car_type").toString(), Double.parseDouble(ride.get("price").toString()));
            rides.add(rideObj);
        }

        return rides;
    }
}
