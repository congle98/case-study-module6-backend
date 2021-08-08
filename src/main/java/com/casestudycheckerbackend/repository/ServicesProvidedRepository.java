package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.ServicesProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ServicesProvidedRepository extends JpaRepository<ServicesProvided,Long> {
    @Query(value = "select services_provided.id,name,category_service_id from services_provided inner join user_services us on services_provided.id=us.services_provided_id and user_id=? and service_status = true order by rand() limit 3;",nativeQuery = true)
    Iterable<ServicesProvided> find3ServicesProvidedByProvider(Long id);

}
