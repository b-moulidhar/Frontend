//package com.valtech.poc.sms.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.valtech.poc.sms.entities.Floors;
//import com.valtech.poc.sms.service.FloorService;
//
//@RestController
//public class FloorController {
//
//    @Autowired
//    private FloorService floorService;
//
//    @GetMapping("/floors")
//    public List<Floors> getAllFloors() {
//        return floorService.getAllFloors();
//    }
//
//    @GetMapping("/floors/{fId}")
//    public Floors getFloorById(@PathVariable int fId) {
//        return floorService.getFloorById(fId);
//    }
//
//    @PostMapping("/floors/{fId}/addSeats")
//    public void addFloorSeats(@PathVariable int fId, @RequestParam int seatsToAdd) {
//        floorService.addFloorSeats(fId, seatsToAdd);
//    }
//
//    @PostMapping("/floors/{fId}/deleteSeats")
//    public void deleteFloorSeats(@PathVariable int fId, @RequestParam int seatsToDelete) {
//        floorService.deleteFloorSeats(fId, seatsToDelete);
//    }
//
//    @PostMapping("/floors/{fId}/updateSeats")
//    public void updateFloorSeats(@PathVariable int fId, @RequestParam int newSeats) {
//        floorService.updateFloorSeats(fId, newSeats);
//    }
//}
