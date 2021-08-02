package com.casestudycheckerbackend.dto.request;

import com.casestudycheckerbackend.models.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationUpdateRequest {
    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Boolean gender;
    private City city;
    private Double height;
    private Double weight;
    private String hobbies;
    private String description;
    private String facebookLink;
    private Boolean isProvider;
    private Double  priceByHour;


}
