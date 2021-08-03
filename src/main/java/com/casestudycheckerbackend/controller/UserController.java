package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/view/{userId}")
    private ResponseEntity<?>getProfileByUserId(@PathVariable Long userId){
        Optional <User> user = userService.findById(userId);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("fail"), HttpStatus.NOT_FOUND);
    }
}
