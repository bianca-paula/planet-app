package com.abac.planetapp.repository;

import com.abac.planetapp.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    void deletePlanetById(Long id);
    Optional<Planet> findPlanetById(Long id);
}
