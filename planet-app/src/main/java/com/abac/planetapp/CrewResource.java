package com.abac.planetapp;

import com.abac.planetapp.model.Crew;
import com.abac.planetapp.model.Planet;
import com.abac.planetapp.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewResource {
    private final CrewService crewService;

    public CrewResource(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Crew>> getAllCrews() {
        List<Crew> crews = crewService.findAllCrews();
        return new ResponseEntity<>(crews, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Crew> getCrewById(@PathVariable("id") Long id) {
        Crew crew = crewService.findCrewById(id);
        return new ResponseEntity<>(crew, HttpStatus.OK);
    }


}
