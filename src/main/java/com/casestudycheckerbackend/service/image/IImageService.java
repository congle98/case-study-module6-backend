package com.casestudycheckerbackend.service.image;

import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.service.IGeneralService;

import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    String getAvatars(long id);
}
