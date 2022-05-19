package com.krmapsgeotools.geotools.implementation;

import com.krmapsgeotools.exceptions.utils.NotAllowedException;
import com.krmapsgeotools.geotools.UserGeoTools;
import com.krmapsgeotools.models.Institution;
import com.krmapsgeotools.models.ObjectWrapper;
import com.krmapsgeotools.models.Point;
import com.krmapsgeotools.services.util.Utils;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import si.uom.SI;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Length;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserGeoToolsImplementation implements UserGeoTools {

    @Override
    public BigDecimal calculateDistanceBetweenTwoPoints(ObjectWrapper objectWrapper){

        double distance = 0.0;
        try {
            Coordinate start = new Coordinate(objectWrapper.getStartingDistance().getLatitude().doubleValue(),
                    objectWrapper.getStartingDistance().getLongitude().doubleValue());
            Coordinate end = new Coordinate(objectWrapper.getFinishDestination().getLatitude().doubleValue(),
                    objectWrapper.getFinishDestination().getLongitude().doubleValue());

            CoordinateReferenceSystem crs = null;
            try {
                crs = CRS.decode("epsg:4326");
            } catch (FactoryException e) {
                e.printStackTrace();
            }
            GeodeticCalculator gc = new GeodeticCalculator(crs);
            gc.setStartingPosition(JTS.toDirectPosition(start, crs));
            gc.setDestinationPosition(JTS.toDirectPosition(end, crs));

            double totalMeters = gc.getOrthodromicDistance();
            distance = totalMeters / 1000;

        } catch (TransformException exception){
            exception.printStackTrace();
        }

        return BigDecimal.valueOf(distance);
    }

    public Geometry bufferPoint(Quantity<Length> distance, CoordinateReferenceSystem origCRS, Geometry geom) {
        Geometry pGeom = geom;
        MathTransform toTransform, fromTransform = null;
        Unit<Length> unit = distance.getUnit();
        if (!(origCRS instanceof ProjectedCRS)) {

            double x = geom.getCoordinate().x;
            double y = geom.getCoordinate().y;

            String code = "AUTO:42001," + x + "," + y;
            CoordinateReferenceSystem auto;
            try {
                auto = CRS.decode(code);
                toTransform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, auto);
                fromTransform = CRS.findMathTransform(auto, DefaultGeographicCRS.WGS84);
                pGeom = JTS.transform(geom, toTransform);
                unit = SI.METRE;

            } catch (MismatchedDimensionException | TransformException | FactoryException e) {
                e.printStackTrace();
            }

        } else {
            unit = (Unit<Length>) origCRS.getCoordinateSystem().getAxis(0).getUnit();
        }


        // buffer
        Geometry out = pGeom.buffer(distance.getValue().doubleValue());
        Geometry retGeom = out;
        // reproject the geometry to the original projection
        if (!(origCRS instanceof ProjectedCRS)) {
            try {
                retGeom = JTS.transform(out, fromTransform);

            } catch (MismatchedDimensionException | TransformException e) {
                e.printStackTrace();
            }
        }
        return retGeom;
    }

    private static String getLocationType(String code){
        code = code.toLowerCase();
        switch (code){
            case ("hospital"):
            case ("pharmacy"):
                return "medical";
            case ("school"):
            case ("university"):
                return "public";
            case ("bus-stop"):
                return "transport";
            default:
                throw new NotAllowedException();
        }
    }

    private static List<Institution> getLocations(String locationType)
    {
        String type = getLocationType(locationType);
        final String uri = "http://ec2-13-59-137-49.us-east-2.compute.amazonaws.com:8183/" + type + "/locations/get/" + locationType;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Institution>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public List<Institution> calculateLocations(Point point, String code){

        Quantity<Length> dist = Quantities.getQuantity(point.getRadius(), SI.METRE);
        GeometryFactory gf = new GeometryFactory();
        org.locationtech.jts.geom.Point p = gf.createPoint(new Coordinate(point.getLatitude().doubleValue(),
                point.getLongitude().doubleValue()));
        Geometry geometry = bufferPoint(dist, DefaultGeographicCRS.WGS84, p);

        List<Institution> list = getLocations(code);
        org.locationtech.jts.geom.Point temp;
        List<Institution> okList = new ArrayList<>();
        for (Institution institution : list) {
            double latitude = institution.getLatitude().doubleValue();
            double longitude = institution.getLongitude().doubleValue();
            temp = gf.createPoint(new Coordinate(latitude, longitude));
            if(geometry.contains(temp)){
                okList.add(institution);
            }
        }
        return okList;
    }

    @Override
    public List<Institution> getLocationsFromZone(Point point) {
        return calculateLocations(point, point.getCode());
    }

    @Override
    public Institution getShortestLocationFromZone(Point point) {
        List<Institution> institutionList = getLocations(point.getCode());
        Institution smallestDistance = null;
        BigDecimal minimum = BigDecimal.valueOf(99999999999L);
        for(Institution institution : institutionList){
            Point finishingPoint = Point.builder()
                    .code("")
                    .radius(0L)
                    .latitude(institution.getLatitude())
                    .longitude(institution.getLongitude())
                    .build();
            ObjectWrapper objectWrapper = ObjectWrapper.builder()
                    .startingDistance(point)
                    .finishDestination(finishingPoint)
                    .build();
            BigDecimal value = calculateDistanceBetweenTwoPoints(objectWrapper);
            if(minimum.compareTo(value) > 0){
                minimum = value;
                smallestDistance = institution;
            }
        }
        return smallestDistance;
    }

    @Override
    public List<Institution> getAllLocationsFromZone(Point point) {
        List<Institution> institutionList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : Utils.types.entrySet()) {
            institutionList.addAll(calculateLocations(point, entry.getValue()));
        }
        return institutionList;
    }
}
