package com.casestudycheckerbackend.controller;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.oder.IOderService;
import com.casestudycheckerbackend.service.statusorderservice.IStatusOrderSerivce;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOderService oderService;

    @Autowired
    private IUserInformationService userInformationService;
    @Autowired
    private IStatusOrderSerivce statusOrderSerivce;
    @PostMapping("/create")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderRequest createOrderRequest){
        System.out.println("đây là đơn thuê"+createOrderRequest);
        oderService.createNewOrder(createOrderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptOrder(@PathVariable Long id, @RequestBody Long statusId){
        if(oderService.acceptOrder(id, statusId)){
            return new ResponseEntity<>(new MessageResponse("OK"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("fail"),HttpStatus.NOT_FOUND);
    }

    @PutMapping("/decline/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id, @RequestBody Long statusId){
        if(oderService.cancelOrder(id, statusId)){
            return new ResponseEntity<>(new MessageResponse("OK"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new MessageResponse("fail"),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orderByProvider/{id}")
    public ResponseEntity<?> getListOrderByProvider(@PathVariable Long id){
        System.out.println("đây là id order"+id);
        Optional<UserInformation> user = userInformationService.findById(id);
        if(user.isPresent()){
            List<Oder> oderList= (List<Oder>) oderService.findByProvider(user.get());
            return new ResponseEntity<>(oderList, HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/orderByUser/{id}")
    public ResponseEntity<?> getListOrderByUser(@PathVariable Long id){
        Optional<UserInformation> user = userInformationService.findById(id);
        if(user.isPresent()){
            List<Oder> oderList= (List<Oder>) oderService.findByUser(user.get());
            return new ResponseEntity<>(oderList, HttpStatus.OK);
        }
        return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        Optional<Oder> order= oderService.findById(id);
        if(order.isPresent()){
            return new ResponseEntity<>(order, HttpStatus.OK);

        }
        return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> findAllOrders(Integer page){
        Page<Oder> oders;
        Pageable pageable;
        if(page!=null){
            pageable = PageRequest.of(page,20);
        }
        else {
            pageable = PageRequest.of(0,20);
        }
        oders = oderService.findAllPage(pageable);
        return new ResponseEntity<>(oders,HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editOrder(@PathVariable Long id,  @RequestBody Oder oder){
        Optional<Oder> oder1 = oderService.findById(id);
        if(oder1.isPresent()){
            oderService.save(oder);
            return new ResponseEntity<>(new MessageResponse("Ok"), HttpStatus.OK);

        }
        return new ResponseEntity<>(new MessageResponse("fail"), HttpStatus.NOT_FOUND);

    }


}
