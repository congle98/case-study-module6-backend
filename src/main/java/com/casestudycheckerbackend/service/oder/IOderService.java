package com.casestudycheckerbackend.service.oder;

import com.casestudycheckerbackend.dto.response.UserOderResponse;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;

public interface IOderService extends IGeneralService<Oder> {
    Iterable<Oder> findByProvider(UserInformation provider);

    Iterable<Oder> findByUser(UserInformation user);

    UserOderResponse getOder(long id);
}
