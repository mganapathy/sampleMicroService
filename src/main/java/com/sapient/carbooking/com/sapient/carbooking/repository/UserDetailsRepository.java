package com.sapient.carbooking.com.sapient.carbooking.repository;

import com.sapient.carbooking.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    List<UserDetails> findByUserName(String userName);
}
