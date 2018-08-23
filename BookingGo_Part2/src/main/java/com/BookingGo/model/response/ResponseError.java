package com.BookingGo.model.response;

/**
 * Error Response class.
 *
 */

public class ResponseError extends Response {

    String message;

    public ResponseError(String pickup, String dropoff, int passengers, String message){
        super(pickup, dropoff,passengers);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
