package com.abac.planetapp.service;

import com.abac.planetapp.exception.CrewNotFoundException;
import com.abac.planetapp.model.Crew;
import com.abac.planetapp.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewService {
    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<Crew> findAllCrews(){
        return crewRepository.findAll();
    }

    public Crew findCrewById(Long id){
        return crewRepository.findCrewById(id).orElseThrow(() -> new CrewNotFoundException("Crew by id " + id + " was not found!"));
    }
}
