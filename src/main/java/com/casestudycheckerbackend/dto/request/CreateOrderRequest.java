package com.casestudycheckerbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderRequest {
    private Long userId;
    private Long providerInformationId;
    private String address;
    private  Long hour;
    private String startTime;
    private LocalDate day;
    private Double totalPrice;

}
