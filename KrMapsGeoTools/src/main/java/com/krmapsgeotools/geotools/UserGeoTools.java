package com.krmapsgeotools.geotools;

import com.krmapsgeotools.models.Institution;
import com.krmapsgeotools.models.ObjectWrapper;
import com.krmapsgeotools.models.Point;

import java.math.BigDecimal;
import java.util.List;

public interface UserGeoTools {

    BigDecimal calculateDistanceBetweenTwoPoints(ObjectWrapper objectWrapper);

    List<Institution> getLocationsFromZone(Point point);

    Institution getShortestLocationFromZone(Point point);

    List<Institution> getAllLocationsFromZone(Point point);
}
