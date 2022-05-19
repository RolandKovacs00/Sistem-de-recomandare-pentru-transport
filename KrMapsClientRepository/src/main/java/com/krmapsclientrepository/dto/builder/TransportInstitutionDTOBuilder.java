package com.krmapsclientrepository.dto.builder;

import com.krmapsclientrepository.dto.TransportInstitutionDTO;

import java.math.BigDecimal;

public class TransportInstitutionDTOBuilder {

    private String name;
    private String code;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public static TransportInstitutionDTOBuilder builder(){
        return new TransportInstitutionDTOBuilder();
    }

    public TransportInstitutionDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public TransportInstitutionDTOBuilder code(String code){
        this.code = code;
        return this;
    }

    public TransportInstitutionDTOBuilder latitude(BigDecimal latitude){
        this.latitude = latitude;
        return this;
    }

    public TransportInstitutionDTOBuilder longitude(BigDecimal longitude){
        this.longitude = longitude;
        return this;
    }

    public TransportInstitutionDTO build(){
        TransportInstitutionDTO transportInstitutionDTO = new TransportInstitutionDTO();

        transportInstitutionDTO.setCode(code);
        transportInstitutionDTO.setName(name);
        transportInstitutionDTO.setLatitude(latitude);
        transportInstitutionDTO.setLongitude(longitude);

        return transportInstitutionDTO;
    }
}
