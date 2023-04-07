package com.valtech.poc.sms.controller;

import java.util.List;

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

import com.valtech.poc.sms.entities.Floors;
import com.valtech.poc.sms.service.FloorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/floors")
public class FloorController {

	@Autowired
	private FloorService floorService;

	@ResponseBody
	@GetMapping("/getAllFloorDetails")
	public List<Floors> getAllFloors() {
		return floorService.getAllFloors();
	}

	@ResponseBody
	@GetMapping("/getFloorDetails/{f_id}")
	public Floors getFloorById(@PathVariable int f_id) {
		return floorService.getFloorById(f_id);
	}

	@ResponseBody
	@PostMapping("/addFloor")
	public ResponseEntity<Floors> addFloor(@RequestParam(name = "f_id") int f_id,
			@RequestParam(name = "f_name") String f_name, @RequestParam(name = "f_seats") int f_seats) {
		Floors floor = new Floors(f_id, f_name, f_seats);
		floorService.addFloor(floor);
		return new ResponseEntity<>(floor, HttpStatus.CREATED);
	}

	@ResponseBody
	@DeleteMapping("/deleteFloor/{f_id}")
	public void deleteFloor(@PathVariable int f_id) {
		floorService.deleteFloor(f_id);
	}

	@ResponseBody
	@PostMapping("/floorSeats/{f_id}/addSeat")
	public void addFloorSeats(@PathVariable int f_id, @RequestParam int seatsToAdd) {
		floorService.addFloorSeats(f_id, seatsToAdd);
	}

	@ResponseBody
	@PostMapping("/floorSeats/{f_id}/deleteSeat")
	public void deleteFloorSeats(@PathVariable int f_id, @RequestParam int seatsToDelete) {
		floorService.deleteFloorSeats(f_id, seatsToDelete);
	}

	@ResponseBody
	@PostMapping("/floorSeats/{f_id}/updateFloorAndSeats")
	public void updateFloorAndSeats(@PathVariable int f_id, @RequestParam(required = false) String f_name,
			@RequestParam(required = false) Integer updatedNumberOfSeats, HttpServletRequest request) {
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
	}

}
