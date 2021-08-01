package com.casestudycheckerbackend.service.userInformationService;

import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;
public interface IUserInformationService extends IGeneralService<UserInformation> {
    UserInformation findByUser(User user);
    Boolean isProvider(Long id);
    Double changePrice(Long id, Double price);
}
