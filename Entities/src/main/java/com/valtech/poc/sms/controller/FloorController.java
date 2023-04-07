package com.valtech.poc.sms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.valtech.poc.sms.entities.Floors;
import com.valtech.poc.sms.service.FloorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/floors")
public class FloorController {

	private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

	@Autowired
	private FloorService floorService;

	@ResponseBody
	@GetMapping("/getAllFloorDetails")
	public ResponseEntity<List<Floors>> getAllFloors() {
		try {
			List<Floors> floors = floorService.getAllFloors();
			logger.info("Successfully fetched all floors' details.");
			return new ResponseEntity<>(floors, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while fetching all floors: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@GetMapping("/getFloorDetails/{f_id}")
	public ResponseEntity<Floors> getFloorById(@PathVariable int f_id) {
		try {
			Floors floor = floorService.getFloorById(f_id);
			if (floor == null) {
				logger.info("Floor with id {} not found", f_id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			logger.info("Successfully fetched floor details with Id '{}'.", f_id);
			return new ResponseEntity<>(floor, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while fetching floors' details with Id '{}'. Id does not exist!", f_id, e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@PostMapping("/addFloor")
	public ResponseEntity<Floors> addFloor(@RequestParam(name = "f_id") int f_id,
			@RequestParam(name = "f_name") String f_name, @RequestParam(name = "f_seats") int f_seats) {
		try {
			Floors floor = new Floors(f_id, f_name, f_seats);
			floorService.addFloor(floor);
			logger.info("Successfully added floor with Id '{}' under name '{}'.", f_id, f_name);
			return new ResponseEntity<>(floor, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error occurred while adding floor with Id '{}'. Duplicate Id exists! : {}", f_id, e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@DeleteMapping("/deleteFloor/{f_id}")
	public ResponseEntity<Void> deleteFloor(@PathVariable int f_id) {
		try {
			floorService.deleteFloor(f_id);
			logger.info("Successfully deleted floor with id '{}'.", f_id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting floor with Id '{}'. Id does not exist!", f_id, e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@PostMapping("/floorSeats/{f_id}/addSeat")
	public ResponseEntity<Void> addFloorSeats(@PathVariable int f_id, @RequestParam int seatsToAdd) {
		try {
			floorService.addFloorSeats(f_id, seatsToAdd);
			logger.info("Successfully added {} seats to floor with Id '{}'.", seatsToAdd, f_id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while adding seats to floor with Id '{}'. ID does not exist!", f_id, e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseBody
	@PostMapping("/floorSeats/{f_id}/deleteSeat")
	public ResponseEntity<Void> deleteFloorSeats(@PathVariable int f_id, @RequestParam int seatsToDelete) {
		try {
			floorService.deleteFloorSeats(f_id, seatsToDelete);
			logger.info("Successfully deleted {} seats from floor with Id '{}'.", seatsToDelete, f_id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while deleting seats from floor with ID: " + f_id, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed to delete seats from floor with ID: " + f_id);
		}
	}

	@ResponseBody
	@PostMapping("/floorSeats/{f_id}/updateFloorAndSeats")
	public void updateFloorAndSeats(@PathVariable int f_id, @RequestParam(required = false) String f_name,
			@RequestParam(required = false) Integer updatedNumberOfSeats, HttpServletRequest request) {
		try {
			String lastFName = (String) request.getSession().getAttribute("f_name" + f_id);
			Integer lastNumSeats = (Integer) request.getSession().getAttribute("updatedNumberOfSeats" + f_id);
			if (f_name == null && lastFName != null) {
				f_name = lastFName;
			}
			if (updatedNumberOfSeats == null && lastNumSeats != null) {
				updatedNumberOfSeats = lastNumSeats;
			}
			request.getSession().setAttribute("f_name" + f_id, f_name);
			request.getSession().setAttribute("updatedNumberOfSeats" + f_id, updatedNumberOfSeats);
			floorService.updateFloorAndSeats(f_id, f_name, updatedNumberOfSeats);
		} catch (Exception e) {
			logger.error("Exception occurred while updating floor and seats for floor with ID: " + f_id, e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed to update floor and seats for floor with ID: " + f_id);
		}
	}
}