package com.krmapsclientrepository.dto.builder;

import com.krmapsclientrepository.dto.PublicInstitutionDTO;
import com.krmapsclientrepository.dto.TransportInstitutionDTO;

import java.math.BigDecimal;

public class PublicInstitutionDTOBuilder {

    private String name;
    private String code;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public static PublicInstitutionDTOBuilder builder(){
        return new PublicInstitutionDTOBuilder();
    }

    public PublicInstitutionDTOBuilder name(String name){
        this.name = name;
        return this;
    }

    public PublicInstitutionDTOBuilder code(String code){
        this.code = code;
        return this;
    }

    public PublicInstitutionDTOBuilder latitude(BigDecimal latitude){
        this.latitude = latitude;
        return this;
    }

    public PublicInstitutionDTOBuilder longitude(BigDecimal longitude){
        this.longitude = longitude;
        return this;
    }

    public PublicInstitutionDTO build(){
        PublicInstitutionDTO publicInstitutionDTO = new PublicInstitutionDTO();

        publicInstitutionDTO.setCode(code);
        publicInstitutionDTO.setName(name);
        publicInstitutionDTO.setLatitude(latitude);
        publicInstitutionDTO.setLongitude(longitude);

        return publicInstitutionDTO;
    }
}
