package com.casestudycheckerbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAvatarRequest {
    private Long userInformationId;
    private String url;
}
