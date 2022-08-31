package com.abac.planetapp;

import com.abac.planetapp.model.AuthRequest;
import com.abac.planetapp.model.Planet;
import com.abac.planetapp.model.User;
import com.abac.planetapp.service.PlanetService;
import com.abac.planetapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
public class PlanetResource {
    private final PlanetService planetService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
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
    public ResponseEntity<Planet> addPlanet(@RequestBody Planet planet) throws Exception {
        try {
            Planet newPlanet = planetService.addPlanet(planet);
            return new ResponseEntity<>(newPlanet, HttpStatus.CREATED);
        }
        catch (Exception ex){
            System.out.println("ID EROARE DIN SERVICE");
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

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())

            );
        }
        catch (Exception exception){
            throw new Exception("Invalid username/password!");
        }
        return jwtUtil.generateToken(authRequest.getUserName());

    }

}
