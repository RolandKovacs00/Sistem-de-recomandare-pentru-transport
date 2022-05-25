package com.krmapsfeignclient.service;

import com.krmapsfeignclient.model.MedicalInstitutionDTO;
import com.krmapsfeignclient.model.PublicInstitutionDTO;
import com.krmapsfeignclient.model.TransportInstitutionDTO;
import com.krmapsfeignclient.model.util.InstitutionDTO;
import com.krmapsfeignclient.model.util.ObjectWrapper;
import com.krmapsfeignclient.model.util.Point;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "krmaps-client-repository")
public interface ClientServiceFeign {

    @ResponseBody
    @GetMapping("/public/locations/get")
    List<PublicInstitutionDTO> getAllPublicLocations();

    @ResponseBody
    @GetMapping("/public/locations/get/{type}")
    List<PublicInstitutionDTO> getPreferredPublicLocations(@PathVariable("type") String code);

    @ResponseBody
    @GetMapping("/public/locations/get/{code}/name/{name}")
    PublicInstitutionDTO getPublicInstitutionDTOLocationByName(@PathVariable("code") String code,
                                                         @PathVariable("name") String name);
    @ResponseBody
    @GetMapping("/public/locations/get/{code}/id/{id}")
    PublicInstitutionDTO getPublicInstitutionDTOLocationById(@PathVariable("code") String code,
                                                       @PathVariable("id") Long id);
    @ResponseBody
    @GetMapping("/medical/locations/get")
    List<MedicalInstitutionDTO> getAllMedicalLocations();

    @ResponseBody
    @GetMapping("/medical/locations/get/{type}")
    List<MedicalInstitutionDTO> getPreferredMedicalLocations(@PathVariable("type") String code);

    @ResponseBody
    @GetMapping("/medical/locations/get/{code}/name/{name}")
    MedicalInstitutionDTO getMedicalInstitutionDTOLocationByName(@PathVariable(name = "code") String code,
                                                                  @PathVariable(name = "name") String name);
    @ResponseBody
    @GetMapping("/medical/locations/get/{code}/id/{id}")
    MedicalInstitutionDTO getMedicalInstitutionDTOLocationById(@PathVariable(name = "code") String code,
                                                         @PathVariable("id") Long id);
    @ResponseBody
    @GetMapping("/transport/locations/get")
    List<TransportInstitutionDTO> getAllTransportLocations();

    @ResponseBody
    @GetMapping("/transport/locations/get/{type}")
    List<TransportInstitutionDTO> getPreferredTransportLocations(@PathVariable("type") String code);

    @ResponseBody
    @GetMapping("/transport/locations/get/bus/station/name/{name}")
    TransportInstitutionDTO getBusStationLocationByName(@PathVariable("name") String name);

    @ResponseBody
    @GetMapping("/transport/locations/get/bus/station/id/{id}")
    TransportInstitutionDTO getBusStationLocationById(@PathVariable("id") Long id);

    @ResponseBody
    @PostMapping("/user/location/distance")
    BigDecimal calculateDistance(@Valid @RequestBody ObjectWrapper objectWrapper);

    @ResponseBody
    @PostMapping("/user/location/zone")
    List<InstitutionDTO> getLocationsFromZone(@Valid @RequestBody Point point);

    @ResponseBody
    @PostMapping("/user/location/all")
    List<InstitutionDTO> getAllLocationsFromZone(@Valid @RequestBody Point point);

    @ResponseBody
    @PostMapping("/user/location/shortest")
    InstitutionDTO getShortestLocationFromZone(@Valid @RequestBody Point point);

    @ResponseBody
    @GetMapping("/user/location/get/name/{name}")
    InstitutionDTO getLocationByName(@PathVariable(name = "name") String name);
}