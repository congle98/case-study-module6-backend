package com.casestudycheckerbackend.dto.response.user;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String username;
    private String email;
}
