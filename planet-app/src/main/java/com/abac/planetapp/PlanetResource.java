package com.abac.planetapp;

import com.abac.planetapp.model.Planet;
import com.abac.planetapp.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
public class PlanetResource {
    private final PlanetService planetService;

    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> planets = planetService.findAllPlanets();
        return new ResponseEntity<>(planets, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") Long id) {
        Planet planet = planetService.findPlanetById(id);
        return new ResponseEntity<>(planet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet){
        Planet newPlanet = planetService.addPlanet(planet);
        return new ResponseEntity<>(newPlanet, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Planet> updatePlanet(@RequestBody Planet planet){
        Planet updatePlanet = planetService.updatePlanet(planet);
        return new ResponseEntity<>(updatePlanet, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable("id") Long id){
        planetService.deletePlanet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
