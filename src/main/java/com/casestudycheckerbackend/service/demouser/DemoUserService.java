package com.casestudycheckerbackend.service.demouser;

import com.casestudycheckerbackend.dto.response.user.UserResponse;

import java.util.List;

public interface DemoUserService {

    List<UserResponse> getAllUsers();
}
