package com.abac.planetapp.service;

import com.abac.planetapp.exception.PlanetNotFoundException;
import com.abac.planetapp.model.Planet;
import com.abac.planetapp.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetService {
    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet addPlanet(Planet planet){
        return planetRepository.save(planet);
    }

    public List<Planet> findAllPlanets(){
        return planetRepository.findAll();
    }

    public Planet updatePlanet(Planet planet){
        return planetRepository.save(planet);
    }

    public void deletePlanet(Long id){
        planetRepository.deletePlanetById(id);
    }

    public Planet findPlanetById(Long id){
        return planetRepository.findPlanetById(id).orElseThrow(() -> new PlanetNotFoundException("Planet by id " + id + " was not found!"));
    }
}
