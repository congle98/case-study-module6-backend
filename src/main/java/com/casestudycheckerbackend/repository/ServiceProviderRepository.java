package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.ServicesProvided;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServicesProvided, Long> {

}
