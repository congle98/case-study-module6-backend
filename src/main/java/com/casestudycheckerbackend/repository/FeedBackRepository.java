package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.FeedbackOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedbackOrder,Long> {
}
