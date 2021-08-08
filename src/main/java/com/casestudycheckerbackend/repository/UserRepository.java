package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByVerificationCode(String code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET account_status = !account_status WHERE id=?",nativeQuery = true)
    void changeStatus(Long id);
}
