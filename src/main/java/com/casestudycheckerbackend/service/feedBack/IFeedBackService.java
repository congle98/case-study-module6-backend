package com.casestudycheckerbackend.service.feedBack;

import com.casestudycheckerbackend.dto.request.CreateFeedBackRequest;
import com.casestudycheckerbackend.dto.response.FeedBackByProviderRespone;
import com.casestudycheckerbackend.dto.response.ProviderHomeResponse;
import com.casestudycheckerbackend.models.FeedbackOrder;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFeedBackService extends IGeneralService<FeedbackOrder> {
    Boolean changeStatus(Long id);

    FeedbackOrder createFeedBack(CreateFeedBackRequest feedBackRequest);

    Page<FeedBackByProviderRespone> findAllByProvider(Long userId, Pageable pageable);

    FeedBackByProviderRespone convert (FeedbackOrder feedbackOrder);
}
