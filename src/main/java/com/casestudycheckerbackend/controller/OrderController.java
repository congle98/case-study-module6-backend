package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.service.oder.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOderService oderService;
    @PostMapping("/create")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderRequest createOrderRequest){
        System.out.println("đây là đơn thuê"+createOrderRequest);
        oderService.createNewOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
