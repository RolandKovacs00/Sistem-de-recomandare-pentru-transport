package com.krmapsclientrepository.mapper.implementation;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.dto.MedicalInstitutionDTO;
import com.krmapsclientrepository.dto.PublicInstitutionDTO;
import com.krmapsclientrepository.dto.TransportInstitutionDTO;
import com.krmapsclientrepository.dto.builder.InstitutionDTOBuilder;
import com.krmapsclientrepository.dto.builder.MedicalInstitutionDTOBuilder;
import com.krmapsclientrepository.dto.builder.PublicInstitutionDTOBuilder;
import com.krmapsclientrepository.dto.builder.TransportInstitutionDTOBuilder;
import com.krmapsclientrepository.mapper.InstitutionMapper;
import com.krmapsclientrepository.model.MedicalInstitution;
import com.krmapsclientrepository.model.PublicInstitution;
import com.krmapsclientrepository.model.TransportInstitution;
import com.krmapsclientrepository.model.util.Institution;
import org.springframework.stereotype.Component;

@Component
public class InstitutionMapperImplementation implements InstitutionMapper {

    @Override
    public MedicalInstitutionDTO medicalInstitutionToMedicalInstitutionDTO(MedicalInstitution medicalInstitution) {

        return MedicalInstitutionDTOBuilder.builder()
                .name(medicalInstitution.getName())
                .code(medicalInstitution.getCode())
                .latitude(medicalInstitution.getLatitude())
                .longitude(medicalInstitution.getLongitude())
                .build();
    }

    @Override
    public PublicInstitutionDTO publicInstitutionToPublicInstitutionDTO(PublicInstitution publicInstitution) {
        return PublicInstitutionDTOBuilder.builder()
                .name(publicInstitution.getName())
                .code(publicInstitution.getCode())
                .latitude(publicInstitution.getLatitude())
                .longitude(publicInstitution.getLongitude())
                .build();
    }

    @Override
    public TransportInstitutionDTO transportInstitutionToTransportInstitutionDTO(TransportInstitution transportInstitution) {
        return TransportInstitutionDTOBuilder.builder()
                .name(transportInstitution.getName())
                .code(transportInstitution.getCode())
                .latitude(transportInstitution.getLatitude())
                .longitude(transportInstitution.getLongitude())
                .build();
    }

    @Override
    public InstitutionDTO institutionToInstitutionDTO(Institution institution) {
        return InstitutionDTOBuilder.builder()
                .name(institution.getName())
                .code(institution.getCode())
                .latitude(institution.getLatitude())
                .longitude(institution.getLongitude())
                .build();
    }

    @Override
    public InstitutionDTO medicalInstitutionToInstitutionDTO(MedicalInstitutionDTO medicalInstitution) {
        return InstitutionDTOBuilder.builder()
                .name(medicalInstitution.getName())
                .code(medicalInstitution.getCode())
                .latitude(medicalInstitution.getLatitude())
                .longitude(medicalInstitution.getLongitude())
                .build();
    }

    @Override
    public InstitutionDTO publicInstitutionToInstitutionDTO(PublicInstitutionDTO publicInstitution) {
        return InstitutionDTOBuilder.builder()
                .name(publicInstitution.getName())
                .code(publicInstitution.getCode())
                .latitude(publicInstitution.getLatitude())
                .longitude(publicInstitution.getLongitude())
                .build();
    }

    @Override
    public InstitutionDTO transportInstitutionToInstitutionDTO(TransportInstitutionDTO transportInstitution) {
        return InstitutionDTOBuilder.builder()
                .name(transportInstitution.getName())
                .code(transportInstitution.getCode())
                .latitude(transportInstitution.getLatitude())
                .longitude(transportInstitution.getLongitude())
                .build();
    }

}
