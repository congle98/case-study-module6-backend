package com.casestudycheckerbackend.controller.user;

import com.casestudycheckerbackend.dto.response.user.UserResponse;
import com.casestudycheckerbackend.service.demouser.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class DemoUserController {

    @Autowired
    private DemoUserService service;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}
