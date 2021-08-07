package com.casestudycheckerbackend.dto.request;

import com.casestudycheckerbackend.models.ServicesProvided;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProviderRequest {
    private Long userInformationId;
    private List<ServicesProvided> services;
    private Double priceByHour;
}
