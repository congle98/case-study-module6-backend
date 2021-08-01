package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.UserLoginRequestDto;
import com.casestudycheckerbackend.dto.response.JwtResponse;
import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.exception.UserFoundException;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.security.jwt.JwtTokenProvider;
import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.user.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImp userDetailsService;


    @Autowired
    private IUserService userService;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody UserLoginRequestDto userLoginRequestDto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword()));
        //thêm đối tượng này vào secutiry để xử lý tiếp
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //khởi tạo jwt từ đối tượng này
        String jwt = jwtTokenProvider.generateJwtToken(authentication);
        //tạo đối tượng userdetail từ authen.getPrincipal
        System.out.println("jwt là " + jwt);
        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);

    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        if (userService.loadUserByUserName(user.getUsername()) != null) {
            throw new UserFoundException();
        }
        userService.save(user);
        return new ResponseEntity<>(new MessageResponse("Tạo mới thành công"), HttpStatus.CREATED);


    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {
        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
