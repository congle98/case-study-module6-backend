package com.casestudycheckerbackend.service.oder;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.OderRepository;
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
        oder.setTotalPrice(createOrderRequest.getTotalPrice());
        StatusOder statusOder = new StatusOder();
        statusOder.setId(1l);
        oder.setStatus(statusOder);
        return oderRepository.save(oder);

    }

    @Override
    public StatusOder changeStatus(String status) {

        switch (status){
            case "WAITING": {
                StatusOder statusOder= new StatusOder(2L);
                return statusOder;

            }
            case "RECEIVED": {
                StatusOder statusOder= new StatusOder(3L);
                return statusOder;

            }
            case "COMPLETE": {
                StatusOder statusOder= new StatusOder(4L);
                return statusOder;

            }
            default: {
                StatusOder statusOder= new StatusOder(5L);
                return statusOder;
            }

        }
    }

    @Override
    public StatusOder cancelOrder(String status) {
        return new StatusOder(5L);
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
