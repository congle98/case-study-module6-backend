package com.casestudycheckerbackend.service.image;

import com.casestudycheckerbackend.dto.request.UpdateAvatarRequest;
import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;

import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    Image updateAvatarOfUserInformation(UpdateAvatarRequest updateAvatarRequest);
    Image addImage(UpdateAvatarRequest updateAvatarRequest);

    Image avatarByUserInformation(UserInformation userInformation);
}
