package com.casestudycheckerbackend.service.servicesProvided;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.service.IGeneralService;

public interface IServicesProvidedService extends IGeneralService<ServicesProvided> {
    Iterable<ServicesProvided> find3ServiceByProvider(long id);
}
