package com.casestudycheckerbackend.service.providerServices;

import com.casestudycheckerbackend.dto.ProviderDto;
import com.casestudycheckerbackend.models.Role;
import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.image.ImageService;
import com.casestudycheckerbackend.service.servicesProvided.ServicesProvidedService;
import com.casestudycheckerbackend.service.userInformationService.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {
    @Autowired
    UserInformationService userInformationService;
    @Autowired
    ServicesProvidedService servicesProvidedService;
    @Autowired
    ImageService imageService;


    public Page<ProviderDto> findAllProviderByViews(Pageable pageable) {
        System.out.println("fsdfsfs");
        Page<UserInformation> providerList = userInformationService.findAllProviderByViews(pageable);
        List<ProviderDto> providerDtoList = new ArrayList<>();
        for (UserInformation provider: providerList) {
            ProviderDto providerDto = new ProviderDto();
            providerDto.setId(provider.getId());
            providerDto.setName(provider.getFullName());
            providerDto.setDescription(provider.getDescription());
            providerDto.setServices(servicesProvidedService.find3ServiceByProvider(provider.getUser().getId()));
            providerDto.setPriceperh(provider.getPriceByHour());
            providerDto.setAvatar(imageService.getAvatars(provider.getId()));
            providerDto.setViews(provider.getNumberOfViews());
            providerDtoList.add(providerDto);
        }
        return new PageImpl<>(providerDtoList,pageable,providerDtoList.size());
    }
    public Page<ProviderDto> findAllProviderByPrice(Pageable pageable) {
        System.out.println("fsdfsfs");
        Page<UserInformation> providerList = userInformationService.findAllProviderByPrice(pageable);
        List<ProviderDto> providerDtoList = new ArrayList<>();
        for (UserInformation provider: providerList) {
            ProviderDto providerDto = new ProviderDto();
            providerDto.setId(provider.getId());
            providerDto.setName(provider.getFullName());
            providerDto.setDescription(provider.getDescription());
            providerDto.setServices(servicesProvidedService.find3ServiceByProvider(provider.getUser().getId()));
            providerDto.setPriceperh(provider.getPriceByHour());
            providerDto.setAvatar(imageService.getAvatars(provider.getId()));
            providerDto.setViews(provider.getNumberOfViews());
            providerDtoList.add(providerDto);
        }
        return new PageImpl<>(providerDtoList,pageable,providerDtoList.size());
    }
}
