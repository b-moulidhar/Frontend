package com.valtech.poc.sms.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valtech.poc.sms.entities.Holidays;
import com.valtech.poc.sms.service.HolidayService;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @GetMapping("/{id}")
    public ResponseEntity<Holidays> getHolidayById(@PathVariable("id") int id) {
        Holidays holiday = holidayService.getHolidayById(id);
        return new ResponseEntity<>(holiday, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createHoliday(@RequestBody Holidays holiday) {
        holidayService.createHoliday(holiday);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateHoliday(@RequestBody Holidays holiday) {
        holidayService.updateHoliday(holiday);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<Boolean> isHoliday(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        boolean isHoliday = holidayService.isHoliday(date);
        return new ResponseEntity<>(isHoliday, HttpStatus.OK);
    }

}