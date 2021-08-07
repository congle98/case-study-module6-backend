package com.casestudycheckerbackend.service.userInformationService;

import com.casestudycheckerbackend.dto.request.RegisterProviderRequest;
import com.casestudycheckerbackend.dto.request.UpdateAvatarRequest;
import com.casestudycheckerbackend.dto.request.UserInformationUpdateRequest;
import com.casestudycheckerbackend.dto.response.ProviderHomeResponse;
import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.UserInformationRepository;
import com.casestudycheckerbackend.service.image.IImageService;
import com.casestudycheckerbackend.service.servicesProvided.IServicesProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInformationService implements IUserInformationService{
    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IServicesProvidedService providedService;

    @Override
    public Iterable<UserInformation> findAll() {
        return userInformationRepository.findAll();
    }

    @Override
    public Optional<UserInformation> findById(Long id) {
        return userInformationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userInformationRepository.deleteById(id);
    }

    @Override
    public UserInformation findByUser(User user) {
        return userInformationRepository.findByUser(user);
    }

    @Override
    public UserInformation save(UserInformation userInformation) {
        return userInformationRepository.save(userInformation);
    }


    @Override
    public UserInformation setViews(Long id) {
        UserInformation userInformation = userInformationRepository.findById(id).get();
        userInformation.setNumberOfViews(userInformation.getNumberOfViews() + 1);
        return userInformationRepository.save(userInformation);
    }

    @Override
    public UserInformationUpdateRequest getUserInfoUpdateRequest(Long userId) {
        UserInformationUpdateRequest userInformationUpdateRequest = new UserInformationUpdateRequest();
        User user = new User();
        user.setId(userId);
        UserInformation userInformation = userInformationRepository.findByUser(user);
        userInformationUpdateRequest.setId(userInformation.getId());
        userInformationUpdateRequest.setCity(userInformation.getCity());
        userInformationUpdateRequest.setDescription(userInformation.getDescription());
        userInformationUpdateRequest.setDateOfBirth(userInformation.getDateOfBirth());
        userInformationUpdateRequest.setFacebookLink(userInformation.getFacebookLink());
        userInformationUpdateRequest.setGender(userInformation.getGender());
        userInformationUpdateRequest.setFullName(userInformation.getFullName());
        userInformationUpdateRequest.setHobbies(userInformation.getHobbies());
        userInformationUpdateRequest.setHeight(userInformation.getHeight());
        userInformationUpdateRequest.setWeight(userInformation.getWeight());
        userInformationUpdateRequest.setIsProvider(userInformation.getProvider());
        userInformationUpdateRequest.setPriceByHour(userInformation.getPriceByHour());
        return userInformationUpdateRequest;
    }

    @Override
    public UserInformationUpdateRequest userInformationUpdate(UserInformationUpdateRequest userInformationUpdateRequest) {
        UserInformation userInformation = userInformationRepository.findById(userInformationUpdateRequest.getId()).get();
        userInformation.setCity(userInformationUpdateRequest.getCity());
        userInformation.setDescription(userInformationUpdateRequest.getDescription());
        userInformation.setDateOfBirth(userInformationUpdateRequest.getDateOfBirth());
        userInformation.setFacebookLink(userInformationUpdateRequest.getFacebookLink());
        userInformation.setGender(userInformationUpdateRequest.getGender());
        userInformation.setFullName(userInformationUpdateRequest.getFullName());
        userInformation.setHobbies(userInformationUpdateRequest.getHobbies());
        userInformation.setHeight(userInformationUpdateRequest.getHeight());
        userInformation.setWeight(userInformationUpdateRequest.getWeight());
        userInformation.setProvider(userInformationUpdateRequest.getIsProvider());
        userInformation.setPriceByHour(userInformationUpdateRequest.getPriceByHour());
        userInformationRepository.save(userInformation);
        return  userInformationUpdateRequest;
    }

    @Override
    public Boolean isProvider(Long id) {
       UserInformation  user =  userInformationRepository.getById(id);
        if(user!=null){
            Boolean check= user.getProvider();
            user.setProvider(!check);
            userInformationRepository.save(user);
            return !check;
        }
        return false;
    }

    @Override
    public Double changePrice(Long id, Double price){
        UserInformation  user =  userInformationRepository.getById(id);
        if(user!=null){

            user.setPriceByHour(price);
            userInformationRepository.save(user);
            return price;
        }
        return 0.0;
    }

    @Override
    public UserInformation providerStatusOff(Long userInformationId) {
        UserInformation userInformation = userInformationRepository.findById(userInformationId).get();
        userInformation.setProvider(false);
        return userInformationRepository.save(userInformation);
    }

    @Override
    public UserInformation updateAvatar(UpdateAvatarRequest updateAvatarRequest) {
        imageService.updateAvatarOfUserInformation(updateAvatarRequest);
        return userInformationRepository.findById(updateAvatarRequest.getUserInformationId()).get();

    }

    @Override
    public UserInformation addImage(UpdateAvatarRequest updateAvatarRequest) {
        imageService.addImage(updateAvatarRequest);

        return userInformationRepository.findById(updateAvatarRequest.getUserInformationId()).get();
    }

    @Override
    public UserInformation registerProvider(RegisterProviderRequest registerProviderRequest) {
        UserInformation userInformation = userInformationRepository.findById(registerProviderRequest.getUserInformationId()).get();
        userInformation.setPriceByHour(registerProviderRequest.getPriceByHour());
        userInformation.setServices(registerProviderRequest.getServices());
        userInformation.setProvider(true);
        return userInformationRepository.save(userInformation);
    }

    @Override
    public Page<ProviderHomeResponse> findAllProviderHomePage(Pageable pageable) {
        Page<UserInformation> userInformationPage = userInformationRepository.findAllByIsProvider(true,pageable);
        List<ProviderHomeResponse> providerHomeResponseList = new ArrayList<>();
        for (UserInformation userInformation: userInformationPage.getContent()) {
            ProviderHomeResponse providerHomeResponse = new ProviderHomeResponse();
            providerHomeResponse.setDescription(userInformation.getDescription());
            providerHomeResponse.setPriceByHour(userInformation.getPriceByHour());
            providerHomeResponse.setUserId(userInformation.getUser().getId());
            providerHomeResponse.setName(userInformation.getFullName());
            String avatarUrl = "";
            Image image = imageService.avatarByUserInformation(userInformation);
            if(image!=null){
                avatarUrl = image.getUrl();
            }
            List<String> serviceName = new ArrayList<>();
//            List<ServicesProvided> servicesProvideds = providedService.findAllByProvider(userInformation);
            for (ServicesProvided service: userInformation.getServices()
                 ) {
                serviceName.add(service.getName());
            }
            providerHomeResponse.setAvatar(avatarUrl);
            providerHomeResponse.setServicesName(serviceName);
            providerHomeResponseList.add(providerHomeResponse);
        }
        return new PageImpl<>(providerHomeResponseList,pageable,providerHomeResponseList.size());
    }

    @Override
    public List<ProviderHomeResponse> findAllProviderHome() {
        List<UserInformation> userInformations = userInformationRepository.findByIsProvider(true);
        List<ProviderHomeResponse> providerHomeResponseList = new ArrayList<>();
        for (UserInformation userInformation: userInformations) {
            ProviderHomeResponse providerHomeResponse = new ProviderHomeResponse();
            providerHomeResponse.setDescription(userInformation.getDescription());
            providerHomeResponse.setPriceByHour(userInformation.getPriceByHour());
            providerHomeResponse.setUserId(userInformation.getUser().getId());
            providerHomeResponse.setName(userInformation.getFullName());
            List<String> serviceName = new ArrayList<>();

            for (ServicesProvided service: userInformation.getServices()
            ) {
                serviceName.add(service.getName());
            }
            providerHomeResponse.setServicesName(serviceName);
            providerHomeResponseList.add(providerHomeResponse);
        }
        return  providerHomeResponseList;
    }

    @Override
    public Page<ProviderHomeResponse> test(Pageable pageable) {
        Page<UserInformation> userInformationPage = userInformationRepository.findAllByIsProvider(true,pageable);
        Page<ProviderHomeResponse> providerHomeResponse = userInformationPage.map(this::convert);
        return  providerHomeResponse;
    }
    @Override
    public ProviderHomeResponse convert(UserInformation userInformation) {
             ProviderHomeResponse providerHomeResponse = new ProviderHomeResponse();
            providerHomeResponse.setDescription(userInformation.getDescription());
            providerHomeResponse.setPriceByHour(userInformation.getPriceByHour());
            providerHomeResponse.setUserId(userInformation.getUser().getId());
            providerHomeResponse.setName(userInformation.getFullName());
            String avatarUrl = "";
            Image image = imageService.avatarByUserInformation(userInformation);
            if(image!=null){
                avatarUrl = image.getUrl();
            }
            List<String> serviceName = new ArrayList<>();
//            List<ServicesProvided> servicesProvideds = providedService.findAllByProvider(userInformation);
            for (ServicesProvided service: userInformation.getServices()
            ) {
                serviceName.add(service.getName());
            }
            providerHomeResponse.setAvatar(avatarUrl);
            providerHomeResponse.setServicesName(serviceName);
            return providerHomeResponse;
    }

    @Override
    public Page<ProviderHomeResponse> findAllByIsProviderAndOrderByNumberOfViews(Pageable pageable) {
        Page<UserInformation> userInformationPage = userInformationRepository.findAllByIsProviderOrderByNumberOfViewsDesc(true,pageable);
        Page<ProviderHomeResponse> providerHomeResponse = userInformationPage.map(this::convert);
        return  providerHomeResponse;
    }
}
