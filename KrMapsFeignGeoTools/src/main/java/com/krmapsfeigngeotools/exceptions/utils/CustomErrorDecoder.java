package com.krmapsfeigngeotools.exceptions.utils;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){

            case 404:{
                return new NotFoundException();
            }

            case 400:{
                return new NotAllowedException();
            }
            default:{
                return defaultErrorDecoder.decode(methodKey, response);
            }
        }
    }
}