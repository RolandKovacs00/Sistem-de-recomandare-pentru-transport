package com.krmapsclientrepository.dto.builder;

import com.krmapsclientrepository.dto.InstitutionDTO;

import java.math.BigDecimal;

public class InstitutionDTOBuilder {

    private String name;
    private String code;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public static InstitutionDTOBuilder builder(){
        return new InstitutionDTOBuilder();
    }

    public InstitutionDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public InstitutionDTOBuilder code(String code){
        this.code = code;
        return this;
    }

    public InstitutionDTOBuilder latitude(BigDecimal latitude){
        this.latitude = latitude;
        return this;
    }

    public InstitutionDTOBuilder longitude(BigDecimal longitude){
        this.longitude = longitude;
        return this;
    }

    public InstitutionDTO build(){
        InstitutionDTO institutionDTO = new InstitutionDTO();

        institutionDTO.setCode(code);
        institutionDTO.setName(name);
        institutionDTO.setLatitude(latitude);
        institutionDTO.setLongitude(longitude);

        return institutionDTO;
    }
}
