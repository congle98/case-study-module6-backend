package com.casestudycheckerbackend.service.oder;

import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.OderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderService implements IOderService{
    @Autowired
    OderRepository oderRepository;

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
}
