package com.krmapsgeotools.mapper.implementation;

import com.krmapsgeotools.dto.InstitutionDTO;
import com.krmapsgeotools.dto.builder.InstitutionDTOBuilder;
import com.krmapsgeotools.mapper.InstitutionMapper;
import com.krmapsgeotools.models.Institution;
import org.springframework.stereotype.Component;

@Component
public class InstitutionMapperImplementation implements InstitutionMapper {

    @Override
    public InstitutionDTO institutionToInstitutionDTO(Institution institution) {
        return InstitutionDTOBuilder.builder()
                .name(institution.getName())
                .code(institution.getCode())
                .latitude(institution.getLatitude())
                .longitude(institution.getLongitude())
                .build();
    }
}
