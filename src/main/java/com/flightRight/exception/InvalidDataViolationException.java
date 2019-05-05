package com.flightRight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDataViolationException extends Exception
{

    static final long serialVersionUID = -3387516993224229948L;


    public InvalidDataViolationException(String message)
    {
        super(message);
    }

}
