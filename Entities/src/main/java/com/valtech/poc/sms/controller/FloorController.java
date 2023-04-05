package com.valtech.poc.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

import com.valtech.poc.sms.entities.Floors;
import com.valtech.poc.sms.service.FloorService;

@Controller
@RequestMapping("/floors")
public class FloorController {

	@Autowired
	private FloorService floorService;

//	@ResponseBody
//	@PostMapping("/add")
//	public ResponseEntity<String> addFloorSeats(@RequestBody Floors floor) {
//		floorService.addFloorSeats(floor);
//		return new ResponseEntity<>("Floor seats added successfully", HttpStatus.OK);
//	}
//
//	@ResponseBody
//	@PutMapping("/update")
//	public ResponseEntity<String> updateFloorSeats(@RequestBody Floors floor) {
//		floorService.updateFloorSeats(floor);
//		return new ResponseEntity<>("Floor seats updated successfully", HttpStatus.OK);
//	}
//
//	@ResponseBody
//	@DeleteMapping("/delete")
//	public ResponseEntity<String> deleteFloorSeats(@RequestParam int floorId) {
//		floorService.deleteFloorSeats(floorId);
//		return new ResponseEntity<>("Floor seats deleted successfully", HttpStatus.OK);
//	}
	
//	---------------------------------------------------------------------------------------------------------
	
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
    @PostMapping("/floorSeats/{f_id}/addSeat")
    public void addFloorSeats(@PathVariable int f_id, @RequestParam int seatsToAdd) {
        floorService.addFloorSeats(f_id, seatsToAdd);
    }
//
	@ResponseBody
    @PostMapping("/floorSeats/{f_id}/deleteSeat")
    public void deleteFloorSeats(@PathVariable int f_id, @RequestParam int seatsToDelete) {
        floorService.deleteFloorSeats(f_id, seatsToDelete);
    }

	@ResponseBody
    @PostMapping("/floorSeats/{f_id}/updateTotalSeats")
    public void updateFloorSeats(@PathVariable int f_id, @RequestParam int updatedNumberOfSeats) {
        floorService.updateFloorSeats(f_id, updatedNumberOfSeats);
    }
}
