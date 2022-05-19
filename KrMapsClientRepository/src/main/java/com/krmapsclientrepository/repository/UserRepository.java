package com.krmapsclientrepository.repository;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.model.util.Institution;
import com.krmapsclientrepository.model.util.Point;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository{

    BigDecimal calculateDistance(BigDecimal fromLatitude, BigDecimal fromLongitude,
                             BigDecimal toLatitude, BigDecimal toLongitude);

    List<Institution> getLocationsFromZone(BigDecimal latitude, BigDecimal longitude,
                                           String code, Long radius);

    Institution getShortestLocationFromZone(Point point);

    InstitutionDTO getLocationByName(String name);
}
