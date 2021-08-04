package com.casestudycheckerbackend.controller.providerController;


import com.casestudycheckerbackend.dto.ProviderDto;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.providerServices.ProviderService;
import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.user.UserService;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    IUserInformationService userInformationService;
    @Autowired
    ProviderService providerService;

    @PutMapping("/{id}/isProvider")
    public ResponseEntity<?> isProvider(@PathVariable Long id ){
        boolean check = userInformationService.isProvider(id);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
    @PutMapping("/{id}/price/{price}")
    public ResponseEntity<?> changePrice(@PathVariable Long id, @PathVariable Double price){
        Double priceResponse = userInformationService.changePrice(id, price);
        return new ResponseEntity<>(priceResponse, HttpStatus.OK);
    }
    @GetMapping("/providersbyviews")
    public ResponseEntity<?>getAllProviderByViews(@PageableDefault Pageable pageable){
        Page<ProviderDto> providerDtoPage = providerService.findAllProviderByViews(pageable);
        return new ResponseEntity<>(providerDtoPage,HttpStatus.OK);
    }
    @GetMapping("/count")
    public ResponseEntity<?>countProviders(){
        return new ResponseEntity<>(userInformationService.countProviders(),HttpStatus.OK);
    }
    @GetMapping("/providersbyprice")
    public ResponseEntity<?>getAllProviderByPrice(@PageableDefault Pageable pageable){
        Page<ProviderDto> providerDtoPage = providerService.findAllProviderByPrice(pageable);
        return new ResponseEntity<>(providerDtoPage,HttpStatus.OK);
    }

}
