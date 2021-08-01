package com.casestudycheckerbackend.service.image;

import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.User;
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
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
