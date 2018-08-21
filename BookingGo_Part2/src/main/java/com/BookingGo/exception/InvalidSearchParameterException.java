package com.BookingGo.exception;

import com.BookingGo.Global;

/**
 * Exception for invalid search parameters.
 *
 */

public class InvalidSearchParameterException extends Exception {

    public InvalidSearchParameterException(){
        super(Global.MESSAGE_INVALID_INPUT);
    }
}
