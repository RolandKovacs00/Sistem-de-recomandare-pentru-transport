CREATE OR REPLACE FUNCTION calculate_distance(latitudeFirstPoint float, LongitudeFirstPoint float,
 latitudeSecondPoint float, longitudeSecondPoint float, units varchar)
    RETURNS float AS $dist$
    DECLARE
        dist float = 0;
        radlat1 float;
        radlat2 float;
        theta float;
        radtheta float;
    BEGIN
        IF latitudeFirstPoint = latitudeSecondPoint OR LongitudeFirstPoint = longitudeSecondPoint
            THEN RETURN dist;
        ELSE
            radlat1 = pi() * latitudeFirstPoint / 180;
            radlat2 = pi() * latitudeSecondPoint / 180;
            theta = LongitudeFirstPoint - longitudeSecondPoint;
            radtheta = pi() * theta / 180;
            dist = sin(radlat1) * sin(radlat2) + cos(radlat1) * cos(radlat2) * cos(radtheta);

            IF dist > 1 THEN dist = 1; END IF;

            dist = acos(dist);
            dist = dist * 180 / pi();
            dist = dist * 60 * 1.1515;

            IF units = 'K' THEN dist = dist * 1.609344; END IF;
            IF units = 'N' THEN dist = dist * 0.8684; END IF;

            RETURN dist;
        END IF;
    END;
$dist$ LANGUAGE plpgsql;
