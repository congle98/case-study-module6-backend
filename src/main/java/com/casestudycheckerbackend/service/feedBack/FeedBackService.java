package com.casestudycheckerbackend.service.feedBack;

import com.casestudycheckerbackend.dto.request.CreateFeedBackRequest;
import com.casestudycheckerbackend.dto.response.FeedBackByProviderRespone;
import com.casestudycheckerbackend.models.FeedbackOrder;
import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.repository.FeedBackRepository;
import com.casestudycheckerbackend.service.image.IImageService;
import com.casestudycheckerbackend.service.oder.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackService implements IFeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private IOderService oderService;
    @Autowired
    private IImageService imageService;

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
        feedbackOrder.setProvider(oder.getProvider());
        oder.setFeedback(feedbackOrderNew);
        oderService.save(oder);
        return feedbackOrderNew;
    }

    @Override
    public Page<FeedBackByProviderRespone> findAllByProvider(Long userId, Pageable pageable) {
        UserInformation provider = new UserInformation();
        provider.setId(userId);
       Page<FeedbackOrder> feedbackOrder = feedBackRepository.findAllByConfirmAndProvider(true,provider,pageable);
//        UserInformation provider = new UserInformation();
//        provider.setId(userId);
//        Page<Oder> oderPage = oderService.finAllByProviderPage(provider, pageable);
//        System.out.println("đây là orderPage" + oderPage.getTotalPages());
//        System.out.println("đây là orderPage" + oderPage.getTotalElements());
//        for (Oder order : oderPage.getContent()
//        ) {
//            if (order.getFeedback() == null || !order.getFeedback().getConfirm()) {
//                oderPage.getContent().remove(order);
//            }
//        }
        Page<FeedBackByProviderRespone> feedBacksByProvider = feedbackOrder.map(this::convert);
        return feedBacksByProvider;
    }

    @Override
    public FeedBackByProviderRespone convert(FeedbackOrder feedbackOrder) {
        FeedBackByProviderRespone feedBackByProviderRespone = new FeedBackByProviderRespone();
        feedBackByProviderRespone.setDescription(feedbackOrder.getDescription());
        feedBackByProviderRespone.setStarRating(feedbackOrder.getStarRating());
        feedBackByProviderRespone.setUserName(feedbackOrder.getOder().getUser().getFullName());
        Image avatar = imageService.avatarByUserInformation(feedbackOrder.getOder().getUser());
        if (avatar != null) {
            String avatarUrl = avatar.getUrl();
            feedBackByProviderRespone.setAvatarUrl(avatarUrl);
        }
        return feedBackByProviderRespone;
    }

}
