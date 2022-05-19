package com.krmapsclientrepository.mapper;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.dto.MedicalInstitutionDTO;
import com.krmapsclientrepository.dto.PublicInstitutionDTO;
import com.krmapsclientrepository.dto.TransportInstitutionDTO;
import com.krmapsclientrepository.model.MedicalInstitution;
import com.krmapsclientrepository.model.PublicInstitution;
import com.krmapsclientrepository.model.TransportInstitution;
import com.krmapsclientrepository.model.util.Institution;

public interface InstitutionMapper {

    MedicalInstitutionDTO medicalInstitutionToMedicalInstitutionDTO(MedicalInstitution medicalInstitution);

    PublicInstitutionDTO publicInstitutionToPublicInstitutionDTO(PublicInstitution publicInstitution);

    TransportInstitutionDTO transportInstitutionToTransportInstitutionDTO(TransportInstitution transportInstitution);

    InstitutionDTO institutionToInstitutionDTO(Institution institution);

    InstitutionDTO medicalInstitutionToInstitutionDTO(MedicalInstitutionDTO medicalInstitution);

    InstitutionDTO publicInstitutionToInstitutionDTO(PublicInstitutionDTO publicInstitution);

    InstitutionDTO transportInstitutionToInstitutionDTO(TransportInstitutionDTO transportInstitution);

}
