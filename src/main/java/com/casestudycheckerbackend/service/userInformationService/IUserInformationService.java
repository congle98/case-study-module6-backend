package com.casestudycheckerbackend.service.userInformationService;

import com.casestudycheckerbackend.dto.request.RegisterProviderRequest;
import com.casestudycheckerbackend.dto.request.UpdateAvatarRequest;
import com.casestudycheckerbackend.dto.request.UserInformationUpdateRequest;
import com.casestudycheckerbackend.dto.response.ProviderHomeResponse;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserInformationService extends IGeneralService<UserInformation> {
    UserInformation findByUser(User user);
    UserInformation setViews(Long id);


    UserInformationUpdateRequest getUserInfoUpdateRequest(Long userId);

    UserInformationUpdateRequest userInformationUpdate(UserInformationUpdateRequest userInformationUpdateRequest);

    Boolean isProvider(Long id);
    Double changePrice(Long id, Double price);

    UserInformation providerStatusOff(Long userInformationId);

    UserInformation updateAvatar(UpdateAvatarRequest updateAvatarRequest);

    UserInformation addImage(UpdateAvatarRequest updateAvatarRequest);

    UserInformation registerProvider(RegisterProviderRequest registerProviderRequest);

    Page<ProviderHomeResponse> findAllProviderHomePage(Pageable pageable);
    List<ProviderHomeResponse> findAllProviderHome();



    Page<ProviderHomeResponse> test(Pageable pageable);

    ProviderHomeResponse convert (UserInformation userInformation);
}
