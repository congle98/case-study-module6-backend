package com.casestudycheckerbackend.service.statusorderservice;

import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StatusOrderService implements IStatusOrderSerivce{
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Override
    public Iterable<StatusOder> findAll() {
        return null;
    }

    @Override
    public Optional<StatusOder> findById(Long id) {
        return statusOrderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public StatusOder save(StatusOder statusOder) {
        return null;
    }
}
