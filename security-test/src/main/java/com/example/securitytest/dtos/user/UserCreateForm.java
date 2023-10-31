package com.example.securitytest.dtos.user;


import com.example.securitytest.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateForm {
    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Size(min = 8)
    @Pattern(regexp = User.PASSWORD_PATTERN, message = "Password should have a minimum of 8 characters long and includes at least one uppercase letter, one number, and one special character.")
    private String password;

    @NotNull
    @Email
    private String email;

}
