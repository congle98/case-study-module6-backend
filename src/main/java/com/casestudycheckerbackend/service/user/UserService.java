package com.casestudycheckerbackend.service.user;

import com.casestudycheckerbackend.dto.response.UserLoginResponse;
import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.Role;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.UserRepository;
import com.casestudycheckerbackend.service.userInformationService.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserLoginResponse userToUserLoginResponse(User user) {
        UserInformation userInformation = userInformationService.findByUser(user);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setId(user.getId());
        userLoginResponse.setRoles(user.getRoles());
        userLoginResponse.setUsername(user.getUsername());
        if(userInformation!=null){
            userLoginResponse.setMoney(userInformation.getMoney());
            userLoginResponse.setIsProvider(userInformation.getIsProvider());
            for (Image image: userInformation.getImage()
            ) {
                if(image.getCategoryImage().getName().equalsIgnoreCase("Avatar")){
                    userLoginResponse.setAvatar(image);
                }
            }
        }
        return userLoginResponse;
    }



    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = new Role();
        //role user trong db có id là 2
        role.setId(2l);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);

        return userRepository.save(user);
    }




}
