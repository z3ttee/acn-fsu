package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout=3000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = new Ride();
		ride.setName("Bobsled Trail");
		ride.setDuration(33);
		
		ride = restTemplate.postForObject("http://localhost:5000/ridetracker/ride", ride, Ride.class);
		System.out.println("Ride: " + ride);
	}

	@Test(timeout=3000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = restTemplate.getForObject("http://localhost:5000/ridetracker/ride/1", Ride.class);
		System.out.println(ride.getId() + ": " + ride.getName());
	}
	
	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:5000/ridetracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}

	@Test(timeout=3000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = restTemplate.getForObject("http://localhost:5000/ridetracker/ride/1", Ride.class);
		ride.setDuration(ride.getDuration() + 1);

		restTemplate.put("http://localhost:5000/ridetracker/ride", ride);

		System.out.println(ride.getId() + ": " + ride.getName() + " - " + ride.getDuration());
	}

	@Test(timeout=3000)
	public void testbatchUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();

		// Does not care which object it is
		restTemplate.getForObject("http://localhost:5000/ridetracker/batch", Object.class);

		//System.out.println(ride.getId() + ": " + ride.getName());
	}

	@Test(timeout=3000)
	public void testDeleteRide() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.delete("http://localhost:5000/ridetracker/delete/5");
	}

	@Test(timeout=3000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getForObject("http://localhost:5000/ridetracker/exception", Ride.class);
	}
}
