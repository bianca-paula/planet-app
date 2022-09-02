package com.abac.planetapp;

import com.abac.planetapp.model.User;
import com.abac.planetapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
//@EnableConfigurationProperties
//@EntityScan(basePackages = {"com.abac.planetapp.model"})

public class PlanetAppApplication {
	@Autowired
	private UserRepository repository;

//	@PostConstruct
//	public void initUsers(){
//		List<User> users = Stream.of(
//				new User(101,"user1","password1","user1@email.com")
//		).collect(Collectors.toList());
//		repository.saveAll(users);
//	}
public static void main(String[] args) {
		SpringApplication.run(PlanetAppApplication.class, args);
	}

}
