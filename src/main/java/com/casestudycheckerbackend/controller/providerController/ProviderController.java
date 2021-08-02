package com.casestudycheckerbackend.controller.providerController;


import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.oder.IOderService;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

public class ProviderController {

    @Autowired
    IUserInformationService userInformationService;
    @Autowired
    IOderService oderService;

    @PutMapping("/isProvider/{id}")
    public ResponseEntity<?> isProvider(@PathVariable Long id ){
        boolean check = userInformationService.isProvider(id);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
    @PutMapping("/price/{id}/{price}")
    public ResponseEntity<?> changePrice(@PathVariable Long id, @PathVariable Double price){
        Double priceResponse = userInformationService.changePrice(id, price);
        return new ResponseEntity<>(priceResponse, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getListOrder(@PathVariable Long id){
        Optional<UserInformation> user = userInformationService.findById(id);
        if(user.isPresent()){
            List<Oder> oderList= (List<Oder>) oderService.findByProvider(user.get());
            return new ResponseEntity<>(oderList, HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/list2/{id}")
    public ResponseEntity<?> getListOrder2(@PathVariable Long id){
        Optional<UserInformation> user = userInformationService.findById(id);
        if(user.isPresent()){
            List<Oder> oderList= (List<Oder>) oderService.findByUser(user.get());
            return new ResponseEntity<>(oderList, HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody Oder oder, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Fail", HttpStatus.FAILED_DEPENDENCY);
        }
        oderService.save(oder);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }


}
