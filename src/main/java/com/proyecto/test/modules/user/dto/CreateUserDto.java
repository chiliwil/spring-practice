package com.proyecto.test.modules.user.dto;

import com.proyecto.test.modules.user.UserEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {
    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String gender;

    private Long addressId;
}
