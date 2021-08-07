package com.casestudycheckerbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderRequest {
    private Long userId;
    private Long providerId;
    private Double money;
}
