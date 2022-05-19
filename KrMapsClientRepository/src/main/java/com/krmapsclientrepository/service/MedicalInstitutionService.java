package com.krmapsclientrepository.service;

import com.krmapsclientrepository.dto.MedicalInstitutionDTO;

import java.util.List;

public interface MedicalInstitutionService {

    List<MedicalInstitutionDTO> getPreferredMedicalLocations(String code);

    List<MedicalInstitutionDTO> getAllMedicalLocations();

    MedicalInstitutionDTO getMedicalInstitutionByName(String code, String name);

    MedicalInstitutionDTO getMedicalInstitutionById(String code, Long id);
}
