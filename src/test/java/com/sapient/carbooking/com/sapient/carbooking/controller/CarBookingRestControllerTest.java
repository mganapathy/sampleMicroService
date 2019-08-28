package com.sapient.carbooking.com.sapient.carbooking.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.sapient.carbooking.com.sapient.carbooking.repository.UserDetailsRepository;
import com.sapient.carbooking.model.UserDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarBookingRestControllerTest {
	@LocalServerPort
	int randomServerPort;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAllUserDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/userDetails/";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("\"userName\":\"mganapathy\""));
	}

	@Test
	public void getByUserName() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/userDetails/mganapathy";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("\"userName\":\"mganapathy\""));

	}

	@Test
	public void CreateUserDetails() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/userDetails/add";
		URI uri = new URI(baseUrl);
		UserDetails userDetails = new UserDetails();
		userDetails.setAddress("test address");
		userDetails.setUserName("Marimuthu");
		ResponseEntity<Object> result = restTemplate.postForEntity(uri, userDetails, null);
		// Verify request succeed
		Assert.assertEquals(201, result.getStatusCodeValue());

	}

	@Test(expected = HttpServerErrorException.class)
	public void deleteByUserNameWithException() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/userDetails/delete/abcd";
		URI uri = new URI(baseUrl);
		restTemplate.delete(uri);
	}
	@Test
	public void deleteByUserNameWithSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/userDetails/delete/muthu";
		URI uri = new URI(baseUrl);
		HttpEntity entity = null;
		ResponseEntity resp = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
		// Verify response code
		Assert.assertEquals(200, resp.getStatusCodeValue());
		
	}
}