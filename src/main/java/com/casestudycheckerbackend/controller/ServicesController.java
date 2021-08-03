package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.service.servicesProvided.IServicesProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private IServicesProvidedService providedService;

    @GetMapping("")
    private ResponseEntity<?> findAllServices(){
        return new ResponseEntity<>(providedService.findAll(), HttpStatus.OK);
    }
}
