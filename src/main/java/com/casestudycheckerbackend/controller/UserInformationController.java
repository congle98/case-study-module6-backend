package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.PaymentOrderRequest;
import com.casestudycheckerbackend.dto.request.RegisterProviderRequest;
import com.casestudycheckerbackend.dto.request.UpdateAvatarRequest;
import com.casestudycheckerbackend.dto.request.UserInformationUpdateRequest;
import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.image.IImageService;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/profile")
public class UserInformationController {
    @Autowired
    private IUserInformationService userInformationService;
    @Autowired
    private IImageService imageService;

    @GetMapping("/view/{userId}")
    private ResponseEntity<?>getProfileByUserId(@PathVariable Long userId){
        User user = new User();
        user.setId(userId);
        // la no tu hieu, vi cac doi no mapping voi nhau id, chi can dung id la dc
        UserInformation userInformation = userInformationService.findByUser(user);
        return new ResponseEntity<>(userInformation, HttpStatus.OK);
    }

    @PutMapping("/addviews")
    private ResponseEntity<?> setViews(@RequestBody Long userId){
        return new ResponseEntity<>(userInformationService.setViews(userId),HttpStatus.OK);
    }

    @PutMapping("")
    private ResponseEntity<?> updateProfile(@RequestBody UserInformationUpdateRequest userInformationUpdateRequest){
        return  new ResponseEntity<>(userInformationService.userInformationUpdate(userInformationUpdateRequest),HttpStatus.OK);
    }
    @GetMapping("/userInfoUpdate/{userId}")
    private  ResponseEntity<?> getUserInformationUpdate(@PathVariable Long userId){
        return new ResponseEntity<>(userInformationService.getUserInfoUpdateRequest(userId),HttpStatus.OK);
    }
    @PutMapping("/providerStatusOff")
    private ResponseEntity<?> providerStatusOff(@RequestBody Long userInformationId){
        return new ResponseEntity<>(userInformationService.providerStatusOff(userInformationId),HttpStatus.OK);
    }


    @PutMapping("/updateAvatar")
    private ResponseEntity<?> updateAvatar(@RequestBody UpdateAvatarRequest updateAvatarRequest){
        return new ResponseEntity<>(userInformationService.updateAvatar(updateAvatarRequest),HttpStatus.OK);
    }

    @DeleteMapping("/deleteImage/{imgId}")
    private ResponseEntity<?> deleteImage(@PathVariable Long imgId){
        imageService.delete(imgId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addImage")
    private ResponseEntity<?> addImage(@RequestBody UpdateAvatarRequest updateAvatarRequest){
        return new ResponseEntity<>(userInformationService.addImage(updateAvatarRequest),HttpStatus.OK);
    }

    @PostMapping("/providerRegister")
    private ResponseEntity<?> registerProvider(@RequestBody RegisterProviderRequest registerProviderRequest){
        return new ResponseEntity<>( userInformationService.registerProvider(registerProviderRequest),HttpStatus.OK);
    }



    @GetMapping("/service/{id}")
    private  ResponseEntity<?> getServiceProvider(@PathVariable Long userId){
        Optional<UserInformation> user = userInformationService.findById(userId);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get().getServices(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("fail"),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getPrice/{id}")
    public ResponseEntity<?> getPriceOfProvider(@PathVariable Long id){
        Optional<UserInformation> user = userInformationService.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get().getPriceByHour(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("fail"),HttpStatus.NOT_FOUND);
    }

    @PutMapping("/paymentOder")
    public ResponseEntity<?> changeMoney(@RequestBody PaymentOrderRequest paymentOrderRequest){
        if(userInformationService.paymentMoney(paymentOrderRequest)){
            return new ResponseEntity<>(new MessageResponse("success"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("fail"), HttpStatus.NOT_FOUND);
    }




}
