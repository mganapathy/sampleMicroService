package com.sapient.carbooking;

import com.sapient.carbooking.com.sapient.carbooking.repository.UserDetailsRepository;
import com.sapient.carbooking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.ApplicationRunner;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class CarbookingApplication {

	private UserDetailsRepository userDetailsRepository;

	public CarbookingApplication(UserDetailsRepository userDetailsRepository) {
		this.userDetailsRepository = userDetailsRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(CarbookingApplication.class, args);
	}

	@Bean
	ApplicationRunner init( ApplicationArguments	 args) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("mganapathy");
		userDetails.setAddress("No.56,G1,SreeBalajiFlat");
		userDetailsRepository.save(userDetails);
		UserDetails userDetails1 = new UserDetails();
		userDetails1.setUserName("muthu");
		userDetails1.setAddress("No.56,G1,SreeBalajiFlat1123123");
		userDetailsRepository.save(userDetails1);
		userDetailsRepository.findAll().forEach(System.out::println);
		return null;
	}
}