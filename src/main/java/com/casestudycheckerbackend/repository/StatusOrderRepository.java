package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.StatusOder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOder,Long> {
}
