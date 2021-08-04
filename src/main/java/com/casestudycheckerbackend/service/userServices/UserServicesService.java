package com.casestudycheckerbackend.service.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesService implements IUserServicesService{
    @Override
    public Iterable<UserServicesService> findAll() {
        return null;
    }

    @Override
    public Optional<UserServicesService> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserServicesService save(UserServicesService userServicesService) {
        return null;
    }

}
