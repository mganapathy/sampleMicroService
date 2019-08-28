package com.sapient.carbooking.com.sapient.carbooking.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.carbooking.com.sapient.carbooking.exception.UserNotFoundException;
import com.sapient.carbooking.com.sapient.carbooking.repository.UserDetailsRepository;
import com.sapient.carbooking.model.UserDetails;


@RestController
class CarBookingRestController {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @GetMapping(value = "/userDetails")
    Collection<UserDetails> getAllUserDetails() {
        this.userDetailsRepository.findAll().forEach(System.out::println);
        return this.userDetailsRepository.findAll();
    }

    @GetMapping(value = "/userDetails/{userName}")
    List<UserDetails> getByUserName(@PathVariable("userName") final String userName) {

        return this.userDetailsRepository.findByUserName(userName)
                .stream()
                .collect(Collectors.toList());

    }

    @PostMapping(value = "/userDetails/add")
    ResponseEntity<Object> CreateUserDetails(@RequestBody UserDetails userDetails) {
        System.out.println("request payload given to service is " + userDetails.toString());
        this.userDetailsRepository.save(userDetails);
        this.userDetailsRepository.findAll().forEach(System.out::println);
        return new ResponseEntity<>("UserDetails is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping (value = "userDetails/delete/{userName}")
    ResponseEntity<Object> deleteByUserName(@PathVariable("userName") final String userName) throws UserNotFoundException {
        List<UserDetails> users = userDetailsRepository.findByUserName(userName);
        if(users.size()  < 1)
            throw new UserNotFoundException("User is not exist");
        userDetailsRepository.deleteAll(users);
        return new ResponseEntity<>("User is deleted", HttpStatus.OK);
//        return this.userDetailsRepository.findByUserName(userName)
//                .stream()
//                .collect(Collectors.toList());

    }

}

