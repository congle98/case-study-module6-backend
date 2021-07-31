package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.UserLoginDto;
import com.casestudycheckerbackend.dto.response.JwtResponse;
import com.casestudycheckerbackend.exception.UserFoundException;
import com.casestudycheckerbackend.models.Role;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.security.jwt.JwtTokenProvider;
import com.casestudycheckerbackend.service.role.IRoleService;
import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.user.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private IRoleService roleService;

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

    @PostMapping("/login")
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


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        if(userService.loadUserByUserName(user.getUsername())!=null){
            throw new UserFoundException();
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setId(2l);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);
        User userRp = userService.save(user);
        //nếu tài khoản tồn tại thì thows ra 1 userfoundexception đã tạo hoặc ra luôn exception
        return new ResponseEntity<>(userRp, HttpStatus.CREATED);



    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){

        return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }

}
