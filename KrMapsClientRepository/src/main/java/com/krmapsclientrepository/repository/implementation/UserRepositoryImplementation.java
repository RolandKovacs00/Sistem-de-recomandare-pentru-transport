package com.krmapsclientrepository.repository.implementation;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.dto.MedicalInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.NotFoundException;
import com.krmapsclientrepository.mapper.InstitutionMapper;
import com.krmapsclientrepository.model.util.Institution;
import com.krmapsclientrepository.model.util.Point;
import com.krmapsclientrepository.repository.UserRepository;
import com.krmapsclientrepository.service.MedicalInstitutionService;
import com.krmapsclientrepository.service.PublicInstitutionService;
import com.krmapsclientrepository.service.TransportInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepositoryImplementation implements UserRepository {

    private final EntityManager entityManager;
    private final MedicalInstitutionService medicalInstitutionService;
    private final PublicInstitutionService publicInstitutionService;
    private final TransportInstitutionService transportInstitutionService;
    private final InstitutionMapper institutionMapper;


    @Autowired
    public UserRepositoryImplementation(EntityManager entityManager,
                                        MedicalInstitutionService medicalInstitutionService,
                                        PublicInstitutionService publicInstitutionService,
                                        TransportInstitutionService transportInstitutionService,
                                        InstitutionMapper institutionMapper) {
        this.entityManager = entityManager;
        this.medicalInstitutionService = medicalInstitutionService;
        this.publicInstitutionService = publicInstitutionService;
        this.transportInstitutionService = transportInstitutionService;
        this.institutionMapper = institutionMapper;
    }

    @Override
    public BigDecimal calculateDistance(BigDecimal fromLatitude, BigDecimal fromLongitude, BigDecimal toLatitude, BigDecimal toLongitude) {
        return BigDecimal.valueOf((Double) entityManager.createNativeQuery("" +
                "SELECT ST_DistanceSphere(ST_MakePoint(" + fromLongitude + ", " + fromLatitude + ")," +
                "ST_MakePoint(" + toLongitude + ", " + toLatitude + "));").getSingleResult() * 0.001);
    }

    @Override
    public List<Institution> getLocationsFromZone(BigDecimal latitude, BigDecimal longitude, String code, Long radius) {
        Query query = entityManager.createNativeQuery(
                "SELECT table_name.id AS id, table_name.nume AS name, table_name.code AS code, ST_X(table_name.geom) AS longitude, ST_Y(table_name.geom) AS latitude" +
                        " FROM " + code + " AS table_name " +
                        "WHERE ST_DWithin(table_name.geom," + "ST_GeogFromText('POINT(" + longitude + " " + latitude + ")'), " + radius + ")"
                /*" WHERE ST_PointInsideCircle(table_name.geom, " + longitude + "," + latitude +"," + radius +" * 0.00001);"*/, Institution.class);
        return query.getResultList();
    }

    @Override
    public Institution getShortestLocationFromZone(Point point) {
        Query query = entityManager.createNativeQuery(
                "SELECT table_name.id AS id, table_name.nume AS name, table_name.code AS code," +
                        " ST_X(table_name.geom) AS longitude, ST_Y(table_name.geom) AS latitude FROM " + point.getCode()
                        + " AS table_name WHERE ST_Distance(table_name.geom, ST_SetSRID(ST_GeogFromText('POINT(" +
                        point.getLongitude() + " " + point.getLatitude() + ")'), 4326), true)" +
                        "=(SELECT MIN(ST_Distance(geom, ST_SetSRID(ST_GeogFromText('POINT(" + point.getLongitude() + " " +
                        point.getLatitude() + ")'), 4326), true)) FROM " + point.getCode() + ");"
                , Institution.class);
        return (Institution) query.getSingleResult();
    }


    private InstitutionDTO partialNameSearch(String name){

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
        List<InstitutionDTO> allInstitutions = publicInstitutionService.getAllPublicLocations()
                .stream()
                .map(institutionMapper::publicInstitutionToInstitutionDTO)
                .collect(Collectors.toList());
        allInstitutions.addAll(
                medicalInstitutionService.getAllMedicalLocations()
                        .stream()
                        .map(institutionMapper::medicalInstitutionToInstitutionDTO)
                        .collect(Collectors.toList())
        );
        allInstitutions.addAll(
                transportInstitutionService.getAllTransportLocations()
                        .stream()
                        .map(institutionMapper::transportInstitutionToInstitutionDTO)
                        .collect(Collectors.toList())
        );
        for(int index = partialNames.size() - 1; index > 0 ; index --){
            String temporaryName = partialNames.get(index);
            for(int j = 0 ; j < temporaryName.length() ; j ++){
                String temp = temporaryName.substring(0, temporaryName.length()  - j);
                for(InstitutionDTO institutionDTO : allInstitutions){
                    if(institutionDTO.getName().toLowerCase().contains(temp)){
                        return institutionDTO;
                    }
                }
            }

        }
        return null;
    }

    @Override
    public InstitutionDTO getLocationByName(String name) {
        InstitutionDTO institutionDTO = partialNameSearch(name);
        if (institutionDTO!=null) {
            return institutionDTO;
        } else {
            throw new NotFoundException();
        }
    }
}
