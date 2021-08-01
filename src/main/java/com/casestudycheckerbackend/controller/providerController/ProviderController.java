package com.casestudycheckerbackend.controller.providerController;


import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.user.UserService;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;

@RestController
@CrossOrigin(origins = "*")

public class ProviderController {

    @Autowired
    IUserInformationService userInformationService;

    @PutMapping("/{id}/isProvider")
    public ResponseEntity<?> isProvider(@PathVariable Long id ){
        boolean check = userInformationService.isProvider(id);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
    @PutMapping("/{id}/price/{price}")
    public ResponseEntity<?> changePrice(@PathVariable Long id, @PathVariable Double price){
        Double priceResponse = userInformationService.changePrice(id, price);
        return new ResponseEntity<>(priceResponse, HttpStatus.OK);
    }

}
