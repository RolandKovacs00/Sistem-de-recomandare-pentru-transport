package com.krmapsgeotools.mapper;

import com.krmapsgeotools.dto.InstitutionDTO;
import com.krmapsgeotools.models.Institution;

public interface InstitutionMapper {

    InstitutionDTO institutionToInstitutionDTO(Institution institution);
}
