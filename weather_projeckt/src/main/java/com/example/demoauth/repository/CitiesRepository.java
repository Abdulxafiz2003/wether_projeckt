package com.example.demoauth.repository;

import com.example.demoauth.models.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {
    Cities findByCitiesName(String citiesName);
}
