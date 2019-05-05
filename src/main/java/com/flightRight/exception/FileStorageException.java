package com.flightRight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileStorageException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public FileStorageException(String message)
    {
        super(message);
    }

}
