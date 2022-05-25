package com.krmapsfeigngeotools.service;

import com.krmapsfeigngeotools.models.InstitutionDTO;
import com.krmapsfeigngeotools.models.ObjectWrapper;
import com.krmapsfeigngeotools.models.Point;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "krmaps-geo-tools")
public interface ClientGeoToolsServiceFeign {

    @ResponseBody
    @PostMapping("/user/location/distance")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    BigDecimal calculateDistanceBetweenTwoPoints(@RequestBody ObjectWrapper objectWrapper);

    @ResponseBody
    @PostMapping("/user/location/zone")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    List<InstitutionDTO> getSpecificLocationsFromZone(@RequestBody Point point);

    @ResponseBody
    @PostMapping("/user/location/shortest")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    InstitutionDTO getShortestLocationFromZone(@RequestBody Point point);

    @ResponseBody
    @PostMapping("/user/location/all")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    List<InstitutionDTO> getAllLocationsFromZone(@RequestBody Point point);
}