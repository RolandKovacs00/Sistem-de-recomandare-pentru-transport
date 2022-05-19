package com.krmapsgeotools.services;

import com.krmapsgeotools.dto.InstitutionDTO;
import com.krmapsgeotools.models.ObjectWrapper;
import com.krmapsgeotools.models.Point;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    BigDecimal calculateDistanceBetweenTwoPoints(ObjectWrapper objectWrapper);

    List<InstitutionDTO> getLocationsFromZone(Point point);

    InstitutionDTO getShortestLocationFromZone(Point point);

    List<InstitutionDTO> getAllLocationsFromZone(Point point);
}
