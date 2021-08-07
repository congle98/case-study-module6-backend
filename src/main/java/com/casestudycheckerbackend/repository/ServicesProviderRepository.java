package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.ServicesProvided;
import com.casestudycheckerbackend.models.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesProviderRepository extends JpaRepository<ServicesProvided,Long> {
        List<ServicesProvided> findAllByUserInformations(UserInformation userInformation);
}
