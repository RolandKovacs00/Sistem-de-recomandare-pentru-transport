package com.krmapsclientrepository.service.implementation;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.ConstraintViolationExceptionCustom;
import com.krmapsclientrepository.exceptions.utils.NotAllowedException;
import com.krmapsclientrepository.mapper.InstitutionMapper;
import com.krmapsclientrepository.model.util.Institution;
import com.krmapsclientrepository.model.util.ObjectWrapper;
import com.krmapsclientrepository.model.util.Point;
import com.krmapsclientrepository.repository.UserRepository;
import com.krmapsclientrepository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final String[] tableNames = new String []{
            "buss_stations", "hospital","pharmacy", "schools", "university"
    };
    private final Map<Integer,String> types = Map.ofEntries(
            Map.entry(1,"hospital"),
            Map.entry(2,"buss_stations"),
            Map.entry(3,"pharmacy"),
            Map.entry(4,"schools"),
            Map.entry(5,"university")
    );
    private final EntityManager entityManager;
    private final InstitutionMapper institutionMapper;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, EntityManager entityManager, InstitutionMapper institutionMapper) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.institutionMapper = institutionMapper;
    }

    private boolean checkConstraints(Point fromDistance, Point toDistance) {
        if(fromDistance.getLongitude() == null || fromDistance.getLatitude() == null){
            return false;
        } else return toDistance.getLongitude() != null && toDistance.getLatitude() != null;
    }

    @Override
    public BigDecimal calculateDistance(ObjectWrapper objectWrapper) {

        Point fromDistance = objectWrapper.getStartingDistance();
        Point toDistance = objectWrapper.getFinishDestination();
        if (checkConstraints(fromDistance, toDistance)) {
            return userRepository.calculateDistance(fromDistance.getLatitude(), fromDistance.getLongitude(),
                    toDistance.getLatitude(), toDistance.getLongitude());
        } else {
            throw new ConstraintViolationExceptionCustom();
        }
    }

    @Override
    public List<InstitutionDTO> getLocationsFromZone(Point point){
        String code = point.getCode().toLowerCase();
        if (code.equals("buss")) {
           code = "buss_stations";
           point.setCode(code);
        }
        if(types.containsValue(code)){
            return userRepository.getLocationsFromZone(point.getLatitude(), point.getLongitude(),
                    point.getCode(),point.getRadius())
                    .stream()
                    .map(institutionMapper::institutionToInstitutionDTO)
                    .collect(Collectors.toList());
        } else {
            throw new NotAllowedException();
        }
    }

    @Override
    public List<InstitutionDTO> getAllLocationsFromZone(Point point) {
        List<InstitutionDTO> institutionList = new ArrayList<>();
        for(String tableName: tableNames){
            institutionList.addAll(userRepository.getLocationsFromZone(point.getLatitude(), point.getLongitude(),
                    tableName,point.getRadius())
                .stream()
                    .map(institutionMapper::institutionToInstitutionDTO)
                    .collect(Collectors.toList())
            );
            entityManager.clear();
        }
        return institutionList;
    }

    @Override
    public InstitutionDTO getShortestLocationFromZone(Point point){
        final String code = point.getCode().toLowerCase();
        if(types.containsValue(code)){
            Institution institution = userRepository.getShortestLocationFromZone(point);
            return institutionMapper.institutionToInstitutionDTO(institution);
        } else {
            throw new NotAllowedException();
        }
    }

    @Override
    public InstitutionDTO getLocationByName(String name) {
        return userRepository.getLocationByName(name);
    }
}
