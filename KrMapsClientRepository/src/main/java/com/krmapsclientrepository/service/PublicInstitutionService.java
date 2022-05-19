package com.krmapsclientrepository.service;


import com.krmapsclientrepository.dto.PublicInstitutionDTO;

import java.util.List;

public interface PublicInstitutionService {

    List<PublicInstitutionDTO> getAllPublicLocations();

    List<PublicInstitutionDTO> getPreferredPublicLocations(String code);

    PublicInstitutionDTO getPublicInstitutionByName(String code, String name);

    PublicInstitutionDTO getPublicInstitutionById(String code, Long id);
}
