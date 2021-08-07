package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryServiceRepository extends JpaRepository<CategoryService,Long> {
}
