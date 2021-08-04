package com.casestudycheckerbackend.service.servicesProvided;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.repository.ServicesProvidedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ServicesProvidedService implements IServicesProvidedService{
    @Autowired
    ServicesProvidedRepository servicesProvidedRepository;
    @Override
    public Iterable<ServicesProvided> findAll() {
        return servicesProvidedRepository.findAll();
    }

    @Override
    public Optional<ServicesProvided> findById(Long id) {
        return servicesProvidedRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        servicesProvidedRepository.deleteById(id);

    }

    @Override
    public ServicesProvided save(ServicesProvided servicesProvided) {
        return servicesProvidedRepository.save(servicesProvided);
    }

    @Override
    public Iterable<ServicesProvided> find3ServiceByProvider(long id) {
        return servicesProvidedRepository.find3ServicesProvidedByProvider(id);
    }
}
