package com.BookingGo;


/**
 * A global class for static data.
 *
 */

public class Global {

    public static final int HTTP_RESPONSE_TIMEOUT = 2000;

    public static final String COORDINATE_PATTERN = "^[-]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";

    public static final String MESSAGE_MISSING_PARAMETERS = "Missing parameters! \n" +
            "There should be 2: <pickup> <dropoff>";

    public static final String MESSAGE_INVALID_INPUT = "Invalid input!\n" +
            "Please make sure your location coordinates are properly formed, e.g.:\n" +
            "51.470020,-0.454295";

    public static final String MESSAGE_SEARCHING = "Searching for your ride (this may take a few seconds)...";

    public static final String MESSAGE_NO_RIDES = "Sorry there are currently no rides matching your criteria.";

}
