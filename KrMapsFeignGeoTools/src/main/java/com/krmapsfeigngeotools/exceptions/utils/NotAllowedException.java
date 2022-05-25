package com.krmapsfeigngeotools.exceptions.utils;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException(){
        super("Operation not permitted.");
    }
}

