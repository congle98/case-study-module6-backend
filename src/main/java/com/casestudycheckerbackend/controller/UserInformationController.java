package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/profile")
public class UserInformationController {
    @Autowired
    private IUserInformationService userInformationService;

    @GetMapping("/{userId}")
    private ResponseEntity<?>getProfileByUserId(@PathVariable Long userId){
        User user = new User();
        user.setId(userId);
        UserInformation userInformation = userInformationService.findByUser(user);
        return new ResponseEntity<>(userInformation, HttpStatus.OK);
    }

    @PutMapping("/views")
    private ResponseEntity<?> setViews(@RequestBody Long userId){
        return new ResponseEntity<>(userInformationService.setViews(userId),HttpStatus.OK);
    }


}
