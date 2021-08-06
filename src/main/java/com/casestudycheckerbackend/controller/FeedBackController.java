package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.CreateFeedBackRequest;
import com.casestudycheckerbackend.models.FeedbackOrder;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.service.feedBack.IFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedBackController {
    @Autowired
    private IFeedBackService feedBackService;


    @PutMapping("/changeStatus")
    ResponseEntity<?> changeStatus(@RequestBody Long feedBackId){
        System.out.println("id của feedback"+feedBackId);
        return new ResponseEntity<>(feedBackService.changeStatus(feedBackId), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<?> createFeedBack(@RequestBody CreateFeedBackRequest feedBackRequest){
        return new ResponseEntity<>(feedBackService.createFeedBack(feedBackRequest),HttpStatus.OK);

    }
}
