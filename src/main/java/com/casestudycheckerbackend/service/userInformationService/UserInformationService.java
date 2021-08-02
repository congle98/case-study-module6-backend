package com.casestudycheckerbackend.service.userInformationService;

import com.casestudycheckerbackend.dto.request.UserInformationUpdateRequest;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationService implements IUserInformationService{
    @Autowired
    private UserInformationRepository userInformationRepository;


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
        userInformationUpdateRequest.setIsProvider(userInformation.getIsProvider());
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
        userInformation.setIsProvider(userInformationUpdateRequest.getIsProvider());
        userInformation.setPriceByHour(userInformationUpdateRequest.getPriceByHour());
        userInformationRepository.save(userInformation);
        return  userInformationUpdateRequest;
    }
}
