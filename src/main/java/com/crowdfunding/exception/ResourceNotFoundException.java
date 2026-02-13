package com.crowdfunding.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends PlatformException {
    public ResourceNotFoundException(String message) { super(message); }
}