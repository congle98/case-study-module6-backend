package com.casestudycheckerbackend.service.feedBack;

import com.casestudycheckerbackend.dto.request.CreateFeedBackRequest;
import com.casestudycheckerbackend.models.FeedbackOrder;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.repository.FeedBackRepository;
import com.casestudycheckerbackend.service.oder.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedBackService implements IFeedBackService{
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private IOderService oderService;
    @Override
    public Iterable<FeedbackOrder> findAll() {
        return feedBackRepository.findAll();
    }

    @Override
    public Optional<FeedbackOrder> findById(Long id) {
        return feedBackRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        feedBackRepository.deleteById(id);
    }

    @Override
    public FeedbackOrder save(FeedbackOrder feedbackOrder) {
        return feedBackRepository.save(feedbackOrder);
    }

    @Override
    public Boolean changeStatus(Long id) {
        FeedbackOrder feedbackOrder = feedBackRepository.findById(id).get();
        feedbackOrder.setConfirm(!feedbackOrder.getConfirm());
        feedBackRepository.save(feedbackOrder);
        return true;
    }

    @Override
    public FeedbackOrder createFeedBack(CreateFeedBackRequest feedBackRequest) {
        Oder oder = oderService.findById(feedBackRequest.getOrderId()).get();
        FeedbackOrder feedbackOrder = new FeedbackOrder();
        feedbackOrder.setDescription(feedBackRequest.getDescription());
        feedbackOrder.setOder(oder);
        feedbackOrder.setStarRating(feedBackRequest.getStarRating());
         FeedbackOrder feedbackOrderNew = feedBackRepository.save(feedbackOrder);
         oder.setFeedback(feedbackOrderNew);
         oderService.save(oder);
        return feedbackOrderNew;
    }
}
