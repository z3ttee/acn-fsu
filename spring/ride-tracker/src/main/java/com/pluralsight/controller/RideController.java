package com.pluralsight.controller;

import java.util.List;

import com.pluralsight.utils.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;

@Controller
public class RideController {

	@Autowired
	private RideService rideService;
	
	@RequestMapping(value = "/ride", method = RequestMethod.POST)
	public @ResponseBody Ride createRide(@RequestBody Ride ride) {
		return rideService.createRide(ride);
	}
	
	@RequestMapping(value = "/rides", method = RequestMethod.GET)
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}

	@RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
	public @ResponseBody Ride getRide(@PathVariable(value = "id") int id) {
		return rideService.getRide(id);
	}

	@RequestMapping(value = "/ride", method = RequestMethod.PUT)
	public @ResponseBody Ride updateRide(@RequestBody Ride ride) {
		return rideService.updateRide(ride);
	}

	@RequestMapping(value = "/batch", method = RequestMethod.GET)
	public @ResponseBody Object batch() {
		rideService.batch();
		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object updateRide(@PathVariable(value = "id") int id) {
		return rideService.deleteRide(id);
	}

	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public @ResponseBody Object exception() {
		throw new DataAccessException("Testing exception throwing") {};
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceError> handle(RuntimeException exception) {
		ServiceError error = new ServiceError();
		error.setStatus(HttpStatus.OK.value());
		error.setMessage(exception.getMessage());

		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
}
