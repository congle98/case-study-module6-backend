package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,Long> {
    UserInformation findByUser(User user);
    @Query(value = "select * from user_information where is_provider=true order by number_of_views desc",nativeQuery = true)
    Page<UserInformation> findAllByIsProviderByViews(Pageable pageable);
    @Query(value = "select count(id) from user_information where is_provider = true",nativeQuery = true)
    Integer countProviders();
    @Query(value = "select * from user_information where is_provider=true order by price_by_hour desc",nativeQuery = true)
    Page<UserInformation> findAllByIsProviderByPrice(Pageable pageable);
}
