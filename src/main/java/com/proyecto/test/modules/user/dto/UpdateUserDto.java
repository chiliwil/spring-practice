package com.proyecto.test.modules.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UpdateUserDto {
    private String username;

    @Size(min = 5, max = 20)
    private String gender;
}
