package com.casestudycheckerbackend.service.demouser;

import com.casestudycheckerbackend.dto.response.user.UserResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoUserServiceImpl implements DemoUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            response.add(userResponse);
        }
        return response;
    }
}
