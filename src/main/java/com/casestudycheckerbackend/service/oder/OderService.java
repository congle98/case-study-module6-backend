package com.casestudycheckerbackend.service.oder;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.OderRepository;
import com.casestudycheckerbackend.repository.StatusOrderRepository;
import com.casestudycheckerbackend.service.user.IUserService;
import com.casestudycheckerbackend.service.userInformationService.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderService implements IOderService{
    @Autowired
    OderRepository oderRepository;

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    StatusOrderRepository statusOrderRepository;



    @Override
    public Iterable<Oder> findAll() {
        return oderRepository.findAll();
    }

    @Override
    public Optional<Oder> findById(Long id) {
        return oderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        oderRepository.deleteById(id);

    }

    @Override
    public Oder save(Oder oder) {
        return oderRepository.save(oder);
    }


    @Override
    public Iterable<Oder> findByProvider(UserInformation provider) {
        return oderRepository.findByProvider(provider);
    }

    @Override
    public Iterable<Oder> findByUser(UserInformation user) {
        return oderRepository.findByUser(user);
    }

    @Override
    public Oder createNewOrder(CreateOrderRequest createOrderRequest) {
        User user = new User();
        user.setId(createOrderRequest.getUserId());
        UserInformation providerInformation = new UserInformation();
        providerInformation.setId(createOrderRequest.getProviderInformationId());
        UserInformation userInformation = userInformationService.findByUser(user);
        Oder oder = new Oder();
        oder.setAddress(createOrderRequest.getAddress());
        oder.setHour(createOrderRequest.getHour());
        oder.setProvider(providerInformation);
        oder.setUser(userInformation);
        oder.setStartTime(createOrderRequest.getStartTime());
        oder.setDay(createOrderRequest.getDay());
        oder.setTotalPrice(createOrderRequest.getTotalPrice());

        StatusOder statusOder = new StatusOder();
        statusOder.setId(1l);
        oder.setStatus(statusOder);
        return oderRepository.save(oder);

    }


    @Override
    public Boolean acceptOrder(Long id, Long statusId){
        Optional<Oder> oder = findById(id);
        if(oder.isPresent()){
            StatusOder newStatus= changeStatus(statusId);
            Oder oderFix = oder.get();
            oderFix.setStatus(newStatus);
            save(oderFix);
            return true;

        }
        return false;
    }

    @Override
    public StatusOder changeStatus(Long statusId) {

        if(1<=statusId && statusId<=3){
            return statusOrderRepository.findById(statusId+1).get();
        }
        else return statusOrderRepository.findById(5L).get();
//        switch (statusId){
//            case 1L: {
//                StatusOder statusOder=statusOrderRepository.findById(2L).get();
//                return statusOder;
//
//            }
//            case 2L: {
//                StatusOder statusOder= new StatusOder(3L);
//                return statusOder;
//
//            }
//            case 3L: {
//                StatusOder statusOder= new StatusOder(4L);
//                return statusOder;
//
//            }
//            default: {
//                StatusOder statusOder= new StatusOder(5L);
//                return statusOder;
//            }
//
//        }
    }

    @Override
    public Boolean cancelOrder(Long id, Long statusId) {
        Optional<Oder> oder =findById(id);
        if(oder.isPresent()){
            StatusOder newStatus= statusOrderRepository.findById(5L).get();
            Oder oderFix = oder.get();
            oderFix.setStatus(newStatus);
            save(oderFix);
            return true;

        }
        return false;


    }


    @Override
    public Page<Oder> findAllPage(Pageable pageable) {
        return oderRepository.findAll(pageable);
    }

    @Override
    public Page<Oder> finAllByProviderPage(UserInformation provider, Pageable pageable) {
        return oderRepository.findAllByProvider(provider,pageable);
    }


}
