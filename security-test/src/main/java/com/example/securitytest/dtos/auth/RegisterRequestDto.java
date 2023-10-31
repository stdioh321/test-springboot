package com.example.securitytest.dtos.auth;

import com.example.securitytest.enums.UserRole;
import com.example.securitytest.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    private UserRole role;

    public User toEntity(){
        var user = new User();
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setRole(this.getRole());
        return user;
    }
}
