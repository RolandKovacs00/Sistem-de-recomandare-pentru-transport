package com.krmapsclientrepository.service;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.model.util.ObjectWrapper;
import com.krmapsclientrepository.model.util.Point;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    BigDecimal calculateDistance(ObjectWrapper objectWrapper);

    List<InstitutionDTO> getLocationsFromZone(Point point);

    List<InstitutionDTO> getAllLocationsFromZone(Point point);

    InstitutionDTO getShortestLocationFromZone(Point point);

    InstitutionDTO getLocationByName(String name);
}
