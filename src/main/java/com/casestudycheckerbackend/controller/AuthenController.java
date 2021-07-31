package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.UserLoginDto;
import com.casestudycheckerbackend.security.jwt.JwtTokenProvider;
import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.user.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImp userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private void authenticate(String username,String password) throws Exception {
        try{

        }
        catch (DisabledException e){
            throw  new Exception("User disable"+e.getMessage());
        }
        catch (BadCredentialsException e){
            throw  new Exception("invalid credentials"+e.getMessage());
        }
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody UserLoginDto userLoginDto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(),userLoginDto.getPassword()));
        //thêm đối tượng này vào secutiry để xử lý tiếp
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //khởi tạo jwt từ đối tượng này
        String jwt = jwtTokenProvider.generateJwtToken(authentication);
        //tạo đối tượng userdetail từ authen.getPrincipal
        System.out.println("jwt là "+jwt);

        return new ResponseEntity<>(
                new JwtResponse(jwt), HttpStatus.OK);


    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){

        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
