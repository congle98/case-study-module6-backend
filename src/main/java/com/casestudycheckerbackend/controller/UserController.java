package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.dto.response.ProviderHomeResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        return new ResponseEntity<>(userService.getUserAccount(userId), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    private ResponseEntity<?>findAllUser(Integer page){
        Page<User> users;
        Pageable pageable;
        if(page!=null){
            pageable = PageRequest.of(page,20);
        }
        else {
            pageable = PageRequest.of(0,20);
        }
        users = userService.findAllPage(pageable);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
