package com.casestudycheckerbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFilterRequest {
    private String gender;
    private String city;
    private Double price;
}
