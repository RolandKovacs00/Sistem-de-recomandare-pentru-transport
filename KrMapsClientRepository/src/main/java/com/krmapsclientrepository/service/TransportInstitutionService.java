package com.krmapsclientrepository.service;

import com.krmapsclientrepository.dto.TransportInstitutionDTO;

import java.util.List;

public interface TransportInstitutionService {

    List<TransportInstitutionDTO> getPreferredTransportLocations(String code);

    TransportInstitutionDTO getBusStationLocationByName(String name);

    TransportInstitutionDTO getBusStationLocationById(Long id);

    List<TransportInstitutionDTO> getAllTransportLocations();
}
