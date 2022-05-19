package com.krmapsgeotools.services.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Utils {

    public static final Map<Integer,String> types = Map.ofEntries(
            Map.entry(1,"hospital"),
            Map.entry(2,"bus-stop"),
            Map.entry(3,"pharmacy"),
            Map.entry(4,"school"),
            Map.entry(5,"university")
    );
}
