package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.dto.response.UserOderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;

public interface UserOderResponseRepository extends JpaRepository<UserOderResponse, Long> {

    @Query(nativeQuery = true, value = "SELECT " +
            "   oder.id, " +
            "   username AS provider_name, " +
            "   phone    AS provider_phone, " +
            "   price, " +
            "   address, " +
            "   hour, " +
            "   start_time, " +
            "   day " +
            "FROM oder " +
            "   LEFT JOIN user ON oder.provider_id = user.id " +
            "WHERE oder.id = :id")
    UserOderResponse getOder(@Param("id") long id);
}
