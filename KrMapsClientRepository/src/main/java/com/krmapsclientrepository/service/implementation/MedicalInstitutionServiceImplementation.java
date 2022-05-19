package com.krmapsclientrepository.service.implementation;

import com.krmapsclientrepository.dto.MedicalInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.NotAllowedException;
import com.krmapsclientrepository.exceptions.utils.NotFoundException;
import com.krmapsclientrepository.mapper.InstitutionMapper;
import com.krmapsclientrepository.model.MedicalInstitution;
import com.krmapsclientrepository.repository.MedicalInstitutionRepository;
import com.krmapsclientrepository.service.MedicalInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalInstitutionServiceImplementation implements MedicalInstitutionService {

    private final MedicalInstitutionRepository medicalInstitutionRepository;
    private final InstitutionMapper institutionMapper;
    private final EntityManager entityManager;

    @Autowired
    public MedicalInstitutionServiceImplementation(MedicalInstitutionRepository medicalInstitutionRepository, InstitutionMapper institutionMapper, EntityManager entityManager) {
        this.medicalInstitutionRepository = medicalInstitutionRepository;
        this.institutionMapper = institutionMapper;
        this.entityManager = entityManager;
    }

    private MedicalInstitutionDTO partialNameSearch(String name){

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
        List<MedicalInstitutionDTO> medicalInstitutionDTOS = new ArrayList<>(getAllMedicalLocations());
        for(int index = partialNames.size() - 1; index > 0 ; index --){
            String temporaryName = partialNames.get(index);
            for(int j = 0 ; j < temporaryName.length() ; j ++){
                String temp = temporaryName.substring(0, temporaryName.length()  - j);
                for(MedicalInstitutionDTO medicalInstitutionDTO : medicalInstitutionDTOS){
                    if(medicalInstitutionDTO.getName().toLowerCase().contains(temp)){
                        return medicalInstitutionDTO;
                    }
                }
            }

        }
        return null;
    }

    @Override
    public List<MedicalInstitutionDTO> getPreferredMedicalLocations(String code) {

        code = code.toLowerCase();
        switch (code){
            case "hospital":{
                return medicalInstitutionRepository.getHospitalLocations()
                        .stream()
                        .map(institutionMapper::medicalInstitutionToMedicalInstitutionDTO)
                        .collect(Collectors.toList());
            }
            case "pharmacy":{
                return medicalInstitutionRepository.getPharmacyLocations()
                        .stream()
                        .map(institutionMapper::medicalInstitutionToMedicalInstitutionDTO)
                        .collect(Collectors.toList());
            }

            default:{
                throw new NotAllowedException();
            }
        }
    }

    @Override
    public List<MedicalInstitutionDTO> getAllMedicalLocations() {

        List<MedicalInstitutionDTO> medicalInstitutionDTOS = medicalInstitutionRepository.getHospitalLocations()
                .stream()
                .map(institutionMapper::medicalInstitutionToMedicalInstitutionDTO)
                .collect(Collectors.toList());
        entityManager.clear();
        medicalInstitutionDTOS.addAll(
                medicalInstitutionRepository.getPharmacyLocations()
                .stream()
                .map(institutionMapper::medicalInstitutionToMedicalInstitutionDTO)
                .collect(Collectors.toList())
        );
        return medicalInstitutionDTOS;
    }

    @Override
    public MedicalInstitutionDTO getMedicalInstitutionByName(String code, String name) {

        String newCode = code.toLowerCase();
        switch (newCode){
            case "hospital":{
                MedicalInstitution medicalInstitution =
                        medicalInstitutionRepository.getHospitalByName(name);
                if(medicalInstitution == null){
                    MedicalInstitutionDTO medicalInstitutionDTO = partialNameSearch(name);
                    if(medicalInstitutionDTO != null){
                        return medicalInstitutionDTO;
                    } else {
                        throw new NotFoundException();
                    }
                } else {
                    return institutionMapper.medicalInstitutionToMedicalInstitutionDTO(medicalInstitution);
                }
            }
            case "pharmacy":{
                MedicalInstitution medicalInstitution =
                        medicalInstitutionRepository.getPharmacyByName(name);
                if(medicalInstitution == null){
                    throw new NotFoundException();
                } else {
                    return institutionMapper.medicalInstitutionToMedicalInstitutionDTO(medicalInstitution);
                }
            }
            default: {
                throw new NotAllowedException();
            }
        }
    }

    @Override
    public MedicalInstitutionDTO getMedicalInstitutionById(String code, Long id) {
        String newCode = code.toLowerCase();
        switch (newCode){
            case "hospital":{
                MedicalInstitution medicalInstitution =
                        medicalInstitutionRepository.getHospitalById(id);
                if(medicalInstitution == null){
                    throw new NotFoundException();
                } else {
                    return institutionMapper.medicalInstitutionToMedicalInstitutionDTO(medicalInstitution);
                }
            }
            case "pharmacy":{
                MedicalInstitution medicalInstitution =
                        medicalInstitutionRepository.getPharmacyById(id);
                if(medicalInstitution == null){
                    throw new NotFoundException();
                } else {
                    return institutionMapper.medicalInstitutionToMedicalInstitutionDTO(medicalInstitution);
                }
            }
            default:{
                throw new NotAllowedException();
            }
        }
    }
}
