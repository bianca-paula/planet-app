package com.abac.planetapp.validator;

import com.abac.planetapp.model.Planet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanetValidator implements Validator<Planet> {
    public void validate(Planet planet) throws ValidationException {
        List<String>  possibleStatusValues = Arrays.asList("OK", "!OK", "TODO", "En Route");
        if(!possibleStatusValues.contains(planet.getStatus())){
            throw new ValidationException("The planet status is invalid!");
        }
    }
}
