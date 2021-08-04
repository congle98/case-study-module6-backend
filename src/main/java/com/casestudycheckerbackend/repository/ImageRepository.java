package com.casestudycheckerbackend.repository;

import com.casestudycheckerbackend.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "select url from image where user_information_id = ? and category_image_id=1",nativeQuery = true)
    String getAvatar(long id);

}
