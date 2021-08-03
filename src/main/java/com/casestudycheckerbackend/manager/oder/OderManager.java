package com.casestudycheckerbackend.manager.oder;

import com.casestudycheckerbackend.dto.response.UserOderResponse;
import com.casestudycheckerbackend.repository.OderRepository;
import com.casestudycheckerbackend.repository.UserOderResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OderManager {

    @Autowired
    private OderRepository oderRepository;
    @Autowired
    private UserOderResponseRepository userOderResponseRepository;

    public UserOderResponse getOder(long id) {
        return userOderResponseRepository.getOder(id);
    }
}
