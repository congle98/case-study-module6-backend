package com.casestudycheckerbackend.service.userInformationService;

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
        return  userInformationRepository.save(userInformation);
    }

    @Override
    public UserInformation setViews(Long id) {
        UserInformation userInformation = userInformationRepository.findById(id).get();
        userInformation.setNumberOfViews(userInformation.getNumberOfViews()+1);
        return  userInformationRepository.save(userInformation);
    }

    @Override
    public Boolean isProvider(Long id) {
       UserInformation  user =  userInformationRepository.getById(id);
        if(user!=null){
            Boolean check= user.getIsProvider();
            user.setIsProvider(!check);
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
}
