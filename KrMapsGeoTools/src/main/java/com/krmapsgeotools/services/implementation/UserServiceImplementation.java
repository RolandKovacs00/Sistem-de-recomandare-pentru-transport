package com.krmapsgeotools.services.implementation;

import com.krmapsgeotools.dto.InstitutionDTO;
import com.krmapsgeotools.exceptions.utils.ConstraintViolationExceptionCustom;
import com.krmapsgeotools.exceptions.utils.NotAllowedException;
import com.krmapsgeotools.geotools.UserGeoTools;
import com.krmapsgeotools.mapper.InstitutionMapper;
import com.krmapsgeotools.models.Institution;
import com.krmapsgeotools.models.ObjectWrapper;
import com.krmapsgeotools.models.Point;
import com.krmapsgeotools.services.UserService;
import com.krmapsgeotools.services.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserGeoTools userGeoTools;

    private final InstitutionMapper institutionMapper;

    @Autowired
    public UserServiceImplementation(UserGeoTools userGeoTools, InstitutionMapper institutionMapper) {
        this.userGeoTools = userGeoTools;
        this.institutionMapper = institutionMapper;
    }

    private boolean checkConstraints(Point fromDistance, Point toDistance) {
        if(fromDistance.getLongitude() == null || fromDistance.getLatitude() == null){
            return false;
        } else return toDistance.getLongitude() != null && toDistance.getLatitude() != null;
    }

    @Override
    public BigDecimal calculateDistanceBetweenTwoPoints(ObjectWrapper objectWrapper) {
        Point fromDistance = objectWrapper.getStartingDistance();
        Point toDistance = objectWrapper.getFinishDestination();
        if (checkConstraints(fromDistance, toDistance)) {
            return userGeoTools.calculateDistanceBetweenTwoPoints(objectWrapper);

        } else {
            throw new ConstraintViolationExceptionCustom();
        }
    }

    @Override
    public List<InstitutionDTO> getLocationsFromZone(Point point) {
        String code = point.getCode().toLowerCase();
        if (code.equals("buss")) {
            code = "bus-stop";
            point.setCode(code);
        } else if (code.equals("schools")) {
            code = "school";
            point.setCode(code);
        }
        if(Utils.types.containsValue(code)) {
            return userGeoTools.getLocationsFromZone(point)
                    .stream()
                    .map(institutionMapper::institutionToInstitutionDTO)
                    .collect(Collectors.toList());
        } else {
            throw new NotAllowedException();
        }
    }

    @Override
    public InstitutionDTO getShortestLocationFromZone(Point point) {
        final String code = point.getCode().toLowerCase();
        if(Utils.types.containsValue(code)) {
            Institution institution = userGeoTools.getShortestLocationFromZone(point);
            return institutionMapper.institutionToInstitutionDTO(institution);
        } else {
            throw new NotAllowedException();
        }
    }

    @Override
    public List<InstitutionDTO> getAllLocationsFromZone(Point point) {
        return userGeoTools.getAllLocationsFromZone(point)
                .stream()
                .map(institutionMapper::institutionToInstitutionDTO)
                .collect(Collectors.toList());
    }
}
