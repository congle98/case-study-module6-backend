package com.casestudycheckerbackend.service.servicesProvided;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;

import java.util.List;

public interface IServicesProvidedService extends IGeneralService<ServicesProvided> {
    List<ServicesProvided> findAllByProvider(UserInformation userInformation);
}
