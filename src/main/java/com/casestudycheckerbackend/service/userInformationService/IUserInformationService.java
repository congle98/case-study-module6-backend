package com.casestudycheckerbackend.service.userInformationService;

import com.casestudycheckerbackend.dto.request.UserInformationUpdateRequest;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;
public interface IUserInformationService extends IGeneralService<UserInformation> {
    UserInformation findByUser(User user);
    UserInformation setViews(Long id);

    UserInformationUpdateRequest getUserInfoUpdateRequest(Long userId);

    UserInformationUpdateRequest userInformationUpdate(UserInformationUpdateRequest userInformationUpdateRequest);
}
