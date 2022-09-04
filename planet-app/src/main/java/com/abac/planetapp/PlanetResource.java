package com.abac.planetapp;


import com.abac.planetapp.model.AuthRequest;
import com.abac.planetapp.model.Crew;
import com.abac.planetapp.model.Planet;
import com.abac.planetapp.model.User;
import com.abac.planetapp.service.PlanetService;
import com.abac.planetapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "**", allowedHeaders = "*")
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin
@RequestMapping("/planet")
public class PlanetResource {
    private final PlanetService planetService;

//    @RequestMapping(value= "/**", method=RequestMethod.OPTIONS)
//    public void corsHeaders(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
//        response.addHeader("Access-Control-Max-Age", "3600");
//    }

//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private AuthenticationManager authenticationManager;
    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @CrossOrigin(origins = "**", allowedHeaders = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> planets = planetService.findAllPlanets();
        return new ResponseEntity<>(planets, HttpStatus.OK);
    }


    @GetMapping("/getcrews")
    public ResponseEntity<Crew> getPlanetCrew(){

        HashMap<String, Long> params = new HashMap<>();
        params.put("id", Long.parseLong("1"));


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(AuthenticationResource.CurrentJWTToken);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<Crew> responseCrew=restTemplate.exchange("http://localhost:8080/crew/find/1", HttpMethod.GET, entity, Crew.class);

        return new ResponseEntity(responseCrew.getBody(), HttpStatus.OK);
    }

//    HashMap<String, Long> params = new HashMap<>();
//params.put("userId", orderDetails.getUserId());
//try {
//        ResponseEntity<User> response
//                = new RestTemplate().getForEntity(
//                "http://localhost:8080/users/{userId}",
//                User.class, params);
//    }

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

//    @PostMapping("/authenticate")
//    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
//            );
////            return jwtUtil.generateToken(authRequest.getUserName());
//            return new ResponseEntity<>(jwtUtil.generateToken(authRequest.getUserName()), HttpStatus.OK);
//        }
//        catch (Exception exception){
//            System.out.println(exception.getMessage());
//            return new ResponseEntity<>("Invalid credentials",HttpStatus.UNAUTHORIZED);
//        }
//
//    }

}
