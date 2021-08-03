package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OderRepository extends JpaRepository<Oder, Long> {
    Iterable<Oder> findByProvider(UserInformation provider);
    Iterable<Oder> findByUser(UserInformation user);

}
