package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.service.oder.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptOrder(@PathVariable Long id, @RequestBody String status){
        Optional<Oder> oder = oderService.findById(id);
        if(oder.isPresent()){
            StatusOder newStatus= oderService.changeStatus(status);
            Oder oderFix = oder.get();
            oderFix.setStatus(newStatus);
            oderService.save(oderFix);
            return new ResponseEntity<>(oderFix, HttpStatus.OK);

        }
        return new ResponseEntity<>(new MessageResponse("fail"),HttpStatus.NOT_FOUND);
    }

    @PutMapping("/decline/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id, @RequestBody String status){
        Optional<Oder> oder = oderService.findById(id);
        if(oder.isPresent()){
            StatusOder newStatus= oderService.cancelOrder(status);
            Oder oderFix = oder.get();
            oderFix.setStatus(newStatus);
            oderService.save(oderFix);
            return new ResponseEntity<>(oderFix, HttpStatus.OK);

        }
        return new ResponseEntity<>(new MessageResponse("fail"),HttpStatus.NOT_FOUND);
    }
}
