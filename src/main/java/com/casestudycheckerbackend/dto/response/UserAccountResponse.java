package com.casestudycheckerbackend.dto.response;

import com.casestudycheckerbackend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountResponse {
    private String username;

    private String email;

    private String phone;

    private Boolean accountStatus ;

    private String joinDate ;

    private String role;
}
