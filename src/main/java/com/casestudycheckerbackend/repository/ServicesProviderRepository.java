package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.ServicesProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesProviderRepository extends JpaRepository<ServicesProvided,Long> {
}
