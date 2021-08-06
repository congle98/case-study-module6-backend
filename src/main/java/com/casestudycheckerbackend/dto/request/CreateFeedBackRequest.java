package com.casestudycheckerbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFeedBackRequest {

    private Long orderId;

    private Integer starRating;

    private String description;
    }
