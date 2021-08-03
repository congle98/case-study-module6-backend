package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private UserService userService;

    @GetMapping("/verify/{code}")
    public String verify(@PathVariable String code){
        boolean verifed = userService.verify(code);
        if(verifed){
            return "verify success";
        }
        return "can not verify";
    }
}
