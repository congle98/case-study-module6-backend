package com.casestudycheckerbackend.service.servicesProvided;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ServicesProvidedService {
    @Autowired
    ServiceProviderRepository serviceProviderRepository;


}
