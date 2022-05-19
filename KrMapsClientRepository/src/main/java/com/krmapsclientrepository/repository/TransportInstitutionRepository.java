package com.krmapsclientrepository.repository;

import com.krmapsclientrepository.model.TransportInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TransportInstitutionRepository extends JpaRepository<TransportInstitution, Long> {

    @Query(value = "SELECT buss_stations.id AS id,buss_stations.nume AS nume, buss_stations.code AS code" +
            ", buss_stations.latitude AS latitude, buss_stations.longitude AS longitude" +
            " FROM public.buss_stations buss_stations"
            ,nativeQuery = true)
    List<TransportInstitution> getBusStationLocations();

    @Query(value = "SELECT buss_stations.id AS id,buss_stations.nume AS nume, buss_stations.code AS code" +
            ", buss_stations.latitude AS latitude, buss_stations.longitude AS longitude" +
            " FROM public.buss_stations buss_stations" +
            " WHERE LOWER(buss_stations.nume) LIKE LOWER('%?1%');"
            ,nativeQuery = true)
    TransportInstitution getBusStationLocationByName(String name);

    @Query(value = "SELECT buss_stations.id AS id,buss_stations.nume AS nume, buss_stations.code AS code" +
            ", buss_stations.latitude AS latitude, buss_stations.longitude AS longitude" +
            " FROM public.buss_stations buss_stations" +
            " WHERE buss_stations.id = ?1"
            ,nativeQuery = true)
    TransportInstitution getBusStationLocationById(Long id);
}
