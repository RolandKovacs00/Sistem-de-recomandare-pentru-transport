package com.krmapsfeigngeotools.controller;

import com.krmapsfeigngeotools.models.InstitutionDTO;
import com.krmapsfeigngeotools.models.ObjectWrapper;
import com.krmapsfeigngeotools.models.Point;
import com.krmapsfeigngeotools.service.ClientGeoToolsServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ClientGeoToolsResourceController {

    private final ClientGeoToolsServiceFeign clientGeoToolsServiceFeign;

    @Autowired
    public ClientGeoToolsResourceController(ClientGeoToolsServiceFeign clientGeoToolsServiceFeign) {
        this.clientGeoToolsServiceFeign = clientGeoToolsServiceFeign;
    }

    @ResponseBody
    @PostMapping("/user/location/distance")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    BigDecimal calculateDistanceBetweenTwoPoints(@RequestBody ObjectWrapper objectWrapper){
        return clientGeoToolsServiceFeign.calculateDistanceBetweenTwoPoints(objectWrapper);
    }

    @ResponseBody
    @PostMapping("/user/location/zone")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    List<InstitutionDTO> getSpecificLocationsFromZone(@RequestBody Point point){
        return clientGeoToolsServiceFeign.getSpecificLocationsFromZone(point);
    }

    @ResponseBody
    @PostMapping("/user/location/shortest")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    InstitutionDTO getShortestLocationFromZone(@RequestBody Point point){
        return clientGeoToolsServiceFeign.getShortestLocationFromZone(point);
    }

    @ResponseBody
    @PostMapping("/user/location/all")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    List<InstitutionDTO> getAllLocationsFromZone(@RequestBody Point point){
        return clientGeoToolsServiceFeign.getAllLocationsFromZone(point);
    }
}
