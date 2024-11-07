package com.hms.repository;

import com.hms.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

   // @Query("Delete from Property p where p.city=:cID")
    @Transactional
    void deleteByCountryId(@Param("cID") Long countryId);
    //@Query("Delete from Property p where p.location=:locationID")
    @Transactional
    void deleteByLocationId(@Param("locationID") Long locationId);
    //@Query("Delete from Property p where p.city =:cityID")
    @Transactional
    void deleteByCityId(@Param("cityID") Long cityId);

    @Query("select p from Property p JOIN p.country c JOIN p.city ci JOIN p.location l where c.name=:name or ci.name=:name or l.name=:name ")
    List<Property> searchHotels(@Param("name")String name);

}