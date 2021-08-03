package com.casestudycheckerbackend.dto.response;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class UserOderResponse {

    @Id
    private Long id;
    private String providerName;
    private String providerPhone;
    private int price;
    private String address;
    private Long hour;
    private String startTime;
    private LocalDate day;
}
