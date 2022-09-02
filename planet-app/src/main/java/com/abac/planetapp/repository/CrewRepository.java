package com.abac.planetapp.repository;

import com.abac.planetapp.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrewRepository extends JpaRepository<Crew, Long> {
    Optional<Crew> findCrewById(Long id);
}
