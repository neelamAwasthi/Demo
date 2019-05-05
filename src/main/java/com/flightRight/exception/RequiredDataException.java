package com.flightRight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequiredDataException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public RequiredDataException(String message)
    {
        super(message);
    }

}
