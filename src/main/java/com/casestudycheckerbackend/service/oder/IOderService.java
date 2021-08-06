package com.casestudycheckerbackend.service.oder;

import com.casestudycheckerbackend.dto.request.CreateOrderRequest;
import com.casestudycheckerbackend.models.Oder;
import com.casestudycheckerbackend.models.StatusOder;
import com.casestudycheckerbackend.models.User;
import com.casestudycheckerbackend.models.UserInformation;
import com.casestudycheckerbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOderService extends IGeneralService<Oder> {
    Iterable<Oder> findByProvider(UserInformation provider);
    Iterable<Oder> findByUser(UserInformation user);
    Oder  createNewOrder(CreateOrderRequest createOrderRequest);
    StatusOder changeStatus(Long statusId);
    Boolean cancelOrder(Long id, Long statusId);
    Boolean acceptOrder(Long id, Long statusId);
    Page<Oder> findAllPage(Pageable pageable);

}
