package com.casestudycheckerbackend.service.user;

import com.casestudycheckerbackend.dto.response.UserLoginResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    User loadUserByUserName(String username);

    UserLoginResponse userToUserLoginResponse(User user);
}
