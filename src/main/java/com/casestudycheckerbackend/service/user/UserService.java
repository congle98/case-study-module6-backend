package com.casestudycheckerbackend.service.user;

import com.casestudycheckerbackend.dto.response.UserAccountResponse;
import com.casestudycheckerbackend.dto.response.UserLoginResponse;
import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.Role;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.UserRepository;
import com.casestudycheckerbackend.service.email.EmailService;
import com.casestudycheckerbackend.service.userInformationService.UserInformationService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private UserService userService;

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
            userLoginResponse.setIsProvider(userInformation.getProvider());
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
    public User findByVerificationCode(String code) {
        return userRepository.findByVerificationCode(code);
    }

    @Override
    public Boolean verify(String code) {
        User user = findByVerificationCode(code);
        System.out.println(user);
        if(user!=null){
            user.setIsVerifyEmail(true);
            user.setAccountStatus(true);
            userRepository.save(user);
            return true;
        }else
            return false;
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
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        UserInformation userInformation = new UserInformation();
        User userDB = userRepository.save(user);
        userInformation.setUser(userDB);
        userInformation.setFullName(userDB.getUsername());
        userInformationService.save(userInformation);
        return userDB;
    }


    @Override
    public UserAccountResponse getUserAccount(Long id) {
        UserAccountResponse userchange = new UserAccountResponse();
        Optional<User> user= userRepository.findById(id);
         if(user.isPresent()){
             User user1 = user.get();
             userchange.setUsername(user1.getUsername());
             userchange.setEmail(user1.getEmail());
             userchange.setPhone(user1.getPhone());
             userchange.setAccountStatus(user1.getAccountStatus());
             userchange.setJoinDate(user1.getJoinDate().toString());
             userchange.setRole(user1.getRoles().get(0).getName());
         }
         return  userchange;
    }


    @Override
    public Page<User> findAllPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


}
