package com.casestudycheckerbackend.dto.response;

import com.casestudycheckerbackend.models.Image;
import com.casestudycheckerbackend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginResponse {
    private Long id;
    private Image avatar;
    private String username;
    private List<Role> roles;
}
