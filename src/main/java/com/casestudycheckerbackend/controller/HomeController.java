package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.SearchFilterRequest;
import com.casestudycheckerbackend.dto.response.ProviderHomeResponse;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private IUserInformationService userInformationService;

//    @GetMapping("/allProviderForViews")
//    private ResponseEntity<?> fillAllForViews(@PageableDefault() Pageable pageable){
////        if(pageable==null){
////            Page<ProviderHomeResponse> appUsers;
////            Pageable pageable2;
////            pageable2 = PageRequest.of(0,3);
////            appUsers = userInformationService.findAllProviderHomePage(pageable2);
////            return new ResponseEntity<>(appUsers,HttpStatus.OK);
////        }
//        Sort sort = Sort.by(Sort.Direction.ASC, "number_of_views");
//        pageable.getSortOr(sort);
//      return  new ResponseEntity<>( userInformationService.findAllProviderHomePage(pageable), HttpStatus.OK);
//    }

    @GetMapping("/findAll")
    private ResponseEntity<?> findAllProvider(Integer page){
        Page<ProviderHomeResponse> providers;
        Pageable pageable;
        if(page!=null){
            pageable = PageRequest.of(page,8);
        }
        else {
            pageable = PageRequest.of(0,8);
        }
        providers = userInformationService.test(pageable);
        return new ResponseEntity<>(providers,HttpStatus.OK);

    }
    @GetMapping("/findAllByViews")
    private ResponseEntity<?> findAllProviderByViews(Integer page){
        Page<ProviderHomeResponse> providers;
        Pageable pageable;
        if(page!=null){
            pageable = PageRequest.of(page,8);
        }
        else {
            pageable = PageRequest.of(0,8);
        }
        providers = userInformationService.findAllByIsProviderAndOrderByNumberOfViews(pageable);
        return new ResponseEntity<>(providers,HttpStatus.OK);

    }

    @PostMapping("/search")
    private ResponseEntity<?> searchFilter(@RequestBody SearchFilterRequest filterRequest){
        System.out.println("đây là filter"+filterRequest);
        List<ProviderHomeResponse> list = userInformationService.searchFilter(filterRequest);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
