package com.abac.planetapp;

import com.abac.planetapp.model.User;
import com.abac.planetapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties
//@EntityScan(basePackages = {"com.abac.planetapp.model"})
public class PlanetAppApplication {
	@Autowired
	private UserRepository repository;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}


//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/planet").allowedOrigins("http://localhost:4200");
//			}
//		};
//	}

//	@PostConstruct
//	public void initUsers(){
//		List<User> users = Stream.of(
//				new User(101,"user1","password1","user1@email.com")
//		).collect(Collectors.toList());
//		repository.saveAll(users);
//	}

//@Bean
//public WebMvcConfigurer corsConfigurer(){
//	return new WebMvcConfigurer() {
//		@Override
//		public void addCorsMappings(CorsRegistry registry){
//			registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*")
//			.allowCredentials(true);
//		}
//	};
//}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/planet").allowedOrigins("*");
//			}
//		};
//	}
public static void main(String[] args) {
		SpringApplication.run(PlanetAppApplication.class, args);
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
//				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
//				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
//		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
//				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//		return new CorsFilter(urlBasedCorsConfigurationSource);
//	}
}
