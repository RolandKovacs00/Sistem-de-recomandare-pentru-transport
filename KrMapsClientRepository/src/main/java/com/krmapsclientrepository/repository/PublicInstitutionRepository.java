package com.krmapsclientrepository.repository;

import com.krmapsclientrepository.model.PublicInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PublicInstitutionRepository extends JpaRepository<PublicInstitution, Long> {

    @Query(value = "SELECT schools.id AS id,schools.nume AS nume, schools.code AS code" +
            ", schools.latitude AS latitude, schools.longitude AS longitude" +
            " FROM public.schools schools"
            ,nativeQuery = true)
    List<PublicInstitution> getSchoolLocations();

    @Query(value = "SELECT schools.id AS id,schools.nume AS nume, schools.code AS code" +
            ", schools.latitude AS latitude, schools.longitude AS longitude" +
            " FROM public.schools schools" +
            " WHERE schools.id = ?1"
            ,nativeQuery = true)
    PublicInstitution getSchoolById(Long id);

    @Query(value = "SELECT schools.id AS id,schools.nume AS nume, schools.code AS code" +
            ", schools.latitude AS latitude, schools.longitude AS longitude" +
            " FROM public.schools schools" +
            " WHERE LOWER(schools.nume) LIKE LOWER('%?1%');"
            ,nativeQuery = true)
    PublicInstitution getSchoolByName(String name);

    @Query(value = "SELECT university.id AS id,university.nume AS nume, university.code AS code" +
            ", university.latitude AS latitude, university.longitude AS longitude" +
            " FROM public.university university"
            ,nativeQuery = true)
    List<PublicInstitution> getUniversityLocations();

    @Query(value = "SELECT university.id AS id,university.nume AS nume, university.code AS code" +
            ", university.latitude AS latitude, university.longitude AS longitude" +
            " FROM public.university university" +
            " WHERE university.id = ?1"
            ,nativeQuery = true)
    PublicInstitution getUniversityById(Long id);

    @Query(value = "SELECT university.id AS id,university.nume AS nume, university.code AS code" +
            ", university.latitude AS latitude, university.longitude AS longitude" +
            " FROM public.university university" +
            " WHERE LOWER(university.nume) LIKE LOWER('%?1%');"
            ,nativeQuery = true)
    PublicInstitution getUniversityByName(String name);
}
