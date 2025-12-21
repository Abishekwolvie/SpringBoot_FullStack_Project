package com.abishek.ecommercewebsiteapiproject.users.userdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(@NotBlank(message = "Username cannot be empty") String username,
                       @NotBlank(message = "Password cannot be empty") String password) {
}
