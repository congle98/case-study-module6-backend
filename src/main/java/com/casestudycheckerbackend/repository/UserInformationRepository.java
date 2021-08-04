package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,Long> {
    UserInformation findByUser(User user);

    Page<UserInformation> findAllByIsProvider(Boolean bl,Pageable pageable);

    List<UserInformation> findByIsProvider(Boolean bl);
}
