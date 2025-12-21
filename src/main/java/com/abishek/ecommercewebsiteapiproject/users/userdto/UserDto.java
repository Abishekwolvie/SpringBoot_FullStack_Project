package com.abishek.ecommercewebsiteapiproject.users.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(@NotBlank(message = "Username cannot be empty") String username,
                      @NotBlank(message = "Password cannot be empty") @Size(min = 6, message = "Password must be at least 6 characters")String password,
                      @NotBlank(message = "Mobile cannot be empty")String mobile,
                      String role) {
}
