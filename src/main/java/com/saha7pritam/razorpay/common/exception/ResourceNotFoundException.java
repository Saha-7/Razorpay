package com.saha7pritam.razorpay.common.exception;


import lombok.Getter;

import java.util.UUID;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final Object identifier;

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(resourceName + "not found with identifier: " + identifier);
        this.resourceName = resourceName;
        this.identifier = identifier;
    }

}
