package com.casestudycheckerbackend.service.user;

import com.casestudycheckerbackend.dto.response.UserAccountResponse;
import com.casestudycheckerbackend.dto.response.UserLoginResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends IGeneralService<User> {
    User loadUserByUserName(String username);

    UserLoginResponse userToUserLoginResponse(User user);

    User findByVerificationCode(String code);

    Boolean verify(String code);

    UserAccountResponse getUserAccount(Long id);

    Page<User> findAllPage(Pageable pageable);

    void lockAccount(Long id);



}
