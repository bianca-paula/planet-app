package com.abac.planetapp.controller;


import com.abac.planetapp.controller.AuthenticationResource;
import com.abac.planetapp.model.Crew;
import com.abac.planetapp.model.Planet;
import com.abac.planetapp.service.PlanetService;
import org.hibernate.mapping.Any;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@CrossOrigin(origins = "**", allowedHeaders = "*")
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

    @GetMapping("/getcrew/{planetID}")
    public ResponseEntity<String> getPlanetCrew(@PathVariable("planetID") Long planetID){

        HashMap<String, Long> params = new HashMap<>();
        Planet planet = planetService.findPlanetById(planetID);
        Long id = planet.getCrew().getId();
        params.put("id", id);


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(AuthenticationResource.CurrentJWTToken);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> responseCrew=restTemplate.exchange("http://localhost:8080/crew/find/"+id.toString(), HttpMethod.GET, entity, String.class);

        return new ResponseEntity(responseCrew.getBody(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") Long id) {
        Planet planet = planetService.findPlanetById(id);
        return new ResponseEntity<>(planet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Planet> addPlanet(@Valid @RequestBody Planet planet) throws Exception {
        try {
            Planet newPlanet = planetService.addPlanet(planet);
            return new ResponseEntity<>(newPlanet, HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
