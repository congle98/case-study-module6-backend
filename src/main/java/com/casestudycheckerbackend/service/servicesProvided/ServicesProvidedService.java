package com.casestudycheckerbackend.service.servicesProvided;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.ServicesProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesProvidedService implements IServicesProvidedService{
    @Autowired
    private ServicesProviderRepository servicesRepository;


    @Override
    public Iterable<ServicesProvided> findAll() {
        return servicesRepository.findAll();
    }

    @Override
    public Optional<ServicesProvided> findById(Long id) {
        return servicesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
            servicesRepository.deleteById(id);
    }

    @Override
    public ServicesProvided save(ServicesProvided servicesProvided) {
        return servicesRepository.save(servicesProvided);
    }

    @Override
    public List<ServicesProvided> findAllByProvider(UserInformation userInformation) {
        return servicesRepository.findAllByUserInformations(userInformation);
    }
}
