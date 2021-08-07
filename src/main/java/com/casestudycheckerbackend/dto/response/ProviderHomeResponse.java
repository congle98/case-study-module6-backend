package com.casestudycheckerbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderHomeResponse {
    private Long userId;
    private String name;
    private String avatar;
    private String description;
    private List<String> servicesName;
    private Double priceByHour;
}
