package com.abac.planetapp.controller;

import com.abac.planetapp.model.AuthRequest;
import com.abac.planetapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationResource {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public static String CurrentJWTToken;

    public AuthenticationResource() {
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
//            return jwtUtil.generateToken(authRequest.getUserName());
            CurrentJWTToken = jwtUtil.generateToken(authRequest.getUserName());
            return new ResponseEntity<>(CurrentJWTToken, HttpStatus.OK);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            CurrentJWTToken = "";
            return new ResponseEntity<>("Invalid credentials",HttpStatus.UNAUTHORIZED);
        }

    }
}
