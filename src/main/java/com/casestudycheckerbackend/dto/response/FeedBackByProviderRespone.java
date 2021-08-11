package com.casestudycheckerbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackByProviderRespone {
    private String userName;
    private String avatarUrl;
    private Integer starRating;
    private String description;
    private LocalDateTime createDate;
}
