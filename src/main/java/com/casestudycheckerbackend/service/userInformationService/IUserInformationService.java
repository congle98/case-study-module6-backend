package com.casestudycheckerbackend.service.userInformationService;

import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserInformationService extends IGeneralService<UserInformation> {
    UserInformation findByUser(User user);
    UserInformation setViews(Long id);
    Boolean isProvider(Long id);
    Double changePrice(Long id, Double price);
    Page<UserInformation> findAllProviderByViews(Pageable pageable);
    Integer countProviders();
    Page<UserInformation> findAllProviderByPrice(Pageable pageable);

}
