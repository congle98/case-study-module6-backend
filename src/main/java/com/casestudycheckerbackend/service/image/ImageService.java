package com.casestudycheckerbackend.service.image;

import com.casestudycheckerbackend.dto.request.UpdateAvatarRequest;
import com.casestudycheckerbackend.models.CategoryImage;
import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService implements IImageService{

    @Autowired ImageRepository imageRepository;

    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image updateAvatarOfUserInformation(UpdateAvatarRequest updateAvatarRequest) {
        UserInformation userInformation = new UserInformation();
        userInformation.setId(updateAvatarRequest.getUserInformationId());
        CategoryImage categoryImage = new CategoryImage();
        categoryImage.setId(1l);
        Image image = imageRepository.findByCategoryImageAndUserInformation(categoryImage,userInformation);
        image.setUrl(updateAvatarRequest.getUrl());
        return imageRepository.save(image);
    }

    @Override
    public Image addImage(UpdateAvatarRequest updateAvatarRequest) {
        UserInformation userInformation = new UserInformation();
        userInformation.setId(updateAvatarRequest.getUserInformationId());
        CategoryImage categoryImage = new CategoryImage();
        categoryImage.setId(2l);
        Image image = new Image();
        image.setCategoryImage(categoryImage);
        image.setUserInformation(userInformation);
        image.setUrl(updateAvatarRequest.getUrl());
        return imageRepository.save(image);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
