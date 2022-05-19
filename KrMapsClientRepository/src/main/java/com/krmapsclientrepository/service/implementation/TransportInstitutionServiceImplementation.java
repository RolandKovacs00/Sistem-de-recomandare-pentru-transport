package com.krmapsclientrepository.service.implementation;

import com.krmapsclientrepository.dto.TransportInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.NotAllowedException;
import com.krmapsclientrepository.exceptions.utils.NotFoundException;
import com.krmapsclientrepository.mapper.InstitutionMapper;
import com.krmapsclientrepository.model.TransportInstitution;
import com.krmapsclientrepository.repository.TransportInstitutionRepository;
import com.krmapsclientrepository.service.TransportInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportInstitutionServiceImplementation implements TransportInstitutionService {

    private final TransportInstitutionRepository transportInstitutionRepository;
    private final InstitutionMapper institutionMapper;

    @Autowired
    public TransportInstitutionServiceImplementation(TransportInstitutionRepository transportInstitutionRepository, InstitutionMapper institutionMapper) {
        this.transportInstitutionRepository = transportInstitutionRepository;
        this.institutionMapper = institutionMapper;
    }

    private TransportInstitutionDTO partialNameSearch(String name){

        name = name.toLowerCase();
        ArrayList<String> partialNames = new ArrayList<>();
        String[] strings = name.split(" ");
        for(int index = 0 ; index < strings.length ; index++){
            String temp = "";
            for(int j = 0 ; j < index + 1 ; j++){
                temp = String.format("%s%s ", temp, strings[j]);
            }
            partialNames.add(temp);
        }
        List<TransportInstitutionDTO> TransportInstitutionDTOS = new ArrayList<>(getAllTransportLocations());
        for(int index = partialNames.size() - 1; index > 0 ; index --){
            String temporaryName = partialNames.get(index);
            for(int j = 0 ; j < temporaryName.length() ; j ++){
                String temp = temporaryName.substring(0, temporaryName.length()  - j);
                for(TransportInstitutionDTO TransportInstitutionDTO : TransportInstitutionDTOS){
                    if(TransportInstitutionDTO.getName().toLowerCase().contains(temp)){
                        return TransportInstitutionDTO;
                    }
                }
            }

        }
        return null;
    }
    
    //Over time this method will be updated
    @Override
    public List<TransportInstitutionDTO> getPreferredTransportLocations(String code) {

        code = code.toLowerCase();
        if ("bus-stop".equals(code)) {
            return transportInstitutionRepository.getBusStationLocations()
                    .stream()
                    .map(institutionMapper::transportInstitutionToTransportInstitutionDTO)
                    .collect(Collectors.toList());
        }
        throw new NotAllowedException();
    }

    @Override
    public TransportInstitutionDTO getBusStationLocationByName(String name) {

        //this will change in time as the application grows
        //String code = "bus-stop";
        TransportInstitution transportInstitution =
                transportInstitutionRepository.getBusStationLocationByName(name);
        if(transportInstitution == null){
            TransportInstitutionDTO transportInstitutionDTO = partialNameSearch(name);
            if(transportInstitutionDTO != null){
                return transportInstitutionDTO;
            } else {
                throw new NotFoundException();
            }
        } else {
            return institutionMapper.transportInstitutionToTransportInstitutionDTO(transportInstitution);
        }
    }

    @Override
    public TransportInstitutionDTO getBusStationLocationById(Long id) {
        //String code = "bus-stop";
        TransportInstitution transportInstitution =
                transportInstitutionRepository.getBusStationLocationById(id);
        if (transportInstitution == null) {
            throw new NotFoundException();
        } else {
            return institutionMapper.transportInstitutionToTransportInstitutionDTO(transportInstitution);
        }
    }

    //Over time this method will be updated
    @Override
    public List<TransportInstitutionDTO> getAllTransportLocations() {

        return transportInstitutionRepository.getBusStationLocations()
                .stream()
                .map(institutionMapper::transportInstitutionToTransportInstitutionDTO)
                .collect(Collectors.toList());
    }
}
