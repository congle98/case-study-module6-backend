package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.UserLoginRequest;
import com.casestudycheckerbackend.dto.response.JwtResponse;
import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.exception.UserFoundException;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.security.jwt.JwtTokenProvider;
import com.casestudycheckerbackend.service.email.EmailService;
import com.casestudycheckerbackend.service.email.Utiliy;
import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.user.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private EmailService emailService;

    @Autowired
    private IUserService userService;


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


    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        //th??m ?????i t?????ng n??y v??o secutiry ????? x??? l?? ti???p
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //kh???i t???o jwt t??? ?????i t?????ng n??y
        String jwt = jwtTokenProvider.generateJwtToken(authentication);
        //t???o ?????i t?????ng userdetail t??? authen.getPrincipal

        if(userService.loadUserByUserName(userLoginRequest.getUsername()).getAccountStatus()
        && userService.loadUserByUserName(userLoginRequest.getUsername()).getIsVerifyEmail()){
            return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
        }
        else if(!userService.loadUserByUserName(userLoginRequest.getUsername()).getIsVerifyEmail()){
            return new ResponseEntity<>(new MessageResponse("T??i kho???n ch??a k??ch ho???t email"),HttpStatus.EXPECTATION_FAILED);
        }
        else {
            return new ResponseEntity<>(new MessageResponse("T??i kho???n ???? b??? kho??"), HttpStatus.OK);
        }


    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user, HttpServletRequest request) throws Exception {
        if (userService.loadUserByUserName(user.getUsername()) != null) {
            throw new UserFoundException();
        }
        String siteURL = Utiliy.getSiteURL(request);

        System.out.println("????y l?? th???ng user:"+user);

        userService.save(user);
        emailService.send(user,siteURL);
        return new ResponseEntity<>(new MessageResponse("T???o m???i th??nh c??ng"), HttpStatus.CREATED);
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        User user =  (User) this.userDetailsService.loadUserByUsername(principal.getName());
        return new ResponseEntity<>(userService.userToUserLoginResponse(user),HttpStatus.OK);
    }
}
