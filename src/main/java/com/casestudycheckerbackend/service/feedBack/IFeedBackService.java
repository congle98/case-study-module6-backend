package com.casestudycheckerbackend.service.feedBack;

import com.casestudycheckerbackend.dto.request.CreateFeedBackRequest;
import com.casestudycheckerbackend.models.FeedbackOrder;
import com.casestudycheckerbackend.service.IGeneralService;

public interface IFeedBackService extends IGeneralService<FeedbackOrder> {
    Boolean changeStatus(Long id);

    FeedbackOrder createFeedBack(CreateFeedBackRequest feedBackRequest);
}
