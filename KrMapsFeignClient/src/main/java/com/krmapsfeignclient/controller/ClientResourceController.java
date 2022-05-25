package com.krmapsfeignclient.controller;

import com.krmapsfeignclient.model.MedicalInstitutionDTO;
import com.krmapsfeignclient.model.PublicInstitutionDTO;
import com.krmapsfeignclient.model.TransportInstitutionDTO;
import com.krmapsfeignclient.model.util.InstitutionDTO;
import com.krmapsfeignclient.model.util.ObjectWrapper;
import com.krmapsfeignclient.model.util.Point;
import com.krmapsfeignclient.service.ClientServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ClientResourceController {

    private final ClientServiceFeign clientServiceFeign;

    @Autowired
    public ClientResourceController(ClientServiceFeign clientServiceFeign) {
        this.clientServiceFeign = clientServiceFeign;
    }

    @ResponseBody
    @GetMapping("/public/locations/get")
    List<PublicInstitutionDTO> getAllPublicLocations(){

        return clientServiceFeign.getAllPublicLocations();
    }

    @ResponseBody
    @GetMapping("/public/locations/get/{type}")
    public List<PublicInstitutionDTO> getPreferredPublicLocations(@PathVariable("type") String code){

        return clientServiceFeign.getPreferredPublicLocations(code);
    }

    @ResponseBody
    @GetMapping("/public/locations/get/{code}/name/{name}")
    PublicInstitutionDTO getPublicInstitutionDTODTOLocationByName(@PathVariable("code") String code,
                                                         @PathVariable("name") String name){
        return clientServiceFeign.getPublicInstitutionDTOLocationByName(code, name);
    }

    @ResponseBody
    @GetMapping("/public/locations/get/{code}/id/{id}")
    PublicInstitutionDTO getPublicInstitutionDTODTOLocationById(@PathVariable("code") String code,
                                                       @PathVariable("id") Long id){
        return clientServiceFeign.getPublicInstitutionDTOLocationById(code, id);
    }

    @ResponseBody
    @GetMapping("/medical/locations/get")
    List<MedicalInstitutionDTO> getAllMedicalLocations(){

        return clientServiceFeign.getAllMedicalLocations();
    }

    @ResponseBody
    @GetMapping("/medical/locations/get/{type}")
    public List<MedicalInstitutionDTO> getPreferredMedicalLocations(@PathVariable("type") String code){
        return clientServiceFeign.getPreferredMedicalLocations(code);
    }

    @ResponseBody
    @GetMapping("/medical/locations/get/{code}/name/{name}")
    public MedicalInstitutionDTO getMedicalInstitutionDTODTOLocationByName(@PathVariable(name = "code") String code,
                                                                  @PathVariable(name = "name") String name){
        return clientServiceFeign.getMedicalInstitutionDTOLocationByName(code, name);
    }

    @ResponseBody
    @GetMapping("/medical/locations/get/{code}/id/{id}")
    public MedicalInstitutionDTO getMedicalInstitutionDTODTOLocationById(@PathVariable(name = "code") String code,
                                                                         @PathVariable(name = "id") Long id){
        return clientServiceFeign.getMedicalInstitutionDTOLocationById(code, id);
    }

    @ResponseBody
    @GetMapping("/transport/locations/get")
    List<TransportInstitutionDTO> getAllTransportLocations(){

        return clientServiceFeign.getAllTransportLocations();
    }

    @ResponseBody
    @GetMapping("/transport/locations/get/{type}")
    public List<TransportInstitutionDTO> getPreferredTransportLocations(@PathVariable("type") String code){
        return clientServiceFeign.getPreferredTransportLocations(code);
    }

    @ResponseBody
    @GetMapping("/transport/locations/get/bus/station/name/{name}")
    public TransportInstitutionDTO getBusStationLocationByName(@PathVariable("name") String name){
        return clientServiceFeign.getBusStationLocationByName(name);
    }

    @ResponseBody
    @GetMapping("/transport/locations/get/bus/station/id/{id}")
    public TransportInstitutionDTO getBusStationLocationById(@PathVariable("id") Long id){
        return clientServiceFeign.getBusStationLocationById(id);
    }

    @ResponseBody
    @PostMapping("/user/location/distance")
    public BigDecimal calculateDistance(@Valid @RequestBody ObjectWrapper objectWrapper){
        return clientServiceFeign.calculateDistance(objectWrapper);
    }

    @ResponseBody
    @PostMapping("/user/location/zone")
    public List<InstitutionDTO> getLocationsFromZone(@Valid @RequestBody Point point){
        return clientServiceFeign.getLocationsFromZone(point);
    }

    @ResponseBody
    @PostMapping("/user/location/all")
    public List<InstitutionDTO> getAllLocationsFromZone(@Valid @RequestBody Point point){
        return clientServiceFeign.getAllLocationsFromZone(point);
    }

    @ResponseBody
    @PostMapping("/user/location/shortest")
    InstitutionDTO getShortestLocationFromZone(@Valid @RequestBody Point point){
        return clientServiceFeign.getShortestLocationFromZone(point);
    }

    @ResponseBody
    @GetMapping("/user/location/get/name/{name}")
    public InstitutionDTO getLocationByName(@PathVariable(name = "name") String name) {
        return this.clientServiceFeign.getLocationByName(name);
    }
}
