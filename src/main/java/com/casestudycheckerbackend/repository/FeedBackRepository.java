package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.FeedbackOrder;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedbackOrder,Long> {
    Page<FeedbackOrder> findAllByConfirmAndProvider(Boolean bl, UserInformation provider, Pageable pageable);
}
