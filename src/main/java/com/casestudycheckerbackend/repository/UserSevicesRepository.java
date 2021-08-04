package com.casestudycheckerbackend.repository;


import com.casestudycheckerbackend.models.UserServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSevicesRepository extends JpaRepository<UserServices,Long> {
}
