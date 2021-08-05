package com.casestudycheckerbackend.service.oder;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.dto.response.MessageResponse;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;

public interface IOderService extends IGeneralService<Oder> {
    Iterable<Oder> findByProvider(UserInformation provider);

    Iterable<Oder> findByUser(UserInformation user);

    Oder createNewOrder(CreateOrderRequest createOrderRequest);

    StatusOder changeStatus(String status);

    StatusOder cancelOrder(String status);

    boolean acceptOrder(Long id, String status);
}
